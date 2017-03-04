package org.kamla.kapoor;

import java.util.HashMap;
import java.util.Map;

public class ZeroPattern {
	
	
	
	private Map<Long,Long> leftPattern ;
	private Map<Long,Long>  rightPattern ;
	private Map<Long,Long>  bothPattern ;
	private Map<Long,Long>  simplePattern ;

	
	
	public ZeroPattern(){	
		
		leftPattern  = new HashMap<Long,Long>();
		rightPattern = new HashMap<Long,Long>();
		bothPattern = new HashMap<Long,Long>();
		simplePattern = new HashMap<Long,Long>();
	}
	

	public Map<Long, Long> getSimplePattern() {
		return simplePattern;
	}
	
	public Map<Long, Long> getLeftPattern() {
		return leftPattern;
	}

	public Map<Long, Long> getRightPattern() {
		return rightPattern;
	}

	public Map<Long, Long> getBothPattern() {
		return bothPattern;
	}
}
