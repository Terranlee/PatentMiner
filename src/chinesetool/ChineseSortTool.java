package chinesetool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.text.Collator;

class ChineseComparator implements Comparator<String>{
	// Chinese comparator
	// use this to convert split words to sorted words
	// words are sorted by pinyin
	Collator cmp = Collator.getInstance(java.util.Locale.CHINA);
	
	@Override
	public int compare(String s1, String s2){
		if(cmp.compare(s1, s2) > 0){
			return 1;
		}
		else if(cmp.compare(s1, s2) < 0){
			return -1;
		}
		return 0;
	}
}

public class ChineseSortTool{
	
	// sort split Chinese words in string
	public static ArrayList<String> sortChineseParagraph(String content){
		String[] parts = content.split(" ");
		ArrayList<String> arrStr = new ArrayList<String>(Arrays.asList(parts));
		Collections.sort(arrStr, new ChineseComparator());
		return arrStr;
	}
		
	// sort split Chinese words in vector
	public static ArrayList<String> sortChineseParagraph(ArrayList<String> content){
		Collections.sort(content, new ChineseComparator());
		return content;
	}
	
	public static void main(String[] args){
		String content = "专利 挖掘 工程";
		ArrayList<String> ans = ChineseSortTool.sortChineseParagraph(content);
		for(int i=0; i<ans.size(); i++){
			System.out.println(ans.get(i));
		}
	}
};

