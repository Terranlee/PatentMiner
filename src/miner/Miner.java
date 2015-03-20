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
		//String content = "��ʵ�������ṩһ�ֵ���䣬���ٰ��������ź���ʾ��壬��ʾ���װ�ڱ������ϲ��İ��������仹���������װ�ã�����ʾ����һ�ˣ��ͽ���װ�ã�����ʾ������һ�ˣ�����ʾ���װ�����������ŵİ����������װ�ð���λ����ʾ���ı��������һ����צ����λ�ڱ����Ű�����Ӧλ�õ�����һ�����ݣ���צ������������Ͻ���ʾ����һ��װ�������������ϡ�����װ�ð������̼����ӱ����ŵ��ڲ��������Ų������ʾ���ı��棬�ӱ��潫��ʾ���װ���������ϡ�����ʾ��尲װ�Ͳ�ж���ס���ݣ��Ӷ���Ч��߱����װ���ԣ������ڱ�����ճ�ά�����������ң�����ʾ���������ۣ����Ҳ���Ӱ�����ĸ���Ч����";
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
