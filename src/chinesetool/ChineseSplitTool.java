package chinesetool;

import java.io.*;
import java.util.ArrayList;

import edu.thu.keyword.extract.CountDF;
import wordsspliter.WordSegConll;

public class ChineseSplitTool {	
	
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
			int counter = 0;
			
			while(temp != null){
				String ans = splitChinese(temp);
				ArrayList<String> sorted = ChineseSortTool.sortChineseParagraph(ans);
				ArrayList<String> removed = ChineseStopWordTool.removeStopWords(sorted);
				for(int i=0; i<removed.size(); i++)
					ps.print(removed.get(i) + " ");
				ps.println("");
				temp = br.readLine();
				counter = counter + 1;
				if(counter % 1000 == 0){
					Integer i= new Integer(counter);
					System.out.println(i.toString() + " data processed");
				}
			}
			ps.close();
			br.close();
		}catch(IOException e){
			System.out.println(e);
		}
	}
	
	public static void main(String args[]){
		parseFile("part_energy.dat", "part_energy_split.dat");
		/*
		if(DAO.isInStopwords("的")){System.out.println("in");} else{System.out.println("out"); }
		if(DAO.isInStopwords("我")){System.out.println("in");} else{System.out.println("out"); }
		if(DAO.isInStopwords("李天润")){System.out.println("in");} else{System.out.println("out"); }
		if(DAO.isInStopwords("张敬卿")){System.out.println("in");} else{System.out.println("out"); }
		*/
	}
}
