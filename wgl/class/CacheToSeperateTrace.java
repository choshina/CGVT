package wgl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;

class PriorAndLS implements Comparable<PriorAndLS>{
	
	int prior;
	LinearState ls;
	
	public PriorAndLS(int i, LinearState l){
		
		prior = i;
		ls = l;
	}


	@Override
	public int compareTo(PriorAndLS o) {
		// TODO Auto-generated method stub
		if(this.prior<o.prior){
			return -1;
		}else if (this.prior>o.prior){
			return 1;
		}else{
			return 0;
		}
	}
	
	public String toString(){
		String s = ""+prior+" "+ls.toString2();
		return s;
	}
	
}

public class CacheToSeperateTrace {
	private PriorityQueue<PriorAndLS> sepTrace;
	private HashSet<LinearState> cache;
	
	public CacheToSeperateTrace(Cache c){
		sepTrace = new PriorityQueue<PriorAndLS>();
		cache = c.getCache();
	}
	
	private void processCache(){
		Iterator<LinearState> it = cache.iterator();
		while(it.hasNext()){
			LinearState ls = it.next();
			int p = ls.getLinearized().howManyOnes();
			PriorAndLS pl = new PriorAndLS(p,ls);
			sepTrace.add(pl);
		}
	}
	
	public void output(){
		
		processCache();
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("cacheInfo"));
			while(!sepTrace.isEmpty()){
				PriorAndLS t = sepTrace.poll();
				bw.write(t.toString()+"\n");
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
