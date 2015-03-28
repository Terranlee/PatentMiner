package topicmodel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.cli.CommandLineParser;  
import org.apache.commons.cli.BasicParser;  
import org.apache.commons.cli.Options;  
import org.apache.commons.cli.CommandLine; 
import org.apache.commons.cli.HelpFormatter;

public class LDAEntry {
	
	private static CommandLineParser clp;
	private static Options op;
	private static HelpFormatter hf;
	
	// initialize parser
	static{
		clp = new BasicParser();
		
		op = new Options();
		op.addOption("est", "", false, "Estimate");
		op.addOption("estc", "", false, "Estimate from previews");
		op.addOption("inf", "", false, "Inference");
		op.addOption("alpha", "", true, "Alpha, default value is 0.5");
		op.addOption("beta", "", true, "Beta, default value is 0.1");
		op.addOption("ntopics", "", true, "Number of topics, default value is 100");
		op.addOption("niters", "", true, "Number of iterations, default value is 2000");
		op.addOption("savestep", "", true, "Step when LDA save model to disk, default value is 200");
		op.addOption("twords", "", true, "Number of Keywords, default value is 0");
		op.addOption("dfile", "", true, "Input data file name");
		op.addOption("dir", "", true, "Directory contains the previews model");
		op.addOption("model", "", true, "Previews estimate model");
		
		hf = new HelpFormatter();
	}
	
	private static void showHelp(){
		System.out.println("LDA commands are as follows:");
		System.out.println("> -est [-alpha <double>] [-beta <double>] [-ntopics <int>]" 
						+ "[-niters <int>] [-savestep <int>] [-twords <int>] -dfile <string>");
		System.out.println("> -estc -dir <string> -model <string> [-niters <int>] -savestep <int>]"
						+ "[-twords <int>]");
		System.out.println("> -inf -dir <string> -model <string> [-niters <int>] [-twords <int>]"
						+ "-dfile <string>");
		hf.printHelp("smssent", op);
	}
	
	private static void parseArgs(String command, LDAKernel ldak){
			
	}
	
	private static void runLDA(String command){
		LDAKernel ldak = new LDAKernel();
		parseArgs(command, ldak);
	}
	
	public static void execute(){
		System.out.println("This is topic model toolkit");
		System.out.println("Type in the command or type 'help' for more information");
		try{
			while(true){
				String content;
				BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
				content = bf.readLine();
				if(content.equals("help"))
					showHelp();
				else{
					runLDA(content);
					break;
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		
	}
}
