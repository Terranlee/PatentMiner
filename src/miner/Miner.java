package miner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Miner {
	
	public static void showHelp(){
		System.out.println("1.(w)    Chinese word split, remove stop words");
		System.out.println("2.(k)    Keyword extraction from documents");
		System.out.println("3.(t)    Topic model analysis for documents");
		System.out.println("4.(e)    Build elastic search for documents");
		System.out.println("5.(exit) Quit this program");
	}
	
	public static void main(String[] args){
		System.out.println("This is TextMiner. A toolkit provides the following functions:");
		showHelp();
		while(true){
			try{
				System.out.print(">");
				BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
				String command = bf.readLine();
				if(command.equals("exit"))
					break;
				else if(command.equals("help"))
					showHelp();
				else{
					MinerExec me = new MinerExec(command);
					me.execute();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}
