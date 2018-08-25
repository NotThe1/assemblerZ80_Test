package Utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class ConstructTestForDisasembler {

	public static void main(String[] args) {
		new ConstructTestForDisasembler().doIt();

	}// main

	private void doIt() {
		int opCode1= 0xDDCB;
		String InputFileName =  "C:\\Users\\admin\\Dropbox\\Resources\\Z80\\z80tables\\Dis_In_DDCB.txt";
		String OutputFileName = "C:\\Users\\admin\\Dropbox\\Resources\\Z80\\z80tables\\Dis_In_DDCB.asm";
		// Pattern pattern = Pattern.compile("( 0X[0123456789ABCDEF]{2})");
		// Matcher matcher;
		try {
			// Scanner lineScanner;
			Scanner scanner = new Scanner(new File(InputFileName));
			FileWriter fw = new FileWriter(OutputFileName);
				
			String line;
			String lineOfCode;
			String[] parts;
			int lineNumber = 0;
			int hiCode,loCode;
			boolean evenLine;
			
			lineOfCode = String.format(";\t%s\t\t%s%n%n",OutputFileName,new Date());
			System.out.print(lineOfCode);
			fw.write(lineOfCode);
			scanner.nextLine(); // skip first line
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				hiCode= lineNumber/2;
				
				lineOfCode = String.format("%n;line %2d:[%X]%n", lineNumber++,hiCode);
				fw.write(lineOfCode);
				System.out.print(lineOfCode);
				parts = line.split(TAB);
				evenLine = (lineNumber % 2)==0;
				for (int i = 0;i<8;i++) {
					loCode = evenLine?i+8:i;
					lineOfCode = String.format(" %-10s\t;[%04X %02X]%n",parts[(i*3) + 1],opCode1,(hiCode * 16)+loCode);
//					lineOfCode = String.format(" %-10s\t;[%04X]%n",parts[(i*3) + 1],(hiCode * 16)+loCode);
					fw.write(lineOfCode);
					System.out.print(lineOfCode);
				}//for
				
	

				// fw.write(line+ System.lineSeparator());
			} // while
			scanner.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// doIt

	private static final String TAB = "\\t";

}// class ConstructTestForDisasembler
