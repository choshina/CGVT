package test.OptimisticLinkedQueueOfferPoll;

import java.util.Random;

public class Scheduler {
	
	private int threadNumber;

	
	private int bugDepth;
	
	private S s;
	
	private int[] priority;
	
	private int[] kPoint;
	
	public Scheduler(int n){
		threadNumber = n;
	}
	
	public Scheduler(int n,int k[], int d){
		threadNumber = n;
		bugDepth = d;
		
		int sum = 0;
		for(int u = 0;u < n;u++){
			
			sum+=k[u];
		}
		
		s = new S(k,n);
		priority = new int[threadNumber];
		kPoint = new int[d-1];
		
		Random r = new Random();
		
		int whichOne = r.nextInt(n);
		int c = n;
		int[] temp = new int[n-1];
		temp[0] = 1;//1215find a bug here
		for(int y = 1;y < n-1;y++){
			temp[y] = temp[y]*(y+1);
		}
		for(int e = 0;e< n;e++){
			priority[e] = d;
		}
		
		while(c > 1){
			int quetient = whichOne/temp[c-2];
			priority[quetient] = d+c-1;
			whichOne = whichOne%temp[c-2];
			c--;
		}
		
		//System.out.println(sum);
		for(int j = 0;j < d-1;j++){
			kPoint[j] = r.nextInt(sum)+1;
		}
	}
	
	public void schedule(){
		if(threadNumber == 1)
			return;
		
		if(shouldSchedule2()){
			Thread.currentThread().yield();//2-thread specific
		}
		//System.out.println("yeah");
	}
	
	
	private boolean shouldSchedule2() {
		// TODO Auto-generated method stub
		Random r = new Random();
		int a = r.nextInt(100);
		if (a <50){
			return true;
		}else{
			return false;
		}
	}

	private boolean shouldSchedule(){
		int t = 0;
		if(!s.toTheEnd()){
			t = pickMax();
			s.add(t);
			
			for(int i = 0;i < bugDepth-1; i++){
				if(s.currentLength() == kPoint[i]){
					
					int prev = priority[t];
					priority[t] = bugDepth - (i+1);
					if(!(prev == priority[t])){
						return true;
					}
					
				}
			}
			if(s.threadEnd(t)){
				return true;
			}
		}else{
			
		}
		return false;
	}
	
	private int pickMax(){
		int max = -1;
		int result = -1;
		for(int i = 0;i < threadNumber;i++){
			if(priority[i] > max){
				max = priority[i];
				result = i;
			}
		}
		return result;
	}
	
	
}

class S{
	
	private int length;
	
	private int[] schedule;

	
	private int[] thdInstru;
	
	public S(int[] l,int n){
		
		thdInstru = l;
		int sum = 0;
		for(int j = 0;j< n;j++){
			sum+=l[j];
		}
		length = sum;
		schedule = new int[length];
		for(int i = 0;i < length;i++){
			schedule[i] = -1;
		}
	}
	
	public boolean toTheEnd(){

		
		/*int[] tThread = new int[threadNumber];
		for(int j = 0;j < threadNumber;j++){
			tThread[j] = 0;
		}
		int i = 0;
		while(schedule[i]!=-1){
		
			tThread[schedule[i]]++;
			i++;
		}
		
		for(int k = 0;k< threadNumber;k++){
			if(thdInstru[k]!=schedule[k])
				return false;
		}
		return true;*/
		
		if(currentLength() == length){
			return true;
		}else
			return false;
		
		
		
	}
	
	public boolean threadEnd(int t){
		
		int tsum = 0;
		int i = 0;
		while(schedule[i]!=-1){
			if(schedule[i] == t)
				tsum++;
			i++;
			if(!(i<length)){
				break;
			}
		}
		if(tsum == thdInstru[t])
			return true;
		else
			return false;
	}
	
	public int currentLength(){
		int result = 0;
		for(int i = 0;i < length;i++){
			if(schedule[i]!=-1)
				result++;
			else
				return result;
		}
		return result;
	}
	
	public void add(int next){
		int i = currentLength();
		schedule[i] = next;
	}
	
}
