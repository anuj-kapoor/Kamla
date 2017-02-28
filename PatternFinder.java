package org.kamla.kapoor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class PatternFinder {

	private FileInputStream fileInputStream;

	private int bufferSize;

	private int inputByteCount;

	public PatternFinder(FileInputStream fileInputStream, int bufferSize) {
		this.fileInputStream = fileInputStream;
		this.bufferSize = bufferSize;

	}

	public int getInputByteCount() {
		return inputByteCount;
	}

	public void setInputByteCount(int inputByteCount) {
		this.inputByteCount = inputByteCount;
	}

	public ZeroPattern getZeroPattern() throws IOException {

		ZeroPattern zeroPattern = new ZeroPattern();
		Map<Integer, Integer> leftPattern;
		Map<Integer, Integer> rightPattern;
		Map<Integer, Integer> bothPattern;
		Map<Integer, Integer> simplePattern;
		int bytesRead = 0;
		int byteIndex = 0;
		byte readByte = 0;
		int countOf0 = 0;
		byte[] buffer = new byte[bufferSize];
		boolean leftSet = false;
		boolean rightSet = false;
		short numberOf1 = 0;
		Queue<Integer> queueForRight = new LinkedList<Integer>();

		leftPattern = zeroPattern.getLeftPattern();
		rightPattern = zeroPattern.getRightPattern();
		bothPattern = zeroPattern.getBothPattern();
		simplePattern = zeroPattern.getSimplePattern();

		// Loop through the file
		while ((bytesRead = fileInputStream.read(buffer)) != -1) {

			// this will have the total bytes read from the file
			inputByteCount += bytesRead * 8;

			byteIndex = 0;
			// Loop through all the bytes read into buffer
			while (byteIndex < bytesRead) {

				readByte = buffer[byteIndex];

				// process each byte
				for (int bitIndex = 7; bitIndex >= 0; bitIndex--) {

					if ((readByte & 1 << bitIndex) == 0) {
						if (queueForRight.size() > 0) {
							numberOf1--;
							countOf0 = queueForRight.remove();

							if (numberOf1 > 0) {
								rightSet = true;
							}

							if (rightSet && leftSet) {
								Integer mapValue = bothPattern.get(countOf0);
								if (mapValue == null) {
									bothPattern.put(countOf0, 1);
								} else {
									bothPattern.put(countOf0, mapValue + 1);
								}
							} else if (leftSet) {
								Integer mapValue = leftPattern.get(countOf0);
								if (mapValue == null) {
									leftPattern.put(countOf0, 1);
								} else {
									leftPattern.put(countOf0, mapValue + 1);
								}
							} else if (rightSet) {
								Integer mapValue = rightPattern.get(countOf0);
								if (mapValue == null) {
									rightPattern.put(countOf0, 1);
								} else {
									rightPattern.put(countOf0, mapValue + 1);
								}
							} else {
								Integer mapValue = simplePattern.get(countOf0);
								if (mapValue == null) {
									simplePattern.put(countOf0, 1);
								} else {
									simplePattern.put(countOf0, mapValue + 1);
								}
							}

							rightSet = false;
							leftSet = false;
							countOf0 = 0;
						}

						if (countOf0 == 0 && numberOf1 > 1) {
							leftSet = true;
						}
						countOf0++;
						numberOf1 = 0;

					} else {
						numberOf1++;

						if (countOf0 > 0) {

							queueForRight.add(countOf0);
							countOf0 = 0;

						}
					}
				}
				byteIndex++;
			}
		}
		return zeroPattern;
	}
}
