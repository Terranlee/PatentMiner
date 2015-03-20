package wordsplit;

import java.io.*;
import java.util.Collections;
import java.util.Arrays;
import java.util.ArrayList;

import edu.thu.keyword.dao.DAO;
import edu.thu.keyword.extract.CountDF;
import wordsspliter.WordSegConll;

public class WordSplitter {
	
	// remove the stop words in a array 
	public static ArrayList<String> removeStopWords(ArrayList<String> contents){
		ArrayList<String> answer = new ArrayList<String>();
		for(int i=0; i<contents.size(); i++){
			if(!DAO.isInStopwords(contents.get(i)))
				answer.add(contents.get(i));
		}
		return answer;
	}
	
	// sort split Chinese words
	public static ArrayList<String> sortChineseParagraph(String content){
		String[] parts = content.split(" ");
		ArrayList<String> arrStr = new ArrayList<String>(Arrays.asList(parts));
		Collections.sort(arrStr, new ChineseComparator());
		return arrStr;
	}
	
	// words will be split by spaces 
	public static String splitChinese(String content){
		String convertString = CountDF.ToDBC(content);
		convertString = convertString.trim().replaceAll("/|／|？|；"," ");
		convertString = convertString.replaceAll("．{2,}+","．");
		convertString= convertString.replaceAll("％|%","");
		String ans = WordSegConll.wordSeg(convertString);
		return ans;
	}
	
	// parse the whole file and split the Chinese words in it
	public static void parseFile(String inputFile, String outputFile){
		try{
			// file input, make sure you use the right encoding
			FileInputStream fis = new FileInputStream(inputFile);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			
			File outFile = new File(outputFile);
			if(!outFile.exists())
				outFile.createNewFile();
			FileOutputStream out = new FileOutputStream(outFile);
			PrintStream ps = new PrintStream(out);
		
			String temp = null;
			temp = br.readLine();
			while(temp != null){
				String ans = splitChinese(temp);
				ArrayList<String> sorted = sortChineseParagraph(ans);
				ArrayList<String> removed = removeStopWords(sorted);
				for(int i=0; i<removed.size(); i++)
					ps.print(removed.get(i) + " ");
				ps.println("");
				temp = br.readLine();
			}
			
			ps.close();
			br.close();
		}catch(IOException e){
			System.out.println(e);
		}
	}
	
	public static void main(String args[]){
		//parseFile("part_metal.txt", "part_metal.out");
		if(DAO.isInStopwords("的")){System.out.println("in");} else{System.out.println("out"); }
		if(DAO.isInStopwords("我")){System.out.println("in");} else{System.out.println("out"); }
		if(DAO.isInStopwords("李天润")){System.out.println("in");} else{System.out.println("out"); }
		if(DAO.isInStopwords("张敬卿")){System.out.println("in");} else{System.out.println("out"); }
	}
}
