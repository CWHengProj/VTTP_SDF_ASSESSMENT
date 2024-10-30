package vttp.batch5.sdf.task02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import vttp.batch5.sdf.task02.supp.Helper;

//java -cp classes vttp.batch5.sdf.task02.Main TTT/figure1.txt
public class Main {

	public static void main(String[] args) throws Exception {
		Helper helper = new Helper();
		if (args.length<1){
			System.err.println("Please enter a valid configuration file. Exiting...");
			System.exit(1);
		}
		String cleanedInput =  args[0].replace("/","\\");
		String fileDir = "C:\\Users\\Admin\\Desktop\\vttp_b5_assessment_template\\task02\\" + cleanedInput;//TODO make the file path relative
		System.out.printf("Processing: %s\n\n",args[0]);
		char[][] gameGrid = helper.getGameGrid(fileDir);
		helper.simulateMoves(gameGrid);
		
		

	}
}
