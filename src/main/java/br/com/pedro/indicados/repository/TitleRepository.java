package br.com.pedro.indicados.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pedro.indicados.models.Title;

public interface TitleRepository extends JpaRepository<Title, Long> { 
	Optional<Title> findByName(String name);
}