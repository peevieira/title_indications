package br.com.pedro.indicados.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.ColumnResult;
import javax.persistence.SqlResultSetMapping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.pedro.indicados.models.Indication;
import br.com.pedro.indicados.models.ProducerIndication;

public interface IndicationRepository extends JpaRepository<Indication, Long> { 	
	
	@Query(value = "SELECT I.* FROM INDICATION I JOIN TITLE T ON T.ID = I.TITLE_ID WHERE T.NAME = :NAME AND I.YEAR = :YEAR", nativeQuery = true)
	Optional<Indication> findTitleIndicationByYear(@Param("NAME") String name, @Param("YEAR") Integer year);
	
	@Query(value = "SELECT I.* FROM INDICATION I WHERE I.YEAR = :YEAR AND I.WINNER = 1", nativeQuery = true)
	List<Indication> findTitleWinnerByYear(@Param("YEAR") Integer year);
	
	@Query(value = "SELECT DISTINCT P.NAME, I.YEAR, I.WINNER FROM INDICATION I " + 
				   "JOIN TITLE T ON T.ID = I.TITLE_ID  " +
			       "JOIN TITLE_PRODUCERS TP ON TP.TITLE_ID = T.ID  " +
				   "JOIN PRODUCER P ON P.ID = TP.PRODUCERS_ID WHERE I.WINNER = 1", nativeQuery = true)
	List<ProducerIndication> findProducerIndication();
	
	
}