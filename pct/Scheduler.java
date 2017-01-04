package pct;

import java.util.Random;

public class Scheduler {
	
	private boolean open;
	
	private Scheduling current;
	
	private int tid;
	
	private Random random;
	
	private int base;
	private int depth;

	public Scheduler(boolean o){
		open = o;
		random = new Random();
	}
	
	public Scheduler(boolean o, int b, int d){
		open = true;
		base = b;
		depth = d;
		tid = 0;
		random = new Random();
		current = new Scheduling();
	}
	
	public void schedule(){
		if(!open)
			return;
		current.add(tid);
		if(shouldSchedule()){
			tid = 1-tid;
			Thread.currentThread().yield();
		}
		
	}
	
	private boolean shouldSchedule(){
		int r = random.nextInt(100);
		if(r < depth*base)
			return true;
		else
			return false;
	}
}
