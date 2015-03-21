package miner;

import chinesetool.ChineseSplitTool;

public class Miner {
	
	public static void main(String[] args){
		ChineseSplitTool.parseFile("part_metal.txt", "part_metal.out");
	}
}
