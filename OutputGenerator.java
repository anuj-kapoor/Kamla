package org.kamla.kapoor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class OutputGenerator {
	
	FileOutputStream fileOutputStream ;
	FileInputStream fileInputStream;
	private PatternFinalizer patternFinalizer;
	private BodyGenerator bodyGenerator;
	private final short OUTPUT_BUFFER_SIZE = 1000;
	private final short INPUT_BUFFER_SIZE = 1000;
	private byte[] outputBuffer;
	private short outputBufferIndex;
	
	
	
	
	public OutputGenerator(String inputFileName, PatternFinalizer patternFinalizer) throws IOException{
	
		this.fileOutputStream = new FileOutputStream(inputFileName.split("[.]")[0]+".kml");
		this.fileInputStream = new FileInputStream(inputFileName);
		this.patternFinalizer = patternFinalizer;	
		this.bodyGenerator = new BodyGenerator(patternFinalizer.getDictionary(),fileOutputStream,OUTPUT_BUFFER_SIZE);
		this.writeContent();

		fileOutputStream.flush();
		fileOutputStream.close();
		fileInputStream.close();
		
	}
	
	
	
	private void writeContent() throws IOException{

		
		
		short numberOfPatterns =  (short)patternFinalizer.getPatternQueue().size();
		byte higherByte = 0;
		byte lowerByte = 0;
		byte[] inputBuffer = new byte[INPUT_BUFFER_SIZE];
		short bytesRead;
		byte inputByte;
		
		byte[] headerBytes = new byte[(numberOfPatterns*2)+2];

		
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
				lowerByte =  (byte) (lowerByte & ~(1<<7));
			}
			else
			if(queuePattern.getLeftRightPattern()==LeftRightPattern.leftPattern){
				higherByte = (byte)queuePattern.getExistingPattern();
				higherByte =  (byte) (higherByte & ~(1<<7));
				lowerByte = (byte)queuePattern.getReplacePattern();
				lowerByte =  (byte) (lowerByte | (1<<7));
			}
			else
			if(queuePattern.getLeftRightPattern()==LeftRightPattern.rightPattern){
				higherByte = (byte)queuePattern.getExistingPattern();
				higherByte =  (byte) (higherByte | (1<<7));
				lowerByte = (byte)queuePattern.getReplacePattern();
				lowerByte =  (byte) (lowerByte & ~(1<<7));
			}
			else{
				higherByte = (byte)queuePattern.getExistingPattern();
				higherByte =  (byte) (higherByte | (1<<7));
				lowerByte = (byte)queuePattern.getReplacePattern();
				lowerByte =  (byte) (lowerByte | (1<<7));
			}
			
			
			headerBytes[++index] = higherByte;
			headerBytes[++index] = lowerByte;
			
		}
		fileOutputStream.write(headerBytes);

		while ((bytesRead = (short) fileInputStream.read(inputBuffer)) != -1) {
			
			for(short byteIndex=0;byteIndex<bytesRead; byteIndex++ ){
				bodyGenerator.writeBody(inputBuffer[byteIndex]);
				
			}
		}
		bodyGenerator.processLastByte();
		outputBuffer = bodyGenerator.getOutputBuffer();
		outputBufferIndex = bodyGenerator.getOutputBufferIndex();
		
		if(bodyGenerator.getCarryForwardPosition() >= 0){
			writeOutput(bodyGenerator.getCarryForwardByte());
			long remainingBits = (bodyGenerator.getNoOfZeros() -(bodyGenerator.getCarryForwardPosition() +1 ));
			long remainingBytes = 0;
			if(remainingBits>0){
				remainingBytes = remainingBits/8;
				remainingBytes = remainingBytes + remainingBits%8>0?1:0;
				for(long remainingIndex =0;remainingIndex<remainingBytes;remainingIndex++){
					
					writeOutput((byte)0);
					
				}
			}
		}
		
		if(outputBufferIndex>0){
			byte[] lastBytes= new byte[outputBufferIndex];
			System.arraycopy(outputBuffer,0,lastBytes,0,outputBufferIndex);
			fileOutputStream.write(lastBytes);
		}
	}
	
	private void writeOutput( byte outputByte) throws IOException{
		
		if(outputBufferIndex>=OUTPUT_BUFFER_SIZE){
			fileOutputStream.write(outputBuffer);
			outputBuffer = new byte[OUTPUT_BUFFER_SIZE];
			outputBufferIndex = 0;

		}
		outputBuffer[outputBufferIndex]= outputByte;
		outputBufferIndex++;
		
	}

}
