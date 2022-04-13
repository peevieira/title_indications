package br.com.pedro.indicados.config.validation.form;

public class ErrorForm {
	
	private String field;
	private String error;
	
	public ErrorForm(String field, String error) {
		this.field = field;
		this.error = error;
	}

	public String getField() {
		return field;
	}

	public String getError() {
		return error;
	}	
	
}
