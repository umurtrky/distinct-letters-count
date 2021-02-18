package main.java;

public class Main {

	public static void main(String[] args) {
		//Take file path as a command line argument
		String filePath = "";
		if(args.length == 1) {
			filePath = args[0];
		} else if(args.length == 0) {
			System.out.println("Missing command line argument... Enter the path of the file as the command line argument.");
		} else {
			System.out.println("More than one command line arguments is entered. The first one will be used as the file path.");
			filePath = args[0];
		}
		
		FileOperations.printMinNumberOfLettersToRemove(filePath);
	}

}
