package org.kamla.kapoor;

public enum LeftRightPattern {
	
	leftPattern,rightPattern,bothPattern,simplePattern;
	
	long maxSaving;
	long maxLoss;



	public long getMaxLoss() {
		return maxLoss;
	}

	public void setMaxLoss(long maxLoss) {
		this.maxLoss = maxLoss;
	}

	public long getMaxSaving() {
		return maxSaving;
	}

	public void setMaxSaving(long maxSaving) {
		this.maxSaving = maxSaving;
	}

}
