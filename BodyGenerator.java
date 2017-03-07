package org.kamla.kapoor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BodyGenerator {


	private byte carryForwardByte;
	private byte carryForwardPosition;
	private long noOfZeros;
	private long noOfOnes;
	private Map<String,Integer> dictionary;
	private Queue<Boolean> bitsQueue = new LinkedList<Boolean>();
	private byte[] outputBuffer;
	private short outputBufferIndex;
	private FileOutputStream fileOutputStream;
	private short outputBufferSize;
	private boolean leftSet;
	private boolean rightSet;
	boolean processBits;
	boolean bitZeroProcessed ;
	
	public long getNoOfZeros() {
		return noOfZeros;
	}
	public byte getCarryForwardByte() {
		return carryForwardByte;
	}

	public byte getCarryForwardPosition() {
		return carryForwardPosition;
	}
	
	public byte[] getOutputBuffer() {
		return outputBuffer;
	}
	public short getOutputBufferIndex() {
		return outputBufferIndex;
	}
	public BodyGenerator(Map<String,Integer> dictionary,FileOutputStream fileOutputStream,short outputBufferSize ){
		this.carryForwardByte = 0;
		this.carryForwardPosition = 7;
		this.noOfZeros = 0;
		this.dictionary = dictionary;
		this.fileOutputStream = fileOutputStream;
		this.outputBufferSize = outputBufferSize;
		this.outputBuffer = new byte[outputBufferSize];
		this.outputBufferIndex = 0;
		
		
		
	}

	public void writeBody(byte inputByte) throws IOException{
		
		byte bitIndex = 7;
		int replacedOutput;

	
		while(bitIndex>=0){
			
			if ((inputByte & 1 << bitIndex) == 0) {
				
				
				while(processBits){
					boolean queueBit = bitsQueue.remove();
				
					if(queueBit){
						noOfOnes++;
						if(bitZeroProcessed){
							
							if(bitsQueue.size()>0){
								rightSet=true;			
							}
							
							if(leftSet && rightSet){
								replacedOutput = dictionary.get(noOfZeros+"B");
								
							}else if(leftSet){
								replacedOutput = dictionary.get(noOfZeros+"L");
								
							}else if(rightSet){
								replacedOutput = dictionary.get(noOfZeros+"R");
								
							}else{
								replacedOutput = dictionary.get(noOfZeros+"S");
							}
							
							
							for(int replacedIndex=1;replacedIndex<=replacedOutput;replacedIndex++){
								setCurrentBit(false);
							}
							
							if(rightSet){
								bitsQueue.remove();
								setCurrentBit(true);
								
							}else{
								setCurrentBit(true);
							}
							
							processBits= false;
							bitZeroProcessed = false;
							leftSet=false;
							rightSet=false;
							noOfZeros = 0;
							noOfOnes = 0;
							
						}

						
					}else{
						
						if(!bitZeroProcessed){
							
							
							if(noOfOnes>0){
								leftSet = true;
								noOfOnes--;
								
								for(int processedIndex = 1;processedIndex<=noOfOnes;processedIndex++){
									setCurrentBit(true);
								}
							}
						}
						
						
						bitZeroProcessed = true;
						noOfOnes = 0;
					}

				}
				
				noOfZeros++;
				bitsQueue.add(false);
				

			}else{
				
				
				bitsQueue.add(true);
				
				if(noOfZeros>0){
					processBits=true;
				}
				
				

			}
			bitIndex--;
		}
		
		
		
	}
	
	public void processLastByte() throws IOException{
		int replacedOutput;
		
		while(processBits){
			boolean queueBit = bitsQueue.remove();
		
			if(queueBit){
				noOfOnes++;
				if(bitZeroProcessed){
					
					if(bitsQueue.size()>0){
						rightSet=true;			
					}		
					
					if(leftSet && rightSet){
						replacedOutput = dictionary.get(noOfZeros+"B");
						
					}else if(leftSet){
						replacedOutput = dictionary.get(noOfZeros+"L");
						
					}else if(rightSet){
						replacedOutput = dictionary.get(noOfZeros+"R");
						
					}else{
						replacedOutput = dictionary.get(noOfZeros+"S");
					}
					
					
					for(int replacedIndex=1;replacedIndex<=replacedOutput;replacedIndex++){
						setCurrentBit(false);
					}
					

					if(rightSet){
						while(bitsQueue.size()>0){
							bitsQueue.remove();
							setCurrentBit(true);
						}
						
					}else{
						setCurrentBit(true);
					}

					
					processBits= false;
					bitZeroProcessed = false;
					leftSet=false;
					rightSet=false;
					noOfZeros = 0;
					noOfOnes = 0;
					
				}

				
			}else{
				
				if(!bitZeroProcessed){
					
					
					if(noOfOnes>0){
						leftSet = true;
						noOfOnes--;
						
						for(int processedIndex = 1;processedIndex<=noOfOnes;processedIndex++){
							setCurrentBit(true);
						}
					}
				}
				
				
				bitZeroProcessed = true;
				noOfOnes = 0;
			}

		}
		
		
	}
	
	private void writeOutput() throws IOException{
		

		if(outputBufferIndex>=outputBufferSize){
			fileOutputStream.write(outputBuffer);
			outputBuffer = new byte[outputBufferSize];
			outputBufferIndex = 0;

		}
		outputBuffer[outputBufferIndex]= carryForwardByte;
		outputBufferIndex++;
		carryForwardByte = 0;
		carryForwardPosition =7;
		
	}
	
	private void setCurrentBit(boolean bitFlag) throws IOException{
		
		if(carryForwardPosition<0){
			writeOutput();
		}
		if(bitFlag){
			carryForwardByte=(byte) (carryForwardByte | 1<<carryForwardPosition);
			carryForwardPosition--;
		}
		else{
			carryForwardPosition--;
		}

		
	}


}