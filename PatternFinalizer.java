package org.kamla.kapoor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class PatternFinalizer {

	private ZeroPattern zeroPattern;

	private List<Pattern> patternQueue;
	
	private Map<String ,Integer> dictionary ;
	

	public PatternFinalizer(ZeroPattern zeroPattern) {
		this.zeroPattern = zeroPattern;
		this.patternQueue  = new LinkedList<Pattern>();
		this.dictionary = new HashMap<String,Integer>();
		this.getPattern();
		
	}

	private Set<Long> returnKeySet() {
		Set<Long> keySet = new TreeSet<Long>();

		keySet.addAll(zeroPattern.getSimplePattern().keySet());
		keySet.addAll(zeroPattern.getLeftPattern().keySet());
		keySet.addAll(zeroPattern.getRightPattern().keySet());
		keySet.addAll(zeroPattern.getBothPattern().keySet());

		return keySet;
	}

	private LeftRightPattern getMaxLoss(long leftLoss, long rightLoss, long bothLoss, long simpleLoss) {

		if (leftLoss >= rightLoss && leftLoss >= bothLoss && leftLoss >= simpleLoss) {
			LeftRightPattern.leftPattern.setMaxLoss(leftLoss);
			return LeftRightPattern.leftPattern;
		} else if (rightLoss >= leftLoss && rightLoss >= bothLoss && rightLoss >= simpleLoss) {
			LeftRightPattern.rightPattern.setMaxLoss(rightLoss);
			return LeftRightPattern.rightPattern;
		} else if (bothLoss >= leftLoss && bothLoss >= rightLoss && bothLoss >= simpleLoss) {
			LeftRightPattern.bothPattern.setMaxLoss(bothLoss);
			return LeftRightPattern.bothPattern;
		}

		LeftRightPattern.simplePattern.setMaxLoss(simpleLoss);
		return LeftRightPattern.simplePattern;

	}

	private LeftRightPattern getMaxSaving(long leftSavings, long rightSavings, long bothSavings, long simpleSavings) {

		if (leftSavings >= rightSavings && leftSavings >= bothSavings && leftSavings >= simpleSavings) {
			LeftRightPattern.leftPattern.setMaxSaving(leftSavings);
			return LeftRightPattern.leftPattern;
		} else if (rightSavings >= leftSavings && rightSavings >= bothSavings && rightSavings >= simpleSavings) {
			LeftRightPattern.rightPattern.setMaxSaving(rightSavings);
			return LeftRightPattern.rightPattern;
		} else if (bothSavings >= leftSavings && bothSavings >= rightSavings && bothSavings >= simpleSavings) {
			LeftRightPattern.bothPattern.setMaxSaving(bothSavings);
			return LeftRightPattern.bothPattern;
		}

		LeftRightPattern.simplePattern.setMaxSaving(simpleSavings);
		return LeftRightPattern.simplePattern;

	}

	private void getPattern() {

		long maximumSavingSimplePattern = 0;
		long maximumSavingSimpleKey = 0;
		long maximumLossSimplePattern = 0;
		long maximumLossSimpleKey = 0;
		long maximumSavingLeftPattern = 0;
		long maximumLossLeftPattern = 0;
		long maximumSavingLeftKey = 0;
		long maximumLossLeftKey = 0;
		long maximumSavingRightPattern = 0;
		long maximumLossRightPattern = 0;
		long maximumSavingRightKey = 0;
		long maximumLossRightKey = 0;
		long maximumSavingBothPattern = 0;
		long maximumLossBothPattern = 0;
		long maximumSavingBothKey = 0;
		long maximumLossBothKey = 0;
		Long key;
		long value = 0;
		long loss = 0;
		long saving = 0;
		Set<Long> keySet = returnKeySet();
		Pattern patternCode;

		boolean patternFinalized = false;

		while (!patternFinalized) {

			maximumSavingSimplePattern = 0;
			maximumSavingSimpleKey = 0;
			maximumLossSimplePattern = 0;
			maximumLossSimpleKey = 0;
			maximumSavingLeftPattern = 0;
			maximumLossLeftPattern = 0;
			maximumSavingLeftKey = 0;
			maximumLossLeftKey = 0;
			maximumSavingRightPattern = 0;
			maximumLossRightPattern = 0;
			maximumSavingRightKey = 0;
			maximumLossRightKey = 0;
			maximumSavingBothPattern = 0;
			maximumLossBothPattern = 0;
			maximumSavingBothKey = 0;
			maximumLossBothKey = 0;
			patternFinalized = true;

			Iterator keySetIterator = keySet.iterator();
			while (keySetIterator.hasNext()) {

				key = (Long) keySetIterator.next();
				value = zeroPattern.getSimplePattern().get(key) != null ? zeroPattern.getSimplePattern().get(key) : 0;
				if (value > 0) {
					patternFinalized = false;

					if (key <= patternQueue.size() + 1) {
						loss = (patternQueue.size() + 2 - key) * value;
						if (loss > maximumLossSimplePattern) {
							maximumLossSimplePattern = loss;
							maximumLossSimpleKey = key;
						}
					} else {
						saving = (key - patternQueue.size() - 1) * value;
						if (saving > maximumSavingSimplePattern) {
							maximumSavingSimplePattern = saving;
							maximumSavingSimpleKey = key;
						}
					}
				}

				value = zeroPattern.getLeftPattern().get(key) != null ? zeroPattern.getLeftPattern().get(key) : 0;

				if (value > 0) {
					patternFinalized = false;

					if (key + 1 <= patternQueue.size() + 1) {
						loss = (patternQueue.size() + 2 - (key+1)) * value;
						if (loss > maximumLossLeftPattern) {
							maximumLossLeftPattern = loss;
							maximumLossLeftKey = key;
						}
					} else {
						saving = (key - patternQueue.size()) * value;
						if (saving > maximumSavingSimplePattern) {
							maximumSavingLeftPattern = saving;
							maximumSavingLeftKey = key;
						}
					}
				}

				value = zeroPattern.getRightPattern().get(key) != null ? zeroPattern.getRightPattern().get(key) : 0;

				if (value > 0) {
					patternFinalized = false;

					if (key + 1 <= patternQueue.size() + 1) {
						loss = (patternQueue.size() + 2 - (key+1)) * value;
						if (loss > maximumLossRightPattern) {
							maximumLossRightPattern = loss;
							maximumLossRightKey = key;
						}
					} else {
						saving = (key - patternQueue.size()) * value;
						if (saving > maximumSavingRightPattern) {
							maximumSavingRightPattern = saving;
							maximumSavingRightKey = key;
						}
					}
				}
				value = zeroPattern.getBothPattern().get(key) != null ? zeroPattern.getBothPattern().get(key) : 0;

				if (value > 0) {
					patternFinalized = false;

					if (key + 2 <= patternQueue.size() + 1) {
						loss = (patternQueue.size() + 2 - (key+2)) * value;
						if (loss > maximumLossBothPattern) {
							maximumLossBothPattern = loss;
							maximumLossBothKey = key;
						}
					} else {
						saving = (key - patternQueue.size() + 1) * value;
						if (saving > maximumSavingBothPattern) {
							maximumSavingBothPattern = saving;
							maximumSavingBothKey = key;
						}
					}
				}

			} // iterator.hasNext
			if (patternFinalized) {
				break;
			}

			LeftRightPattern leftRightLossPattern = getMaxLoss(maximumLossLeftPattern, maximumLossRightPattern,
					maximumLossBothPattern, maximumLossSimplePattern);
			LeftRightPattern leftRightSavingPattern = getMaxSaving(maximumSavingLeftPattern, maximumSavingRightPattern,
					maximumSavingBothPattern, maximumSavingSimplePattern);

			if (leftRightSavingPattern.getMaxSaving() >= leftRightLossPattern.getMaxLoss()) {
				patternCode = new Pattern();
				patternCode.setReplacePattern(patternQueue.size() + 1);
				patternCode.setLeftRightPattern(leftRightSavingPattern);

				
				if (leftRightSavingPattern == LeftRightPattern.leftPattern) {

					patternCode.setExistingPattern(maximumSavingLeftKey);
					patternCode.setCount(zeroPattern.getLeftPattern().get(maximumSavingLeftKey));
					zeroPattern.getLeftPattern().put(maximumSavingLeftKey, 0L);
					dictionary.put(maximumSavingLeftKey+"L", patternQueue.size() + 1);


				} else if (leftRightSavingPattern == LeftRightPattern.rightPattern) {

					patternCode.setExistingPattern(maximumSavingRightKey);
					patternCode.setCount(zeroPattern.getRightPattern().get(maximumSavingRightKey));
					zeroPattern.getRightPattern().put(maximumSavingRightKey, 0L);
					dictionary.put(maximumSavingRightKey+"R", patternQueue.size() + 1);
				} else if (leftRightSavingPattern == LeftRightPattern.bothPattern) {

					patternCode.setExistingPattern(maximumSavingBothKey);
					patternCode.setCount(zeroPattern.getBothPattern().get(maximumSavingBothKey));
					zeroPattern.getBothPattern().put(maximumSavingBothKey, 0L);
					dictionary.put(maximumSavingBothKey+"B", patternQueue.size() + 1);
				} else {

					patternCode.setExistingPattern(maximumSavingSimpleKey);
					patternCode.setCount(zeroPattern.getSimplePattern().get(maximumSavingSimpleKey));
					zeroPattern.getSimplePattern().put(maximumSavingSimpleKey, 0L);
					dictionary.put(maximumSavingSimpleKey+"S", patternQueue.size() + 1);

				}

				patternQueue.add(patternCode);
	//			keySet.remove(patternCode.getExistingPattern());

			} else {
				patternCode = new Pattern();
				patternCode.setReplacePattern(patternQueue.size() + 1);
				patternCode.setLeftRightPattern(leftRightLossPattern);

				if (leftRightLossPattern == LeftRightPattern.leftPattern) {

					patternCode.setExistingPattern(maximumLossLeftKey);
					patternCode.setCount(zeroPattern.getLeftPattern().get(maximumLossLeftKey));
					zeroPattern.getLeftPattern().put(maximumLossLeftKey, 0L);
					dictionary.put(maximumLossLeftKey+"L", patternQueue.size() + 1);

				} else if (leftRightLossPattern == LeftRightPattern.rightPattern) {

					patternCode.setExistingPattern(maximumLossRightKey);
					patternCode.setCount(zeroPattern.getRightPattern().get(maximumLossRightKey));
					zeroPattern.getRightPattern().put(maximumLossRightKey, 0L);
					dictionary.put(maximumLossRightKey+"R", patternQueue.size() + 1);

				} else if (leftRightLossPattern == LeftRightPattern.bothPattern) {

					patternCode.setExistingPattern(maximumLossBothKey);
					patternCode.setCount(zeroPattern.getBothPattern().get(maximumLossBothKey));
					zeroPattern.getBothPattern().put(maximumLossBothKey, 0L);
					dictionary.put(maximumLossBothKey+"B", patternQueue.size() + 1);

				} else {

					patternCode.setExistingPattern(maximumLossSimpleKey);
					patternCode.setCount(zeroPattern.getSimplePattern().get(maximumLossSimpleKey));
					zeroPattern.getSimplePattern().put(maximumLossSimpleKey, 0L);
					dictionary.put(maximumLossSimpleKey+"S", patternQueue.size() + 1);

				}

				patternQueue.add(patternCode);
	//			keySet.remove(patternCode.getExistingPattern());

			}

		}


	}

	public List<Pattern> getPatternQueue() {
		return patternQueue;
	}

	public Map<String, Integer> getDictionary() {
		return dictionary;
	}

}
