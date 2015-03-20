package miner;

import keyword.KeywordExtract;
import wordsspliter.WordSegConll;
import java.io.*;

public class Miner {
	
	public static void readin(String filename, String output) throws IOException{
		File file = new File(filename);
		if(!file.exists() || file.isDirectory())
			throw new FileNotFoundException();
		BufferedReader br = new BufferedReader(new FileReader(file));
		File dst = new File(output);
		if(!dst.exists())
			dst.createNewFile();
		FileOutputStream out = new FileOutputStream(dst);
		PrintStream ps = new PrintStream(out);
		
		String temp = null;
		temp = br.readLine();
		while(temp != null){
			String ans = WordSegConll.wordSeg(temp);
			ps.println(ans);
			temp = br.readLine();
		}
		ps.close();
		br.close();
	}
	
	public static void main(String[] args){
		//String content = "本实用新型提供一种电冰箱，至少包括冰箱门和显示面板，显示面板装在冰箱门上部的凹部，冰箱还包括卡配合装置，在显示面板的一端，和紧固装置，在显示面板的另一端，将显示面板装到所述冰箱门的凹部。卡配合装置包括位于显示面板的背面的至少一个卡爪，和位于冰箱门凹部相应位置的至少一个凹陷，卡爪与所述凹陷配合将显示面板的一端装到所述冰箱门上。紧固装置包括紧固件，从冰箱门的内侧插入冰箱门并伸进显示面板的背面，从背面将显示面板装到冰箱门上。该显示面板安装和拆卸容易、便捷，从而有效提高冰箱的装配性，有利于冰箱的日常维护保养。并且，该显示面板外观美观，并且不会影响冰箱的隔热效果。";
		//String ans = WordSegConll.wordSeg(content);
		//System.out.println(ans);
		//String conllAns = WordSegConll.wordSegConll(content);
		//System.out.println(conllAns);
		
		try{
			readin("part_metal.txt", "part_metal.out");
		}catch(IOException e){
			System.out.println(e);
		}
	}
}
