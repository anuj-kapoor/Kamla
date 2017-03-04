package org.kamla.kapoor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class KamlaCompressor {
	
	private String inputFileName;
	private Bit0Analyzer bit0Analyzer;
	private PatternFinalizer patternFinalizer;
	private OutputGenerator outputGenerator;
	
	
	public KamlaCompressor(String inputFileName) throws IOException{
		
		//this.bit0Analyzer = new Bit0Analyzer(1000, "C:\\Users\\anuj\\OneDrive\\Documents\\input.dat");
		this.inputFileName= inputFileName;
		this.bit0Analyzer = new Bit0Analyzer(1000,this.inputFileName);
		this.patternFinalizer = new PatternFinalizer(this.bit0Analyzer.getZeroPattern());
		this.outputGenerator = new OutputGenerator(this.inputFileName, this.patternFinalizer);
		
	}
	
	
	

	public static void main(String[] args) throws IOException {

		KamlaCompressor  kamlaCompressor= new KamlaCompressor("C:\\Users\\anuj\\OneDrive\\Documents\\input.dat");
	

		
//		PatternFinalizer patternFinalizer = new PatternFinalizer(zeroPattern);
//		
//		List<Pattern> patternQueue = patternFinalizer.getPattern();
//		
//		System.out.println("\ninputByte:"+ patternFinder.getInputByteCount());
//		Iterator it = patternQueue.iterator();
//		long newSum = 0;
//		long oldSum = 0;
//		long savings=0;
//
//		while(it.hasNext()){
//			Pattern p =(Pattern)it.next();
//			if(p.getLeftRightPattern()==LeftRightPattern.bothPattern){
//				newSum += p.getCount()*(p.getReplacePattern()-2);
//			}
//			else if(p.getLeftRightPattern()==LeftRightPattern.leftPattern || p.getLeftRightPattern()==LeftRightPattern.rightPattern ){
//				newSum += p.getCount()*(p.getReplacePattern()-1);
//			}
//			else{
//				newSum += p.getCount()*(p.getReplacePattern());
//			}
//			
//			oldSum += p.getCount()*p.getExistingPattern();
//		}
//		savings = oldSum-newSum;
//		System.out.println("old Sum:"+ oldSum);
//		System.out.println("new Sum:"+ newSum);
//		System.out.println("savings "+ savings);
//		System.out.println("No. of patterns "+ patternQueue.size());
//		
		
		
		
//		FileOutputStream outputStream = new FileOutputStream("C:\\Users\\anuj\\OneDrive\\Documents\\output.txt");
//		
////		outputStream.write(Header.getHeaderbytes(patternQueue));
//		
//		outputStream.close();
//		
//		byte[] headerBytes= Header.getHeaderbytes(patternQueue);
		
		
		

	}
	
}
