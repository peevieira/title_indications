package br.com.pedro.indicados.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.pedro.indicados.models.Title;

@RunWith(SpringRunner.class)
@DataJpaTest
class TitleRepositoryTest {
	
	@Autowired
	private TitleRepository titleRepository;
	
	@Autowired
	private StudioRepository studioRepository;
	
	@Autowired
	private ProducerRepository producerRepository;
	
	@Test
	void testFindByName1() {			
		Assert.assertTrue(titleRepository.findByName("Title 1").get().getName().equals("Title 1"));		
	}
	
	@Test
	void testFindByName2() {			
		Assert.assertTrue(titleRepository.findByName("Title 2").get().getName().equals("Title 2"));		
	}
	
	@Test
	void testSave() {		
		Title tl = new Title();
		tl.setName("Title test");
		tl.setStudio(studioRepository.findById((long) 3).get());
		tl.setProducers(producerRepository.findAll());		
		titleRepository.save(tl);
		Assert.assertTrue(tl.getId() > 0);		
	}
	
	@Test
	void testDelete() {		
		titleRepository.deleteById((long) 1);	
		Assert.assertTrue(!titleRepository.findById((long) 1).isPresent());		
	}
	
	@Test
	void testUpdate() {		
		Title tl = titleRepository.findById((long) 2).get();
		tl.setName("Title Alt");		
		Assert.assertTrue(titleRepository.findByName("Title Alt").isPresent());			
	}

}
