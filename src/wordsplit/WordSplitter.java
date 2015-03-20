package wordsplit;

import java.io.*;

import edu.thu.keyword.extract.CountDF;
import wordsspliter.WordSegConll;

public class WordSplitter {
	
	// words will be split by spaces 
	public static String splitChinese(String content){
		String convertString = CountDF.ToDBC(content);
		convertString = convertString.trim().replaceAll("/|£¯|£¿|£»"," ");
		convertString = convertString.replaceAll("£®{2,}+","£®");
		convertString= convertString.replaceAll("£¥|%","");
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
				ps.println(ans);
				temp = br.readLine();
			}
			
			ps.close();
			br.close();
		}catch(IOException e){
			System.out.println(e);
		}
	}
	
	public static void main(String args[]){
		parseFile("part_metal.txt", "part_metal.out");
	}
}
