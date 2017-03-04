package org.kamla.kapoor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Body {


	byte carryForwardByte;
	byte carryForwardPosition;
	long noOfZeros;
	Map<Long,List<Boolean>> dictionary;
	byte[] outputBuffer;
	short outputBufferIndex;
	FileOutputStream outputStream;
	short outputBufferSize;
	
	public long getNoOfZeros() {
		return noOfZeros;
	}

	public void byteGenerator(byte inputByte) throws IOException{
		
		byte bitIndex = 7;
	
		while(bitIndex>0){
			
			if ((inputByte & 1 << bitIndex) == 0) {
				noOfZeros++;
			}else{
				
				if(noOfZeros>0){
					List<Boolean> replacePatternList = dictionary.get(noOfZeros);
					
					for( Boolean replacePattern:replacePatternList){
						
						if(!replacePattern){
							setCurrentBit(true);
						}
						else{
							setCurrentBit(false);
						}
					}
				}
				setCurrentBit(true);

			}
			bitIndex--;
		}
		
		
		
	}
	
	private void writeOutput() throws IOException{
		
		if(outputBufferIndex<outputBufferSize){
			outputStream.write(outputBuffer);
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