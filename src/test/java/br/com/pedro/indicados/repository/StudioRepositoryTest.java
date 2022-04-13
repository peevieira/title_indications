package br.com.pedro.indicados.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.pedro.indicados.models.Studio;

@RunWith(SpringRunner.class)
@DataJpaTest
class StudioRepositoryTest {

	@Autowired
	private StudioRepository studioRepository;
	
	@Test
	void testFindByName1() {			
		Assert.assertTrue(studioRepository.findByName("Studio 1").get().getName().equals("Studio 1"));		
	}
	
	@Test
	void testFindByName2() {			
		Assert.assertTrue(studioRepository.findByName("Studio 2").get().getName().equals("Studio 2"));		
	}
	
	@Test
	void testSave() {		
		Studio std = new Studio();
		std.setName("Studio test");		
		studioRepository.save(std);
		Assert.assertTrue(std.getId() > 0);		
	}
	
	@Test
	void testDelete() {		
		studioRepository.deleteById((long) 1);	
		Assert.assertTrue(!studioRepository.findById((long) 1).isPresent());		
	}
	
	@Test
	void testUpdate() {		
		Studio std = studioRepository.findById((long) 2).get();
		std.setName("Studio Alt");		
		Assert.assertTrue(studioRepository.findByName("Studio Alt").isPresent());			
	}

}
