package org.kamla.kapoor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Header {
	

	
	public  byte[] getHeaderbytes(List<Pattern> patternQueue){
		
		
		short numberOfPatterns =  (short)patternQueue.size();
		byte higherByte = 0;
		byte lowerByte = 0;
		
		byte[] headerBytes = new byte[(numberOfPatterns*2)+2];

		

		
		headerBytes[0] = ((byte)(numberOfPatterns>>8));
		headerBytes[1] = ((byte)(numberOfPatterns));
		
		Iterator<Pattern> queueIterator =  patternQueue.iterator();
		int index =1;
		while(queueIterator.hasNext()){
			Pattern queuePattern = queueIterator.next();
			
			
			
			if(queuePattern.getLeftRightPattern()==LeftRightPattern.simplePattern){
				higherByte = (byte)queuePattern.getExistingPattern();
				higherByte =  (byte) (higherByte & ~(1<<7));
				lowerByte = (byte)queuePattern.getReplacePattern();
				lowerByte =  (byte) (higherByte & ~(1<<7));
			}
			else
			if(queuePattern.getLeftRightPattern()==LeftRightPattern.leftPattern){
				higherByte = (byte)queuePattern.getExistingPattern();
				higherByte =  (byte) (higherByte & ~(1<<7));
				lowerByte = (byte)queuePattern.getReplacePattern();
				lowerByte =  (byte) (higherByte | (1<<7));
			}
			else
			if(queuePattern.getLeftRightPattern()==LeftRightPattern.rightPattern){
				higherByte = (byte)queuePattern.getExistingPattern();
				higherByte =  (byte) (higherByte | (1<<7));
				lowerByte = (byte)queuePattern.getReplacePattern();
				lowerByte =  (byte) (higherByte & ~(1<<7));
			}
			else{
				higherByte = (byte)queuePattern.getExistingPattern();
				higherByte =  (byte) (higherByte | (1<<7));
				lowerByte = (byte)queuePattern.getReplacePattern();
				lowerByte =  (byte) (higherByte | (1<<7));
			}
			
			
			headerBytes[++index] = higherByte;
			headerBytes[++index] = lowerByte;
			
		}
		
		return headerBytes;
	}
	


}
