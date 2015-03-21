package topicmodel;

import edu.thu.keyword.util.KWDSettings;

public class LDAKernel {
	// LDA execution type
	public enum LDAExecType{
		EST,		//estimate the topic model
		ESTC,		//continue to estimate topic model on previews result
		INF			//do inference for previews unseen data
	}
	
	private LDAExecType type;
	private double alpha;
	private double beta;
	private int nTopics;
	private int nIters;
	private int saveStep;
	private int tWords;
	private String dfile;
	private String dir;
	private String model;
	
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
	
	//native function
	public native void calculateLDA(int argc, String argv);
	
	// load shared object file
	static{
		System.load(findPathOfLibrary());
	}
	
	private static String findPathOfLibrary(){
		String path = KWDSettings.rootPath;
		switch(KWDSettings.osType){
			case linux_x64:
				path += "data/ldalibs/liblda.so";
				break;
			case windows_x64:
				path += "data/ldalibs/lda.dll";
				break;
			case linux_x86:
			case windows_x86:
				System.out.println("platform not supported");
				break;
			}
		return path;
	}
}
