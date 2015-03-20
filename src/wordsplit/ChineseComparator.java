package wordsplit;

import java.util.Comparator;
import java.text.Collator;

public class ChineseComparator implements Comparator<String>{
	// Chinese comparator
	// use this to convert split words to sorted words
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
