package pct;

public class Controller {
	
	class Block{
		int blockID;
		Scheduler scheduler;
		boolean[] beginFlag;
		boolean[] endFlag;
		
		int[][] thdInfo;
		
		public Block(int i){
			blockID = i;
			beginFlag = new boolean[threadNumber];
			endFlag = new boolean[threadNumber];
			thdInfo = new int[threadNumber][];
		}

		public void setThdInfo(int tid, int[] opList) {
			thdInfo[tid] = opList;
			synchronized(beginFlag){
				beginFlag[tid] = true;
				for(int i = 0;i < threadNumber;i++){
					
					if(!beginFlag[i]){
						try {
							beginFlag.wait();
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
						return;
					}
				}
				
				//Testcase t = new Testcase(thdInfo);
				//boolean f = fb.check(t, 3);
				
				int base = opInstruction.calculate(thdInfo);
			
				scheduler = new Scheduler(true,base,3);
				beginFlag.notifyAll();
				
				
				return;
			}
			
		}

		public void handleEnd(int tid) {
			synchronized(endFlag){
				endFlag[tid] = true;
				for(int i = 0;i < threadNumber;i++){
					
					if(!endFlag[i]){
						try {
							endFlag.wait();
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
						return;
					}
				}
				endFlag.notifyAll();
				return;
			}
			
		}
		
	
		
		
	}
	
	private int threadNumber;
	
	private boolean blockworking;
	
	private Block[] blockset;

	private int blockIndex;
	
	private OpInstruction opInstruction;
	
	//private FBRecorder fb;
	
	private byte[] blkcrtlock = new byte[0];
	private byte[] blkclslock = new byte[0];
	
	public Controller(int t,int nop){
		threadNumber = t;
		blockworking = false;
		blockset = new Block[1000];
		blockIndex = 0;
		opInstruction = new OpInstruction(nop,"schedref");
		//fb = new FBRecorder();
	}
	
	public void uploadThreadInfo(int tid, int[] opList){
		synchronized(blkcrtlock){
			
			if(!blockworking){
				blockIndex++;
				blockset[blockIndex] = new Block(blockIndex);
				blockworking = true;
			}
		}

		blockset[blockIndex].setThdInfo(tid,opList);
		
	}
	
	public void handleEnd(int tid){
		blockset[blockIndex].handleEnd(tid);
		
		synchronized(blkclslock){
			if(blockworking){
				blockworking = false;
				try {
					blkclslock.wait();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			
				return;
			}
			blkclslock.notifyAll();
		}
	
	}
	
	public Scheduler getScheduler(){
		return blockset[blockIndex].scheduler;
	}
	
}
