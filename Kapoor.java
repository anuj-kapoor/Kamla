package org.kamla.kapoor;

import java.io.FileInputStream;
import java.io.IOException;

public class Kapoor {

	public static void main(String[] args) throws IOException {

		byte[] buffer = new byte[2];
//		int nRead = 0;
//		int byteIndex = 0;
//		long readByte = 0;
//		int countOf0 = 0;
//		Pattern patternCode = null;
//		int maximumSavingSimplePattern = 0;
//		int maximumSavingSimpleIndex = 0;
//		int maximumLossSimplePattern = 0;
//		int maximumLossSimpleIndex = 0;
//		int maximumSavingBothPattern = 0;
//		int maximumSavingBothIndex = 0;
//		int maximumLossBothPattern = 0;
//		int maximumLossBothIndex = 0;
//		int maximumSavingRightPattern = 0;
//		int maximumSavingRightIndex = 0;
//		int maximumLossRightPattern = 0;
//		int maximumLossRightIndex = 0;
//		int maximumSavingLeftPattern = 0;
//		int maximumSavingLeftIndex = 0;
//		int maximumLossLeftPattern = 0;
//		int maximumLossLeftIndex = 0;
//		int savings = 0;
//		int inputbytecount=0;
//		boolean patternFinalized = false;
//		LinkedList<Pattern> patternQueue = new LinkedList<Pattern>();
//		int PATTERN_MAX_SIZE = 5000;
//
//		int[] simplePattern = new int[PATTERN_MAX_SIZE];
//		int[] leftPattern = new int[PATTERN_MAX_SIZE];
//		int[] rightPattern = new int[PATTERN_MAX_SIZE];
//		int[] bothPattern = new int[PATTERN_MAX_SIZE];
//
//		boolean currenBitSet = false;
//		boolean leftSet = false;
//		boolean rightSet = false;
//		boolean bothSet = false;
//		
		

		FileInputStream inputStream = new FileInputStream("C:\\Users\\anuj\\OneDrive\\Documents\\input.dat");
		PatternFinder patternFinder = new PatternFinder(inputStream,1000);
		ZeroPattern zeroPattern = patternFinder.getZeroPattern();
		inputStream.close();
		
		PatternFinalizer patternFinalizer = new PatternFinalizer(zeroPattern);
		
		
		

//		while ((nRead = inputStream.read(buffer)) != -1) {
//			inputbytecount+=nRead*8;
//			byteIndex = 0;
//			while (byteIndex < nRead) {
//
//				readByte = buffer[byteIndex];
//				
//
//				for (int bitIndex = 7; bitIndex >= 0; bitIndex--) {
//
//					if ((readByte & 1 << bitIndex) == 0) {
//						if(countOf0==0 && currenBitSet){
//							leftSet = true;
//						}
//						countOf0++;
//						currenBitSet = false;
//
//					} else {
//						currenBitSet=true;
//						if (countOf0 > 0) {
//							rightSet = true;
//							if(rightSet && leftSet){
//								bothPattern[countOf0]++;
//							}
//							else if(leftSet){
//								leftPattern[countOf0]++;
//							}
//							else if(rightSet){
//								rightPattern[countOf0]++;
//							}
//							
//							rightSet = false;
//							leftSet = false;
//							currenBitSet=false;
//							
//							countOf0 = 0;
//						}
//					}
//
//
//
//				}
//
//				byteIndex++;
//
//			}
//
//		}
//
//		inputStream.close();
//
////		while (!patternFinalized) {
////
////			patternFinalized = true;
////			maximumSavingSimplePattern = 0;
////			maximumLossSimplePattern = 0;
////			maximumSavingLeftPattern = 0;
////			maximumLossLeftPattern = 0;
////			maximumSavingRightPattern = 0;
////			maximumLossRightPattern = 0;
////			maximumSavingBothPattern = 0;
////			maximumLossBothPattern = 0;
////			for (int patternIndex = 1; patternIndex < PATTERN_MAX_SIZE; patternIndex++) {
////
////				if (leftPattern[patternIndex] > 0) {
////					patternFinalized = false;
////
////					if (patternIndex > patternQueue.size() + 1) {
////						if ((patternIndex - patternQueue.size() - 1) * leftPattern[patternIndex] > maximumSavingLeftPattern) {
////							maximumSavingLeftIndex = patternIndex;
////							maximumSavingLeftPattern = (patternIndex  - patternQueue.size() ) * leftPattern[patternIndex];
////
////						}
////					}
////
////					if (patternIndex <= patternQueue.size() + 1) {
////						if ((patternQueue.size() + 2 - patternIndex) * leftPattern[patternIndex] >=maximumLossLeftPattern) {
////							maximumLossLeftPattern = (patternQueue.size() + 2 - patternIndex) * leftPattern[patternIndex];
////							maximumLossLeftIndex = patternIndex;
////						}
////					}
////
////				}
////				
////				if (rightPattern[patternIndex] > 0) {
////					patternFinalized = false;
////
////					if (patternIndex > patternQueue.size() + 1) {
////						if ((patternIndex - patternQueue.size() - 1) * rightPattern[patternIndex] > maximumSavingRightPattern) {
////							maximumSavingRightIndex = patternIndex;
////							maximumSavingRightPattern = (patternIndex  - patternQueue.size() ) * rightPattern[patternIndex];
////
////						}
////					}
////
////					if (patternIndex <= patternQueue.size() + 1) {
////						if ((patternQueue.size() + 2 - patternIndex) * rightPattern[patternIndex] >=maximumLossRightPattern) {
////							maximumLossRightPattern = (patternQueue.size() + 2 - patternIndex) * rightPattern[patternIndex];
////							maximumLossRightIndex = patternIndex;
////						}
////					}
////
////				}
////
////				if (bothPattern[patternIndex] > 0) {
////					patternFinalized = false;
////
////					if (patternIndex > patternQueue.size() + 1) {
////						if ((patternIndex - patternQueue.size() - 1) * bothPattern[patternIndex] > maximumSavingBothPattern) {
////							maximumSavingBothIndex = patternIndex;
////							maximumSavingBothPattern = (patternIndex + 1  - patternQueue.size() ) * bothPattern[patternIndex];
////
////						}
////					}
////
////					if (patternIndex <= patternQueue.size() + 1) {
////						if ((patternQueue.size() + 2 - patternIndex) * bothPattern[patternIndex] >=maximumLossBothPattern) {
////							maximumLossBothPattern = (patternQueue.size() + 2 - patternIndex) * bothPattern[patternIndex];
////							maximumLossBothIndex = patternIndex;
////						}
////					}
////
////				}
////
////				
////			}
////			if (patternFinalized) {
////				break;
////			}
////
////			LeftRightPattern leftRightSavingPattern = getMaxSaving(maximumSavingLeftPattern,maximumSavingRightPattern,maximumSavingBothPattern);
////			
////			LeftRightPattern leftRightLossPattern = getMaxLoss(maximumLossLeftPattern,maximumLossRightPattern,maximumLossBothPattern);
////
////
////			if (leftRightSavingPattern.getMaxSaving() > leftRightLossPattern.getMaxLoss()) {
////				patternCode = new Pattern();
////				patternCode.setReplacePattern(patternQueue.size() + 1);
////				patternCode.setLeftRightPattern(leftRightSavingPattern);
////				
////				if(leftRightSavingPattern == LeftRightPattern.leftPattern){
////					
////					patternCode.setExistingPattern(maximumSavingLeftIndex);
////					patternCode.setCount(leftPattern[maximumSavingLeftIndex]);
////					savings += maximumSavingLeftPattern;
////					leftPattern[maximumSavingLeftIndex] = 0;
////					
////				}
////				else if(leftRightSavingPattern == LeftRightPattern.rightPattern){
////				
////					patternCode.setExistingPattern(maximumSavingRightIndex);
////					patternCode.setCount(rightPattern[maximumSavingRightIndex]);
////					savings += maximumSavingRightPattern;
////					rightPattern[maximumSavingRightIndex] = 0;
////				}
////				else{
////					
////					patternCode.setExistingPattern(maximumSavingBothIndex);
////					patternCode.setCount(bothPattern[maximumSavingBothIndex]);
////					savings += maximumSavingBothPattern;
////					bothPattern[maximumSavingBothIndex] = 0;
////					
////				}
////
////				patternQueue.add(patternCode);
////				
////			} else {
////				patternCode = new Pattern();
////				patternCode.setReplacePattern(patternQueue.size() + 1);
////				patternCode.setLeftRightPattern(leftRightLossPattern);
////				
////				if(leftRightLossPattern == LeftRightPattern.leftPattern){
////	
////					patternCode.setExistingPattern(maximumLossLeftIndex);
////					patternCode.setCount(leftPattern[maximumLossLeftIndex]);
////					savings -= maximumLossLeftPattern;
////					leftPattern[maximumLossLeftIndex] = 0;
////				}
////				else if(leftRightLossPattern == LeftRightPattern.rightPattern){
////					
////					patternCode.setExistingPattern(maximumLossRightIndex);
////					patternCode.setCount(rightPattern[maximumLossRightIndex]);
////					savings -= maximumLossRightPattern;
////					rightPattern[maximumLossRightIndex] = 0;
////					
////				}
////				else{
////
////					
////					patternCode.setExistingPattern(maximumLossBothIndex);
////					patternCode.setCount(bothPattern[maximumLossBothIndex]);
////					savings -= maximumLossBothPattern;
////					bothPattern[maximumLossBothIndex] = 0;
////					
////				}
////				
////
////				patternQueue.add(patternCode);
////
////
////			}
////
////		}
////
////		
////		System.out.println("inputByte:"+ inputbytecount);
////		Iterator it = patternQueue.iterator();
////		int newSum = 0;
////		int oldSum = 0;
////		while(it.hasNext()){
////			Pattern p =(Pattern)it.next();
////			newSum += p.getCount()*(p.getReplacePattern()-2);
////			oldSum += p.getCount()*p.getExistingPattern();
////		}
////		savings = oldSum-newSum;
////		System.out.println("old Sum:"+ oldSum);
////		System.out.println("new Sum:"+ newSum);
////		System.out.println("savings "+ savings);
////		for(Pattern pm:patternQueue ){
////			System.out.print("Existing pattern "+pm.getExistingPattern());
////			System.out.println("New pattern "+pm.getReplacePattern());
////			System.out.println("count "+pm.getCount());
////		}
//
//	}
//	
//	public static LeftRightPattern getMaxSaving(int leftSavings, int rightSavings, int bothSavings){
//		
//		if(leftSavings>=rightSavings && leftSavings>=bothSavings){
//			LeftRightPattern.leftPattern.setMaxSaving(leftSavings);
//			return LeftRightPattern.leftPattern;
//		}
//		else if(rightSavings>=leftSavings && rightSavings>=bothSavings){
//			LeftRightPattern.rightPattern.setMaxSaving(rightSavings);
//			return LeftRightPattern.rightPattern;
//		}
//		else if(bothSavings>=leftSavings && bothSavings>=rightSavings){
//			LeftRightPattern.bothPattern.setMaxSaving(bothSavings);
//			return LeftRightPattern.bothPattern;
//		}
//		
//		LeftRightPattern.bothPattern.setMaxSaving(bothSavings);
//		return LeftRightPattern.bothPattern;
//		
//	}
//
//	public static LeftRightPattern getMaxLoss(int leftLoss, int rightLoss, int bothLoss){
//		
//		if(leftLoss>=rightLoss && leftLoss>=bothLoss){
//			LeftRightPattern.leftPattern.setMaxLoss(leftLoss);
//			return LeftRightPattern.leftPattern;
//		}
//		else if(rightLoss>=leftLoss && rightLoss>=bothLoss){
//			LeftRightPattern.rightPattern.setMaxLoss(rightLoss);
//			return LeftRightPattern.rightPattern;
//		}
//		else if(bothLoss>=leftLoss && bothLoss>=rightLoss){
//			LeftRightPattern.bothPattern.setMaxLoss(bothLoss);
//			return LeftRightPattern.bothPattern;
//		}
//		
//		LeftRightPattern.bothPattern.setMaxLoss(bothLoss);
//		return LeftRightPattern.bothPattern;
//		
//	}
//	
	}
	
}
