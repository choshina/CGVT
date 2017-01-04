package pct;

import java.util.LinkedList;
import java.util.Queue;

public class Scheduling {
	
	Queue<Integer> q;
	
	public Scheduling(){
		q = new LinkedList<Integer>();
	}
	
	public void add(int v){
		q.offer(v);
	}
	
	public int length(){
		return q.size();
	}
	
	public int deq(){
		return q.poll();
	}
	
	public int peek(){
		return q.peek();
	}
}
