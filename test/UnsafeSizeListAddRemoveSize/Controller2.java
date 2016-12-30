package test.UnsafeSizeListAddRemoveSize;

public class Controller2 {
	class Block{
		Scheduler scheduler;
		boolean[] beginFlag;
		boolean[] endFlag;
	//	boolean fbf;
	//	byte[] beginlock = new byte[0];
		
		int[][] thdInfo;
		
		public Block(){
			beginFlag = new boolean[threadNumber];
			endFlag = new boolean[threadNumber];
			thdInfo = new int[threadNumber][];
		}

		public void setThdInfo(int tid, int[] opList) {
			thdInfo[tid] = opList;
			synchronized(beginFlag){
				beginFlag[tid] = true;
				for(int i = 0;i < threadNumber;i++){
				//	System.out.println("setThdInfo");
				//	System.out.println("beginFlag "+beginFlag[i]);
					if(!beginFlag[i]){
						try {
							beginFlag.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return;
					}
				}
				beginFlag.notifyAll();
				scheduler = new Scheduler(threadNumber,thdInfo,3);
				
				return;
			}
			
		}

		public void handleEnd(int tid) {
			synchronized(endFlag){
				endFlag[tid] = true;
				for(int i = 0;i < threadNumber;i++){
				//	System.out.println("handle End");
					if(!endFlag[i]){
						try {
							endFlag.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
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
	
	private byte[] blkcrtlock = new byte[0];
	private byte[] blkclslock = new byte[0];
	
	public Controller2(int t){
		threadNumber = t;
		blockworking = false;
		blockset = new Block[1000];
		blockIndex = 0;
		
	}
	
	public void uploadThreadInfo(int tid, int[] opList){
		synchronized(blkcrtlock){
	//		System.out.println("blockworking "+blockworking);
			if(!blockworking){
				blockIndex++;
				blockset[blockIndex] = new Block();
				blockworking = true;
			}
		}

		blockset[blockIndex].setThdInfo(tid,opList);
	//	System.out.println("endupload");
	}
	
	public void handleEnd(int tid){
		blockset[blockIndex].handleEnd(tid);
		
		synchronized(blkclslock){
			if(blockworking){
				blockworking = false;
				try {
					blkclslock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	//			System.out.println("endhandleend2");
				return;
			}
			blkclslock.notifyAll();
		}
	//	System.out.println("endhandleend");
	}
	
	public Scheduler getScheduler(){
		return blockset[blockIndex].scheduler;
	}
	
}
