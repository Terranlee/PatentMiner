package topicmodel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.cli.CommandLineParser;  
import org.apache.commons.cli.BasicParser;  
import org.apache.commons.cli.Options;  
import org.apache.commons.cli.CommandLine; 
import org.apache.commons.cli.HelpFormatter;

import topicmodel.LDAKernel.LDAExecType;

public class LDAEntry {
	
	private static CommandLineParser clp;
	private static Options op;
	private static HelpFormatter hf;
	
	// initialize parser
	static{
		clp = new BasicParser();
		
		op = new Options();
		op.addOption("est", "estimate", false, "Estimate");
		op.addOption("estc", "estimateContinue", false, "Estimate from previews");
		op.addOption("inf", "inference", false, "Inference");
		
		op.addOption("alpha", "Alpha", true, "Alpha, default value is 0.5");
		op.addOption("beta", "Beta", true, "Beta, default value is 0.1");
		op.addOption("ntopics", "numTopics", true, "Number of topics, default value is 100");
		op.addOption("niters", "numIters", true, "Number of iterations, default value is 2000");
		op.addOption("savestep", "saveSteps", true, "Step when LDA save model to disk, default value is 200");
		op.addOption("twords", "topicWords", true, "Number of Keywords, default value is 0");
		op.addOption("dfile", "dataFile", true, "Input data file name");
		op.addOption("dir", "directory", true, "Directory contains the previews model");
		op.addOption("model", "prevModel", true, "Previews estimate model");
		
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
	
	// parse LDA arguments, return success or not
	private static boolean parseArgs(String command, LDAKernel ldak){
		try{
			String[] args = command.split(" ");
			CommandLine cl = clp.parse(op, args);
			if(cl.hasOption("est")){
				ldak.setType(LDAExecType.EST);
				if(cl.hasOption("dfile"))
					ldak.setDfile(cl.getOptionValue("dfile"));
				else{	System.out.println("data file is needed");
						return false;	}
			}
			if(cl.hasOption("estc")){
				ldak.setType(LDAExecType.ESTC);
				if(cl.hasOption("dir"))
					ldak.setDir(cl.getOptionValue("dir"));
				else{	System.out.println("directory is needed");
						return false;	}
				if(cl.hasOption("model"))
					ldak.setModel(cl.getOptionValue("model"));
				else{	System.out.println("previews model is needed");
						return false;	}
			}
			if(cl.hasOption("inf")){
				ldak.setType(LDAExecType.INF);
				if(cl.hasOption("dfile"))
					ldak.setDfile(cl.getOptionValue("dfile"));
				else{	System.out.println("data file is needed");
						return false;	}
				if(cl.hasOption("dir"))
					ldak.setDir(cl.getOptionValue("dir"));
				else{	System.out.println("directory is needed");
						return false;	}
				if(cl.hasOption("model"))
					ldak.setModel(cl.getOptionValue("model"));
				else{	System.out.println("previews model is needed");
						return false;	}
			}
			if(cl.hasOption("alpha"))
				ldak.setAlpha(Double.parseDouble(cl.getOptionValue("alpha")));
			if(cl.hasOption("beta"))
				ldak.setBeta(Double.parseDouble(cl.getOptionValue("beta")));
			if(cl.hasOption("ntopics"))
				ldak.setNTopics(Integer.parseInt(cl.getOptionValue("ntopics")));
			if(cl.hasOption("niters"))
				ldak.setNIters(Integer.parseInt(cl.getOptionValue("niters")));
			if(cl.hasOption("savestep"))
				ldak.setSaveStep(Integer.parseInt(cl.getOptionValue("savestep")));
			if(cl.hasOption("twords"))
				ldak.setTWords(Integer.parseInt(cl.getOptionValue("twords")));
		}catch(Exception e){
			System.out.println("Unrecognized option");
			return false;
		}
		return true;
	}
	
	private static void runLDA(String command){
		LDAKernel ldak = new LDAKernel();
		boolean ans = parseArgs(command, ldak);
		if(ans == false){
			System.out.println("Parse arguments error, type help for details");
			return;
		}
		System.out.println("Parse arguments success, begin running LDA");
		int argc = ldak.generateCommand();
		String argv = ldak.getCommand();
		ldak.calculateLDA(argc, argv);
		if(ans == false){
			System.out.println("Get topics error");
			return;
		}
	}
	
	public static void execute(){
		System.out.println("This is topic model toolkit");
		System.out.println("Type the command or type 'help' for more information"
				+ " or type 'exit' to return back to Miner");
		try{
			while(true){
				System.out.print("lda");
				String content;
				BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
				content = bf.readLine();
				if(content.equals("help"))
					showHelp();
				if(content.equals("exit"))
					break;
				else
					runLDA(content);
			}
		}catch(IOException e){	e.printStackTrace();	}
	}
	
	public static void main(String[] args){
		String command = "-est -dfile trndocs.dat -twords 10 -ntopics 30 -niters 300";
		runLDA(command);
	}
}
