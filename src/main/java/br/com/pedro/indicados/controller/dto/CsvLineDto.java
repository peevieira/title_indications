package br.com.pedro.indicados.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.pedro.indicados.models.CsvLine;

public class CsvLineDto {
	
	private Integer year;	
	private String title;	
	private String studio;	
	private String producers;	
	private String winner;
	private String error;
	
	public CsvLineDto(CsvLine line) {		
		this.year = line.getYear();
		this.title = line.getTitle();
		this.studio = line.getStudio();
		this.producers = line.getProducers();
		this.winner = line.getWinner();
		this.error = line.getError();
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}

	public String getProducers() {
		return producers;
	}

	public void setProducers(String producers) {
		this.producers = producers;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public static List<CsvLineDto> converter(List<CsvLine> lines) {
		return lines.stream().map(CsvLineDto::new).collect(Collectors.toList());
	}
	
}
