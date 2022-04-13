package br.com.pedro.indicados.service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

import br.com.pedro.indicados.models.CsvLine;
import br.com.pedro.indicados.models.Indication;
import br.com.pedro.indicados.models.Producer;
import br.com.pedro.indicados.models.Studio;
import br.com.pedro.indicados.models.Title;
import br.com.pedro.indicados.repository.IndicationRepository;
import br.com.pedro.indicados.repository.ProducerRepository;
import br.com.pedro.indicados.repository.StudioRepository;
import br.com.pedro.indicados.repository.TitleRepository;

@Service
public class CsvFileService {
	
	@Autowired
	private TitleRepository titleRepository;
	
	@Autowired
	private StudioRepository studioReposiroy;
	
	@Autowired
	private ProducerRepository producerRepository;
	
	@Autowired
	private IndicationRepository indicationRepository;
	
	public List<CsvLine> importarDadosCSV(String arquivo) throws CsvException, IOException {	
		
		List<CsvLine> lines = new CsvToBeanBuilder<CsvLine>(new FileReader(arquivo))
				.withType(CsvLine.class)
				.withIgnoreEmptyLine(true)
				.withSkipLines(1)
				.withSeparator(';')				
				.build()
				.parse();	
		
		List<CsvLine> linesError = new ArrayList<CsvLine>(); 
		
		lines.forEach(line -> {
			try {
				saveDataCsv(line);
			} catch (Exception e) {	
				line.setError(e.getMessage());
				linesError.add(line);
			}
		});
		
		return linesError;
	}
	
	private void saveDataCsv(CsvLine line) throws Exception {		
		saveTitle(line);
		saveIndication(line);		
	}
	
	private void saveIndication(CsvLine line) throws Exception {
		
		Optional<Indication> indicationOptional = indicationRepository.findTitleIndicationByYear(line.getTitle(), line.getYear());
		List<Indication> winnerOptional = indicationRepository.findTitleWinnerByYear(line.getYear());
		
		if (!indicationOptional.isPresent()) {
			
			if ((!winnerOptional.isEmpty()) && (line.getWinner().equalsIgnoreCase("YES"))) {
				throw new Exception("There is already a winning title for this year");
			}		
		
			Optional<Title> titleOp = titleRepository.findByName(line.getTitle());
			
			Indication indication = new Indication();						
			indication.setTitle(titleOp.get());
			indication.setWinner( (line.getWinner().equalsIgnoreCase("YES") ? 1 : 0) );
			indication.setYear(line.getYear());
			
			indicationRepository.save(indication);
		}
		
	}
	
	private void saveTitle(CsvLine line) {
		
		Optional<Title> titleOp = titleRepository.findByName(line.getTitle());
		
		if (!titleOp.isPresent()) {
			Title title = new Title();
			title.setName(line.getTitle());
			title.setStudio(returnStudio(line.getStudio()));	
			title.setProducers(returnProducers(title.getProducers(), line));
			
			titleRepository.save(title);		
		}
		
	}
	
	private Studio returnStudio(String name) {
		Optional<Studio> studioOptional = studioReposiroy.findByName(name);
		
		if (studioOptional.isPresent()) {
			return studioOptional.get();
		} else {
			Studio studio = new Studio();
			studio.setName(name);
			
			studioReposiroy.save(studio);
			
			return studio;
		}
	}
	
	private List<Producer> returnProducers(List<Producer> producers, CsvLine line) {
		
		String producerName = returnNextProducer(line);
		
		while (producerName.length() > 0) {
			
			Optional<Producer> producerOptional = producerRepository.findByName(producerName);
			
			if (producerOptional.isPresent()) {
				
				if (!producers.contains(producerOptional.get()))
					producers.add(producerOptional.get());
							
			} else {
				Producer producer = new Producer();
				producer.setName(producerName);
				
				producerRepository.save(producer);
				
				producers.add(producer);
			}
						
			producerName = returnNextProducer(line);
		}
		
		return producers;		
	}
	
	private String returnNextProducer(CsvLine line) {		
		Integer posVirgula = line.getProducers().indexOf(",");
		Integer posAnd = line.getProducers().toUpperCase().indexOf(" AND ");
		Integer pos = (posAnd >= 0 ? posAnd :  posVirgula); 
		
		if ((pos > 0) && (posVirgula >= 0) && (pos > posVirgula)) {
			pos = posVirgula;
		} else if ((pos > 0) && (posAnd >= 0) && (pos > posAnd)) { 
			pos = posAnd;
		}
		
		if (pos < 0) {
			String producer = line.getProducers();
			line.setProducers("");
			return producer.trim();
		} else if (pos >= 0) {
			String producer = line.getProducers().substring(0, pos);
			
			if (posVirgula == pos) {
				line.setProducers(line.getProducers().replaceAll(producer+",", ""));
			} else {
				line.setProducers(line.getProducers().replaceAll(producer+" and", ""));
			}			
			
			return producer.trim();	
			
		} else {
			return line.getProducers().trim();
		}
	}
	
}
