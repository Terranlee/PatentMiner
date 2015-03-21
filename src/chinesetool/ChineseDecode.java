package chinesetool;

import java.io.UnsupportedEncodingException;

public class ChineseDecode {
	//change Chinese encoding from utf8 to GBK
	public static String utf8ToGBK(String utf){
		try{
			String unicode = new String(utf.getBytes(),"UTF-8");   
			String gbk = new String(unicode.getBytes("GBK"));  
			return gbk;
		}catch(UnsupportedEncodingException e){
			System.out.println(e);
			return null;
		}
	}
	
	//change Chinese encoding from GBK to utf8
	public static String GBKToUtf8(String gbk){
		try{
		    char[] c = gbk.toCharArray();  
		    byte[] fullByte = new byte[3*c.length];  
		    for (int i=0; i<c.length; i++) {  
		        String binary = Integer.toBinaryString(c[i]);  
		        StringBuffer sb = new StringBuffer();  
		        int len = 16 - binary.length();
		        for(int j=0; j<len; j++){  
		        	sb.append("0");  
		        }  
		        sb.append(binary);  
		        sb.insert(0, "1110");  
		        sb.insert(8, "10");  
		        sb.insert(16, "10");  
		        fullByte[i*3] = Integer.valueOf(sb.substring(0, 8), 2).byteValue();  
		        fullByte[i*3+1] = Integer.valueOf(sb.substring(8, 16), 2).byteValue();  
		        fullByte[i*3+2] = Integer.valueOf(sb.substring(16, 24), 2).byteValue();  
		    }  
		    return new String(fullByte,"UTF-8");  
		}catch(UnsupportedEncodingException e){
			System.out.println(e);
			return null;
		}
	}
}
