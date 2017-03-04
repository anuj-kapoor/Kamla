package org.kamla.kapoor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Bit0Analyzer {


	
	private String inputFileName;

	private int bufferSize;

	private long inputByteCount;
	
	private ZeroPattern zeroPattern;

	public Bit0Analyzer(int bufferSize, String inputFileName) throws IOException {
		this.bufferSize = bufferSize;
		this.inputFileName = inputFileName;
		this.zeroPattern = new ZeroPattern();
		this.generateZeroPattern();

	}

	public long getInputByteCount() {
		return inputByteCount;
	}


	public ZeroPattern getZeroPattern() {
		return zeroPattern;
	}

	private void generateZeroPattern() throws IOException {

		FileInputStream fileInputStream = new FileInputStream(inputFileName);
		Map<Long, Long> leftPattern = zeroPattern.getLeftPattern();
		Map<Long, Long> rightPattern = zeroPattern.getRightPattern();
		Map<Long, Long> bothPattern =  zeroPattern.getBothPattern();
		Map<Long, Long> simplePattern  = zeroPattern.getSimplePattern();
		long bytesRead = 0;
		int byteIndex = 0;
		byte readByte = 0;
		long countOf0 = 0;
		byte[] buffer = new byte[bufferSize];
		boolean leftSet = false;
		boolean rightSet = false;
		long numberOf1 = 0;
		Queue<Long> queueForRight = new LinkedList<Long>();

		// Loop through the file
		while ((bytesRead = fileInputStream.read(buffer)) != -1) {

			// this will have the total bytes read from the file
			inputByteCount += bytesRead * 8;
			System.out.println(inputByteCount);

			byteIndex = 0;
			// Loop through all the bytes read into buffer
			while (byteIndex < bytesRead) {

				readByte = buffer[byteIndex];

				// process each byte
				for (byte bitIndex = 7; bitIndex >= 0; bitIndex--) {

					//If the bit read = 0 start
					if ((readByte & 1 << bitIndex) == 0) {
						
						if (queueForRight.size() > 0) {
							numberOf1--;
							countOf0 = queueForRight.remove();

							if (numberOf1 > 0) {
								rightSet = true;
							}

							if (rightSet && leftSet) {
								Long mapValue = bothPattern.get(countOf0);
								if (mapValue == null) {
									bothPattern.put(countOf0, 1L);
								} else {
									bothPattern.put(countOf0, mapValue + 1);
								}
							} else if (leftSet) {
								Long mapValue = leftPattern.get(countOf0);
								if (mapValue == null) {
									leftPattern.put(countOf0, 1L);
								} else {
									leftPattern.put(countOf0, mapValue + 1);
								}
							} else if (rightSet) {
								Long mapValue = rightPattern.get(countOf0);
								if (mapValue == null) {
									rightPattern.put(countOf0, 1L);
								} else {
									rightPattern.put(countOf0, mapValue + 1);
								}
							} else {
								Long mapValue = simplePattern.get(countOf0);
								if (mapValue == null) {
									simplePattern.put(countOf0, 1L);
								} else {
									simplePattern.put(countOf0, mapValue + 1);
								}
							}

							rightSet = false;
							leftSet = false;
							countOf0 = 0;
						}

						//To identify Left pattern
						if (countOf0 == 0 && numberOf1 > 1) {
							leftSet = true;
						}
						countOf0++;
						numberOf1 = 0;
						//If the bit read = 0 end
					} else {
						//If the bit read = 1 start					
						numberOf1++; // if bit read = 1 increment number of 1's this will help in identifying left or right pattern

						if (countOf0 > 0) {

							queueForRight.add(countOf0);//queue to check later on if at least 1 comes twice to the right of zero pattern
							countOf0 = 0;

						}
						//If the bit read = 1 end	
					}
				}
				byteIndex++;
			}
		}
		fileInputStream.close();
	}
}
