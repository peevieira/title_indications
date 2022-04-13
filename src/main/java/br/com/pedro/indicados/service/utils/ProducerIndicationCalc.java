package br.com.pedro.indicados.service.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProducerIndicationCalc {
	
	private String name;
	private List<Integer> yearsWin = new ArrayList<Integer>();
	
	private Integer minInterval = Integer.MAX_VALUE; 
	private Integer maxInterval = Integer.MIN_VALUE;
	
	private Integer minorYearMinInterval = 0;
	private Integer majorYearMinInterval = 0;
	
	private Integer minorYearMaxInterval = 0;
	private Integer majorYearMaxInterval = 0;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Integer> getYearsWin() {
		return yearsWin;
	}
	public void setYearsWin(List<Integer> yearsWin) {
		this.yearsWin = yearsWin;
	} 
	
	public Integer getMinInterval() {
		return minInterval;
	}
	
	public Integer getMaxInterval() {
		return maxInterval;
	}
	
	public Integer getMinorYearMinInterval() {
		return minorYearMinInterval;
	}
	
	public Integer getMajorYearMinInterval() {
		return majorYearMinInterval;
	}
	
	public Integer getMinorYearMaxInterval() {
		return minorYearMaxInterval;
	}
	
	public Integer getMajorYearMaxInterval() {
		return majorYearMaxInterval;
	}
	public void CalcInterval() {
		calcMinInterval();
		calcMaxInterval();
	}
	
	private void calcMinInterval() {
		yearsWin.sort(Comparator.naturalOrder());
		
		for (int i = 0; i < yearsWin.size(); i++) {
			
			Integer minoYear;
			Integer majorYear;
			Integer interval;
			
			if ((yearsWin.size() == 1) || (yearsWin.size()-1 == i)) {
				minoYear = yearsWin.get(i);
				majorYear = yearsWin.get(i);				
			} else {
				minoYear = yearsWin.get(i);
				majorYear = yearsWin.get(i+1);
			}
			
			interval = majorYear - minoYear;
			
			if ((interval < minInterval) && (interval != 0)) {
				minInterval = interval;
				minorYearMinInterval = minoYear;
				majorYearMinInterval = majorYear; 
			}	
			
		}
	}
	
	private void calcMaxInterval() {		
		yearsWin.sort(Comparator.naturalOrder());
		
		for (int i = 0; i < yearsWin.size(); i++) {
			
			Integer minoYear;
			Integer majorYear;
			Integer interval;
			
			if ((yearsWin.size() == 1) || (yearsWin.size()-1 == i)) {
				minoYear = yearsWin.get(i);
				majorYear = yearsWin.get(i);				
			} else {
				minoYear = yearsWin.get(i);
				majorYear = yearsWin.get(i+1);
			}
			
			interval = majorYear - minoYear;
			
			if ((interval > maxInterval) && (interval != 0)) {
				maxInterval = interval;
				minorYearMaxInterval = minoYear;
				majorYearMaxInterval = majorYear; 
			}	
			
		}
	}
	
	

}
