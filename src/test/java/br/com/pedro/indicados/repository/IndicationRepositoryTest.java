package br.com.pedro.indicados.repository;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.pedro.indicados.models.Indication;
import br.com.pedro.indicados.models.ProducerIndication;

@RunWith(SpringRunner.class)
@DataJpaTest
class IndicationRepositoryTest {
	
	@Autowired
	private IndicationRepository indicatorRepository; 
	
	@Autowired
	private TitleRepository titleRepository;

	@Test
	void testFindTitleIndicationByYear1() {		
		Indication ind = indicatorRepository.findTitleIndicationByYear("Title 1", 1990).get();
		Assert.assertNotNull(ind);
		Assert.assertEquals("Title 1", ind.getTitle().getName());
		Assert.assertTrue(1990 == ind.getYear());
	}
	
	@Test
	void testFindTitleIndicationByYear2() {		
		Indication ind = indicatorRepository.findTitleIndicationByYear("Title 5", 1999).get();
		Assert.assertNotNull(ind);
		Assert.assertEquals("Title 5", ind.getTitle().getName());
		Assert.assertTrue(1999 == ind.getYear());
	}
	
	@Test
	void testFindTitleIndicationByYear3() {		
		Optional<Indication> ind = indicatorRepository.findTitleIndicationByYear("Title 8", 1990);
		Assert.assertTrue(!ind.isPresent());
	}
	
	@Test
	void testFindTitleWinnerByYear1() {		
		List<Indication> inds = indicatorRepository.findTitleWinnerByYear(1990);
		Assert.assertTrue(inds.get(0).getTitle().getName().equals("Title 1"));
	}
	
	@Test
	void testFindTitleWinnerByYear2() {		
		List<Indication> inds = indicatorRepository.findTitleWinnerByYear(1999);
		Assert.assertTrue(inds.get(0).getTitle().getName().equals("Title 5"));
	}
	
	@Test
	void testFindProducerIndication1() {		
		List<ProducerIndication> inds = indicatorRepository.findProducerIndication();
		Assert.assertTrue(inds.get(0).getName().equals("Producer 3"));
		Assert.assertTrue(inds.get(1).getName().equals("Producer 5"));
		Assert.assertTrue(inds.size() == 2);
	}
	
	@Test
	void testSave() {		
		Indication ind = new Indication();		
		ind.setTitle(titleRepository.findById((long) 1).get());
		ind.setWinner(1);
		ind.setYear(2020);
		indicatorRepository.save(ind);
		Assert.assertTrue(ind.getId() > 0);		
	}
	
	@Test
	void testDelete() {		
		indicatorRepository.deleteById((long) 1);	
		Assert.assertTrue(!indicatorRepository.findById((long) 1).isPresent());		
	}
	
	@Test
	void testUpdate() {		
		Indication ind = indicatorRepository.findById((long) 2).get();
		ind.setYear(2050);
		indicatorRepository.save(ind);
		
		ind = indicatorRepository.findById((long) 2).get();
		
		Assert.assertTrue(ind.getYear() == 2050);		
	}

}
