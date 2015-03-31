package tools;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.util.List;

import chinesetool.ChineseSplitTool;
import keyword.KeywordExtract;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public class DataGenerater {
	
	// wordbank
	private static HashSet<String> wordbankSet;
	private static ArrayList<String> wordbank;
	
	// file suffix
	private static String splitSuffix = "spl";
	private static String keywordSuffix = "kwd";
	private static String outputSuffix = "out";
	private static String randomSuffix = "rnd";
	
	// randomize parameter
	private static double randomRate1 = 0.05;
	private static double randomRate2 = 0.1;
	
	private static double percent1 = 0.2;
	private static double percent2 = 0.5;
	private static double percent3 = 0.3;
	
	// randomize size, duplicate*counter=original size
	private static int duplicate = 10;
	private static int counter = 1000;
	
	private static void readFile(String filename, ArrayList<String> allContent){
		try{
			FileInputStream fis = new FileInputStream(filename);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String content;
			content = br.readLine();
			while(content != null){
				allContent.add(content);
				content = br.readLine();
			}
			br.close();
		}catch(IOException e){	e.printStackTrace();	}
	}
	
	private static void buildWordBank(HashSet<String> bank, ArrayList<String> contents){
		for(int i=0; i<contents.size(); i++){
			String temp = contents.get(i);
			String[] arrStr = temp.split(" ");
			for(int j=0; j<arrStr.length; j++)
				bank.add(arrStr[j]);
		}
	}
	
	private static void randomizeString(String content, BufferedWriter bw) throws IOException{
		Random ra = new Random();
		int sz = wordbank.size();
		String[] arrStr = content.split(" ");
		for(int i=0; i<(int)(duplicate*percent1); i++)
			bw.write(content + "\n");
		for(int i=0; i<(int)(duplicate*percent2); i++){
			for(int j=0; j<arrStr.length; j++){
				double rnd = ra.nextDouble();
				if(rnd < randomRate1){
					String temp = wordbank.get(ra.nextInt(sz-1));
					bw.write(temp + " ");
				}
				else{	bw.write(arrStr[j] + " ");	}	
			}
			bw.write("\n");
		}
		for(int i=0; i<(int)(duplicate*percent3); i++){
			for(int j=0; j<arrStr.length; j++){
				double rnd = ra.nextDouble();
				if(rnd < randomRate2){
					String temp = wordbank.get(ra.nextInt(sz-1));
					bw.write(temp + " ");
				}
				else{	bw.write(arrStr[j] + " ");	}	
			}
			bw.write("\n");
		}
	}
	
	private static void randomizeText(String filename){
		String output = filename + randomSuffix;
		String input = filename + splitSuffix;
		ArrayList<String> allContent = new ArrayList<String>();
		wordbankSet = new HashSet<String>();
		readFile(input, allContent);
		buildWordBank(wordbankSet, allContent);
		wordbank = new ArrayList<String>(wordbankSet);
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(output));
			for(int i=0; i<counter; i++)
				randomizeString(allContent.get(i), bw);
			bw.close();
		}catch(IOException e){	e.printStackTrace();	}
		
	}
	
	private static void mergeFile(String src1, String src2, String output){
		try{
			FileInputStream fis1 = new FileInputStream(src1);
			BufferedReader br1 = new BufferedReader(new InputStreamReader(fis1));
			FileInputStream fis2 = new FileInputStream(src2);
			BufferedReader br2 = new BufferedReader(new InputStreamReader(fis2));
			BufferedWriter bw = new BufferedWriter(new FileWriter(output));
			
			String content1, content2;
			content1 = br1.readLine();
			while(content1 != null){
				content2 = br2.readLine();
				content2 = br2.readLine();
				content2 = br2.readLine();
				content2 = br2.readLine();
				bw.write(content1 + "\n" + content2 + "\n");
				content1 = br1.readLine();
			}
			br1.close();
			br2.close();
			bw.close();
		}catch(IOException e){	e.printStackTrace();	}
		catch(Exception e){	e.printStackTrace();	}
	}
	
	private static void keywordAndSplit(String filename){
		String split = filename + splitSuffix;
		String keyword = filename + keywordSuffix;
		ChineseSplitTool.parseFile(filename, split);
		KeywordExtract k = new KeywordExtract();
		k.extractKey(filename, keyword);
	}
	
	public static void main(String[] args){
		String filename = "newenergy_part.dat";
		String outfile = "output.dat";
		keywordAndSplit(filename);
		mergeFile(filename+splitSuffix, filename+keywordSuffix, outfile);
	}
}
