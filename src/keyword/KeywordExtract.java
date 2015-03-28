package keyword;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import edu.thu.keyword.dao.DAO;
import edu.thu.keyword.main.KeywordsExtracter;

public class KeywordExtract {
	
	private void extractKey(String infilename, String outfilename){
		File infile = null;
		File outfile = null;
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try{
			infile = new File(infilename);
			outfile = new File(outfilename);
			reader = new BufferedReader(new FileReader(infile));
			writer = new BufferedWriter(new FileWriter(outfile));
			String content = "";
			Integer round = new Integer(0);
			while(true){
				try {
					content = reader.readLine();
					if(content == null){
						break;
					}
					String keyword = KeywordsExtracter.ExtractKey("", content, 5);
					writer.write("文本: \n" + content + "\n关键词: \n" + keyword + "\n");
					if(round.intValue() % 100 == 0){
						System.out.println("Round " + round.toString() + ": " + keyword);
					}
				}catch(Exception e){
					System.out.println("Exception");
					continue;
				}finally{
					round = new Integer(round.intValue() + 1);
				}
			}
			}catch(Exception e){
				System.out.println("Exception");
			}finally{
				try{
					reader.close();
					writer.close();
				}catch(Exception e){
					System.out.println("Exception");
				}
			}
		}
	
	public void execute(){
		
	}
	
	public static void main(String[] args) {
		long start=System.currentTimeMillis();
		System.out.println("check stopwords :" + DAO.isInStopwords("提高"));
		KeywordExtract k = new KeywordExtract();
		k.extractKey("patentdata/newenergy.txt", "patentdata/newenergy-key.txt");
		k.extractKey("patentdata/metal.txt", "patentdata/metal-key.txt");
		long end=System.currentTimeMillis();
		int timeMilliseconds = (int) (end - start);
		System.out.println("\n" + "time: " + (timeMilliseconds / 1000f) + " seconds");
	}
}
