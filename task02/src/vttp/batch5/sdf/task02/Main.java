package vttp.batch5.sdf.task02;

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
		String fileDir = cleanedInput;
		System.out.printf("Processing: %s\n\n",args[0]);
		char[][] gameGrid = helper.getGameGrid(fileDir);
		helper.simulateMoves(gameGrid);
		
		

	}
}
