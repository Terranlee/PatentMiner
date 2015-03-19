package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import edu.thu.keyword.dao.DAO;
import edu.thu.keyword.main.KeywordsExtracter;

public class Main {
	
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
			Integer round = 0;
			while(true){
				try {
					content = reader.readLine();
					if(content == null){
						break;
					}
					String keyword = KeywordsExtracter.ExtractKey("", content, 5);
					writer.write("文本: \n" + content + "\n关键词: \n" + keyword + "\n");
					if(round % 100 == 0){
						System.out.println("Round " + round.toString() + ": " + keyword);
					}
				}catch(Exception e){
					System.out.println("Exception");
					continue;
				}finally{
					round += 1;
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
	
	public static void main(String[] args) {
		long start=System.currentTimeMillis();
		
		//KeywordsExtracter ek=new KeywordsExtracter();
//		String t = "动画片的跨文化传播探析——以《中华小子 《》三国演义》为例,电视动画;;中华小子;;三国演义;;跨文化传播";
//		String c = "在中国动画片努力参与国际化竞争,实施\"走出去\"战略之时,探索《中华小子》与《三国演义》的跨文化传播策略对当前动漫产业进一步发展具有重要的意义。在跨文化传播过程中,只有那些努力发掘本土文化资源,并进行传统文化内容的现代化再创造,以独特的东方韵味与意境营造区别于气势宏伟的美国动画及唯美风格的日本动画,以民族文化与美学意境为根基并巧妙地编入国际性符码,在弘扬特定民族文化基础上进行了本土文化与国际文化的\"双重编码\"的动画产品,才能够在国际文化市场上获得市场效益与文化传播效果的实现。";
		
//		String t = "应用化学省级品牌专业的建设与实践";
//		String c = "从人才培养方案、课程建设、教材建设、师资队伍建设、实验条件的改善及产学研结合等方面介绍了应用化学品牌专业的建设思路及取得的成绩。";
		
		System.out.println("check stopwords :" + DAO.isInStopwords("提高"));
//		String keyword = KeywordsExtracter.ExtractKey(t,c,10);
		Main m = new Main();
		m.extractKey("patentdata/newenergy.txt", "patentdata/newenergy-key.txt");
		m.extractKey("patentdata/metal.txt", "patentdata/metal-key.txt");
		long end=System.currentTimeMillis();
		int timeMilliseconds = (int) (end - start);
		System.out.println("\n" + "time: " + (timeMilliseconds / 1000f) + " seconds");
	}
//	test :false
//	WordSegConll Root Path :/home/yu/Script/java/News_summary/bin/
//	Init Done!
//	导入用户词个数80158
//	:品牌专业,应用化学,师资,省级,产学研,课程,
//
//	time: 73.135 seconds
}
