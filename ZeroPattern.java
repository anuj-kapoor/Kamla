package org.kamla.kapoor;

import java.util.HashMap;
import java.util.Map;

public class ZeroPattern {
	
	
	
	private Map<Integer,Integer> leftPattern ;
	private Map<Integer,Integer>  rightPattern ;
	private Map<Integer,Integer>  bothPattern ;
	private Map<Integer,Integer>  simplePattern ;

	
	
	public ZeroPattern(){	
		
		leftPattern  = new HashMap<Integer,Integer>();
		rightPattern = new HashMap<Integer,Integer>();
		bothPattern = new HashMap<Integer,Integer>();
		simplePattern = new HashMap<Integer,Integer>();
	}
	

	public Map<Integer, Integer> getSimplePattern() {
		return simplePattern;
	}




	public void setSimplePattern(Map<Integer, Integer> simplePattern) {
		this.simplePattern = simplePattern;
	}

	
	public Map<Integer, Integer> getLeftPattern() {
		return leftPattern;
	}




	public void setLeftPattern(Map<Integer, Integer> leftPattern) {
		this.leftPattern = leftPattern;
	}




	public Map<Integer, Integer> getRightPattern() {
		return rightPattern;
	}




	public void setRightPattern(Map<Integer, Integer> rightPattern) {
		this.rightPattern = rightPattern;
	}




	public Map<Integer, Integer> getBothPattern() {
		return bothPattern;
	}




	public void setBothPattern(Map<Integer, Integer> bothPattern) {
		this.bothPattern = bothPattern;
	}







	
	
}
