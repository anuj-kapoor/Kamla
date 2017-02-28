package org.kamla.kapoor;

public enum LeftRightPattern {
	
	leftPattern,rightPattern,bothPattern,simplePattern;
	
	int maxSaving;
	int maxLoss;



	public int getMaxLoss() {
		return maxLoss;
	}

	public void setMaxLoss(int maxLoss) {
		this.maxLoss = maxLoss;
	}

	public int getMaxSaving() {
		return maxSaving;
	}

	public void setMaxSaving(int maxSaving) {
		this.maxSaving = maxSaving;
	}

}
