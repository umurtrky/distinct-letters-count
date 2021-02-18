package main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileOperations {

	static void printException(Exception e) {
		System.out.println("Error: " + e.getMessage());
		 for(StackTraceElement el : e.getStackTrace()) {
			 System.out.println(el.toString());
		 }
	}
	
	public static void printMinNumberOfLettersToRemove(String filePath) {
		if(!filePath.trim().isEmpty()) {
			BufferedReader br = null;
			try {
				File file = new File(filePath);
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				 String readLine = "";
				 int lineCount = 1;
				 String lineResult = "";
				 
				 //The number to check if the line is empty
				 int emptyLine = 0;
				 boolean isConstraintViolated = false;
				 
				 //read file line by line
				 while((readLine = br.readLine()) != null) {
					 if(!readLine.matches("[a-z]*")) {
						 lineResult += "This line contains at least one character which is not a lowercase letter. ";
						 isConstraintViolated = true;
						 emptyLine = 0;
					 }
					 if(readLine.length()<1) {
						 emptyLine++;
						 //If empty line again that means a real empty line
						 if(emptyLine > 1) {
							 lineResult += "No characters on this line. ";
							 isConstraintViolated = true;
							 emptyLine = 0;
						 }
					 } else if(readLine.length()>300000) {
						 lineResult += "Number of characters on this line is above 300000. ";
						 isConstraintViolated = true;
					 } else if(!isConstraintViolated) {
						 emptyLine = 0;
						 lineResult += StringUtil.getMinimumNumberOfLettersToRemove(readLine);
					 }
					 if(emptyLine != 1) {
						 System.out.println(lineCount + "- " + lineResult);
						 lineCount++;
						 isConstraintViolated = false;
					 } else {
						 System.out.println("");
					 }
					 lineResult = "";
				 }
			 } catch (FileNotFoundException e) {
				 printException(e);
			} catch (IOException e) {
				printException(e);
			} catch (Exception e) {
				printException(e);
			}finally {
				if(br != null) {
					try {
						br.close();
					} catch (IOException e) {
						printException(e);
					}
				}
			}
		}
	}
}
