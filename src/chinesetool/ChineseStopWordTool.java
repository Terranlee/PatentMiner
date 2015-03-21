package chinesetool;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import edu.thu.keyword.dao.DAO;

public class ChineseStopWordTool {
	// static code, initialize regex
	private static Pattern p;
	static{
		p = Pattern.compile("[0-9]+?");
	}
	
	// remove the stop words in a array 
	public static ArrayList<String> removeStopWords(ArrayList<String> contents){
		ArrayList<String> answer = new ArrayList<String>();
		for(int i=0; i<contents.size(); i++){
			if(DAO.isInStopwords(contents.get(i)))
				continue;
			Matcher m = p.matcher(contents.get(i));
			if(m.find() == true)
				continue;
			answer.add(contents.get(i));
		}
		return answer;
	}
	
	// if a single word is stop word
	public static boolean isStopWord(String word){
		if(DAO.isInStopwords(word))
			return true;
		Matcher m = p.matcher(word);
		if(m.find() == true)
			return true;
		else
			return false;
	}
	
	public static void main(String[] args){
		String word = "��";
		System.out.println(isStopWord(word));
		ArrayList<String> words = new ArrayList<String>();
		words.add("��Ҫ");
		words.add("��");
		words.add("�����ھ�");
		words = removeStopWords(words);
		for(int i=0; i<words.size(); i++){
			System.out.println(words.get(i));
		}
	}
}
