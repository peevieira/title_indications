package br.com.pedro.indicados.controller.dto;

import java.util.ArrayList;
import java.util.List;

public class ConsultMaxMinDto {
	
	private List<ConsultDto> min = new ArrayList<ConsultDto>();
	private List<ConsultDto> max = new ArrayList<ConsultDto>();
	
	public List<ConsultDto> getMin() {
		return min;
	}
	
	public void setMin(List<ConsultDto> min) {
		this.min = min;
	}
	
	public List<ConsultDto> getMax() {
		return max;
	}
	
	public void setMax(List<ConsultDto> max) {
		this.max = max;
	}
	
}
