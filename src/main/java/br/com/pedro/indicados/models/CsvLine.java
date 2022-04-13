package br.com.pedro.indicados.models;

import com.opencsv.bean.CsvBindByPosition;

public class CsvLine {
	
	@CsvBindByPosition(position = 0)
	private Integer year;
	@CsvBindByPosition(position = 1)
	private String title;
	@CsvBindByPosition(position = 2)
	private String studio;
	@CsvBindByPosition(position = 3)
	private String producers;
	@CsvBindByPosition(position = 4)
	private String winner;
	
	private String error;		

	public CsvLine() { }
		
	public CsvLine(Integer year, String title, String studio, String producers, String winner) {
		this.year = year;
		this.title = title;
		this.studio = studio;
		this.producers = producers;
		this.winner = winner;
	}

	@Override
	public String toString() {
		return "CsvLine [year=" + year + ", title=" + title + ", studio=" + studio + ", producers=" + producers
				+ ", winner=" + winner + "]";
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
	
}
