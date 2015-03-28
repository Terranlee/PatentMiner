package topicmodel;

import edu.thu.keyword.util.KWDSettings;

public class LDAKernel {
	// LDA execution type
	public enum LDAExecType{
		EST,		//estimate the topic model
		ESTC,		//continue to estimate topic model on previews result
		INF			//do inference for previews unseen data
	}
	
	//LDA parameters
	private LDAExecType type;
	private double alpha;
	private double beta;
	private int nTopics;	//how many topics you want to analyse
	private int nIters;		//how many Gibbs iteration
	private int saveStep;	//save answer after how many iterations
	private int tWords;		//related words of each topic
	private String dfile;	//input data file
	//for continuous analysis based on previews answer
	private String dir;		//previews answers directory
	private String model;	//previews answer file that already exist
	
	private String command;
	private int counter;
	
	//set default value of LDA
	public LDAKernel(){
		type = LDAExecType.EST;
		alpha = 0.5;
		beta = 0.1;
		nTopics = 100;
		nIters = 2000;
		saveStep = 200;
		tWords = 0;
		dfile = "";
		dir = "";
		model = "";
		command = "";
	}
	
	//set parameters
	public void setType(LDAExecType t){	type = t;	}
	public void setAlpha(double a){	alpha = a;	}
	public void setBeta(double b){	beta = b;	}
	public void setNTopics(int n){	nTopics = n;	}
	public void setNIters(int n){	nIters = n;	}
	public void setSaveStep(int s){	saveStep = s;	}
	public void setTWords(int t){	tWords = t;	}
	public void setDfile(String d){	dfile = d;	}
	public void setDir(String d){	dir = d;	}
	public void setModel(String m){	model = m;	}
	
	//get parameters
	public LDAExecType getType(){	return type;	}
	public double getAlpha()	{	return alpha;	}
	public double getBeta()		{	return beta;	}
	public int getNTopics()		{	return nTopics;	}
	public int getNIters()		{	return nIters;	}
	public int getSaveStep()	{	return saveStep;}
	public int getTWords()		{	return tWords;	}
	public String getDfile()	{	return dfile;	}
	public String getDir()		{	return dir;		}
	public String getModel()	{	return model;	}
	
	//get LDA command, can be used as argv and argc
	public String getCommand(){	return command;	}
	public int getCounter(){	return counter;	}
	
	//generate LDA command
	public int generateCommand(){
		counter = 14;
		StringBuffer sb = new StringBuffer();
		sb.append("src/lda ");
		if(type == LDAExecType.EST)
			sb.append("-est");
		else if(type == LDAExecType.ESTC)
			sb.append("-estc");
		else
			sb.append("-inf");
		if(!dir.equals("")){
			sb.append(" -dir " + dir);
			sb.append(" -model " + model);
			counter = counter + 2;
		}
		sb.append(" -alpha " + String.valueOf(alpha));
		sb.append(" -beta " + String.valueOf(beta));
		sb.append(" -ntopics " + String.valueOf(nTopics));
		sb.append(" -niters " + String.valueOf(nIters));
		sb.append(" -savestep " + String.valueOf(saveStep));
		sb.append(" -twords " + String.valueOf(tWords));
		if(!dfile.equals("")){
			sb.append(" -dfile " + dfile);
			counter = counter + 2;
		}
		command = sb.toString();
		return counter;
	}
	
	// The following functions are related to jni
	//native function
	public native void calculateLDA(int argc, String argv);
	// load shared object file
	static{
		String dir = findPathOfLibrary();
		System.load(dir);
	}
	private static String findPathOfLibrary(){
		String path = KWDSettings.rootPath;
		switch(KWDSettings.osType){
			case linux_x64:
				path += "data/ldalibs/liblda64.so";
				break;
			case windows_x64:
				path += "data/ldalibs/lda64.dll";
				break;
			case linux_x86:
				System.out.println("Linux_x86 is currently not supported");
				break;
			case windows_x86:
				path += "data/ldalibs/lda32.dll";
				break;
			}
		return path;
	}
	
	public static void main(String[] args){
		LDAKernel ldak = new LDAKernel();
		ldak.setType(LDAExecType.EST);
		ldak.setNIters(1000);
		ldak.setSaveStep(100);
		ldak.setTWords(20);
		ldak.setDfile("part_energy_split.dat");
		int argc = ldak.generateCommand();
		String argv = ldak.getCommand();
		ldak.calculateLDA(argc, argv);
	}
}
