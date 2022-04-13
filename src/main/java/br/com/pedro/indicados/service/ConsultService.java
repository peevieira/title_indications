package br.com.pedro.indicados.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pedro.indicados.controller.dto.ConsultDto;
import br.com.pedro.indicados.controller.dto.ConsultMaxMinDto;
import br.com.pedro.indicados.models.ProducerIndication;
import br.com.pedro.indicados.repository.IndicationRepository;
import br.com.pedro.indicados.service.utils.ProducerIndicationCalc;

@Service
public class ConsultService {
	
	@Autowired
	private IndicationRepository indicationRepository;
	
	public ConsultMaxMinDto consultMaxMinWiner() {
		List<ProducerIndication> producerIndication = indicationRepository.findProducerIndication();
		List<ProducerIndicationCalc> producerIndicationCalc = new ArrayList<ProducerIndicationCalc>();
				
		producerIndication.forEach(indication -> {
			List<ProducerIndicationCalc> pi = (List<ProducerIndicationCalc>) producerIndicationCalc.stream().filter(i -> i.getName().equals(indication.getName())).toList();
			
			if (pi.size() > 0) {
				pi.get(0).getYearsWin().add(indication.getYear());				
			} else {
				ProducerIndicationCalc piCalc = new ProducerIndicationCalc();
				piCalc.setName(indication.getName());
				piCalc.getYearsWin().add(indication.getYear());
				producerIndicationCalc.add(piCalc);
			}
		});
		
		producerIndicationCalc.forEach(indication -> {
			indication.CalcInterval();
		});		
				
		return processReturn(producerIndicationCalc, getMinInterval(producerIndicationCalc), getMaxInterval(producerIndicationCalc));
	}
	
	private Integer getMinInterval(List<ProducerIndicationCalc> producerIndicationCalc ) {
		Integer min = Integer.MAX_VALUE;
		
		for (Iterator iterator = producerIndicationCalc.iterator(); iterator.hasNext();) {
			ProducerIndicationCalc producerIndicationCalc2 = (ProducerIndicationCalc) iterator.next();
			
			if (min > producerIndicationCalc2.getMinInterval()) {
				min = producerIndicationCalc2.getMinInterval();
			}
			
		}
		
		return min;
	}
	
	private Integer getMaxInterval(List<ProducerIndicationCalc> producerIndicationCalc ) {
		Integer max = Integer.MIN_VALUE;
		
		for (Iterator iterator = producerIndicationCalc.iterator(); iterator.hasNext();) {
			ProducerIndicationCalc producerIndicationCalc2 = (ProducerIndicationCalc) iterator.next();
			
			if (max < producerIndicationCalc2.getMaxInterval()) {
				max = producerIndicationCalc2.getMaxInterval();
			}
			
		}
		
		return max;
	}
	
	private ConsultMaxMinDto processReturn(List<ProducerIndicationCalc> producerIndicationCalc, Integer min, Integer max) {
		ConsultMaxMinDto consultMinMax = new ConsultMaxMinDto();
		
		List<ProducerIndicationCalc> mins = producerIndicationCalc.stream().filter(i -> i.getMinInterval() == min).toList();
		List<ProducerIndicationCalc> maxs = producerIndicationCalc.stream().filter(i -> i.getMaxInterval() == max).toList();
		
		mins.forEach(m -> {
			
			ConsultDto consult = new ConsultDto();
			consult.setInterval(m.getMinInterval());
			consult.setProducer(m.getName());
			consult.setPreviousWin(m.getMinorYearMinInterval());
			consult.setFollowingWin(m.getMajorYearMinInterval());
			
			consultMinMax.getMin().add(consult);
		});
		
		maxs.forEach(m -> {
			
			ConsultDto consult = new ConsultDto();
			consult.setInterval(m.getMaxInterval());
			consult.setProducer(m.getName());
			consult.setPreviousWin(m.getMinorYearMaxInterval());
			consult.setFollowingWin(m.getMajorYearMaxInterval());
			
			consultMinMax.getMax().add(consult);
		});
		
		return consultMinMax; 
			
	}

}
