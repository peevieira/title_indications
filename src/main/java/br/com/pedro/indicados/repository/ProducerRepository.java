package br.com.pedro.indicados.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pedro.indicados.models.Producer;

public interface ProducerRepository extends JpaRepository<Producer, Long> { 
	Optional<Producer> findByName(String name);
}