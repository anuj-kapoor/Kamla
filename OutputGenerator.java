package org.kamla.kapoor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class OutputGenerator {
	
	private String outputFile;
	
	private PatternFinalizer patternFinalizer;
	
	
	
	
	public OutputGenerator(String inputFileName, PatternFinalizer patternFinalizer) throws IOException{
		this.outputFile = inputFileName.split("[.]")[0]+".kml";
		this.patternFinalizer = patternFinalizer;	
		this.writeHeader();
	}
	
	
	
	private void writeHeader() throws IOException{

		
		
		short numberOfPatterns =  (short)patternFinalizer.getPatternQueue().size();
		byte higherByte = 0;
		byte lowerByte = 0;
		
		byte[] headerBytes = new byte[(numberOfPatterns*2)+2];
		
		FileOutputStream fileOutputStream = new FileOutputStream(outputFile);

		

		
		headerBytes[0] = ((byte)(numberOfPatterns>>8));
		headerBytes[1] = ((byte)(numberOfPatterns));
		
		Iterator<Pattern> queueIterator =  patternFinalizer.getPatternQueue().iterator();
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
		
	
		fileOutputStream.write(headerBytes);
		fileOutputStream.close();
		
		
	}

}
