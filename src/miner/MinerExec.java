package miner;

import chinesetool.ChineseSplitTool;
import keyword.KeywordExtract;
import topicmodel.LDAEntry;

public class MinerExec {
	
	private String type;
	
	public MinerExec(String t){
		type = t;
	}
	
	public void execute(){
		if(type.equals("w")){
			ChineseSplitTool.execute();
		}
		else if(type.equals("k")){
			KeywordExtract ke = new KeywordExtract();
			ke.execute();
		}
		else if(type.equals("t")){
			LDAEntry.execute();
		}
		else if(type.equals("e")){
			// do something to initialize the elastic search tools
		}
		else{
			System.out.println("Unsupported command:" + type);
		}
	}
	
	public static void main(String[] args){
		
	}
}
