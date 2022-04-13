package br.com.pedro.indicados.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pedro.indicados.models.Studio;

public interface StudioRepository extends JpaRepository<Studio, Long> { 
	Optional<Studio> findByName(String name);
}