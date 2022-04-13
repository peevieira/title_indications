package br.com.pedro.indicados.controller.form;

import javax.validation.constraints.NotNull;

public class CsvFileForm {

	@NotNull
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}	
	
}
