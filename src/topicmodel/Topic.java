package topicmodel;

import java.util.ArrayList;
import java.util.Collections;

public class Topic implements Comparable{
	private String name;
	private double value;

	public Topic(){
		name = "";
		value = 0.0;
	}

	public Topic(String n, double v){
		name = n;
		value = v;
	}
	
	@Override
	public String toString(){
		return name + Double.toString(value);
	}
	
	public int compareTo(Object o){
		Topic tmp = (Topic)o;
		if(this.value < tmp.value)
			return 1;
		else if(this.value > tmp.value)
			return -1;
		else
			return 0;
	}
	
	public static void main(String[] args){
		Topic t1 = new Topic("asdf", 1.234);
		Topic t2 = new Topic("qwer", 2.345);
		Topic t3 = new Topic("zxcv", 3.456);
		Topic t4 = new Topic("qaza", 2.789);
		
		ArrayList<Topic> topics = new ArrayList<Topic>();
		topics.add(t1);
		topics.add(t2);
		topics.add(t3);
		topics.add(t4);
		Collections.sort(topics);
		for(int i=0; i<topics.size(); i++)
			System.out.println(topics.get(i).toString());
	}
}