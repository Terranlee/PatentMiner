package topicmodel;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LDAToolkit {
	
	private static String getWorkingDir(String filename){
		int place = filename.lastIndexOf("/");
		System.out.println(place);
		if(place != -1)
			return filename.substring(0, place+1);
		else
			return "";
	}
	
	public static ArrayList<Topic> getAllTopics(String filename){
		File infile = null;
		Scanner sc = null;
		ArrayList<Topic> array = new ArrayList<Topic>();
		try{
			infile = new File(filename);
			sc = new Scanner(infile);
			String content1, content2;
			while(true){
				content1 = sc.next();
				if(content1 == null)
					break;
				if(content1.equals("Topic")){
					content2 = sc.next();
					continue;
				}
				content2 = sc.next();
				array.add(new Topic(content1, Double.valueOf(content2)));
			}
		}catch(IOException e){
			System.out.println(e);
		}finally{
			sc.close();
			return array;
		}
	}
	
	// get the answer after `which` iteration
	// which = [1, max)
	public static ArrayList<Topic> getTopicWords(LDAKernel ldak, int which){
		int s = ldak.getSaveStep();
		int i = ldak.getNIters();
		int times = i / s;
		String filename = "", dir = "";
		
		boolean isFinal = false; 
		if(which > times){
			System.out.println("Only saved for " + Integer.toString(times) + " times");
			System.out.println("Return final result instead");
			isFinal = true;
		}
		
		if(isFinal == false){
			int iters =  which * s;
			String name = "";
			if(iters / 100 == 0)
				name = "000" + Integer.toString(iters);
			else if(iters / 1000 == 0)
				name = "00" + Integer.toString(iters);
			else if(iters / 10000 == 0)
				name = "0" + Integer.toString(iters);
			else
				name = Integer.toString(iters);
			String inputFile = ldak.getDfile();
			dir = getWorkingDir(inputFile);
			filename = dir + "model-" + name + ".twords";
		}
		else{
			filename = dir + "model-final.twords";
		}
		return getAllTopic(filename);
	}
	
	public static void main(String[] args){
		LDAKernel ldak = new LDAKernel();
		ldak.setDfile("part_energy_split.dat");
		ldak.setNIters(1000);
		ldak.setSaveStep(100);
		
		ArrayList<Topic> topics = getTopicWords(ldak, 1);
		for(int i=0; i<100; i++)
			System.out.println(topics.get(i).toString());
	}
		
}
