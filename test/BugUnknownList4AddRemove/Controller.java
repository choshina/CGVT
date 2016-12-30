package test.BugUnknownList4AddRemove ; 

public class Controller {
	
	private int threadNumber;
	
	//private ThreadInfo[] thdInfo;
	private int[][] thdInfo;
	
	private int[] endFlag;//-1:can't end 1: OK to end
	
	private int[] beginFlag;//-1:not ready; 1:ready to begin
	
	public byte[] res = {0};
	
	public byte[] eres = {1};
		
	private Scheduler s;
	
	private FBRecorder fb;
	
	private boolean fbf;
	
	private int depth;

	public Controller(int n){
		threadNumber = n;
		
		endFlag = new int[n];
		
		beginFlag = new int[n];
		
		thdInfo = new int[n][];
		
		for(int i = 0;i < n;i++){
			
			endFlag[i] = -1;
			beginFlag[i] = -1;
		}
		
		fb = new FBRecorder();
		
		fbf = false;
		
		depth = 2;
		
	}
	
	/*
	 * to upload the information of thread i. and modify the begin flag, to say that
	 * the data has been uploaded. 
	 * as each thread hold its own beginflag domain, no data race would happen.
	 * 
	 * */
	public boolean uploadThreadInfo(int id, int[] opList){
		int i = id;
		
		try{
			synchronized(res){
				thdInfo[i] = opList;
				
				beginFlag[i] = 1;
				
				if(couldBegin()){//feedback version
					
					Testcase t = new Testcase(thdInfo);
					fbf = fb.check(t,depth);
					if(!fbf)
						return false;
					s = new Scheduler(threadNumber,getNumInstru(),3);
					res.notify();
					return true;
					
				}else{
					res.wait();
					return fbf;
				}
				
				/*if(couldBegin()){//non-feedback version
					s = new Scheduler(threadNumber, getNumInstru(), 3);
					res.notify();
					
				}else{
					res.wait();
				}*/
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		return false;
		
		
	}
	
	private int[] getNumInstru(){
		int[] w = new int[threadNumber];
		for(int i = 0;i < threadNumber;i++){
			w[i] = 0;
			for(int j = 0;j < thdInfo[i].length;j++){
				w[i] = w[i] + getNumInstructions(thdInfo[i][j]);
			//	System.out.println("w[i]"+w[i]);
			}
			
		}
		return w;
	}
	
	private int getNumInstructions(int op){
		int result = 0;
		switch(op){
		case 0:
			result = 3;
			break;
		case 1:
			result = 3;
			break;
		}
		return result;
	}
	
	
	/*
	 * if this returns true, it says that the information of all the threads has been
	 * uploaded, and the block could begin.
	 * 
	 * */
	private boolean couldBegin(){
		for(int i = 0;i < threadNumber;i++){
			if(beginFlag[i]!=1){
				return false;
			}
		}
		return true;
	}
	
	/*
	 * if the @toTheEnd could return true, this can also return true;  
	 * 
	 * */
	public boolean couldEnd(){
		for(int i = 0;i < threadNumber;i++){
			if(endFlag[i]!=1){
				return false;
			}
		}
		return true;
	}
	
	public void handleEnd(int i){
		try{
			synchronized(eres){
				endFlag[i] = 1;
				
				if(couldEnd()){
					eres.notify();
				}else{
					eres.wait();
				}
			}
			
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}
	
	public Scheduler getScheduler(){
		return s;
	}
	
	
	
	
}

