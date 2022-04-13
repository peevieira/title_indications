package br.com.pedro.indicados.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.pedro.indicados.models.Producer;

@RunWith(SpringRunner.class)
@DataJpaTest
class ProducerRepositoryTest {
	
	@Autowired
	private ProducerRepository producerRepository;

	@Test
	void testFindByName1() {			
		Assert.assertTrue(producerRepository.findByName("Producer 4").get().getName().equals("Producer 4"));		
	}
	
	@Test
	void testFindByName2() {			
		Assert.assertTrue(producerRepository.findByName("Producer 6").get().getName().equals("Producer 6"));		
	}
	
	@Test
	void testSave() {		
		Producer prod = new Producer();
		prod.setName("Producer teste");
		
		producerRepository.save(prod);
		Assert.assertTrue(prod.getId() > 0);		
	}
	
	@Test
	void testDelete() {		
		producerRepository.deleteById((long) 1);	
		Assert.assertTrue(!producerRepository.findById((long) 1).isPresent());		
	}
	
	@Test
	void testUpdate() {		
		Producer prod = producerRepository.findById((long) 2).get();
		prod.setName("Producer Alt");		
		Assert.assertTrue(producerRepository.findByName("Producer Alt").isPresent());			
	}

}
