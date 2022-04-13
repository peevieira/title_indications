package br.com.pedro.indicados.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pedro.indicados.models.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> { }