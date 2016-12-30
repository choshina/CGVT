package schedule;

import java.util.Random;

import benchmark.BugUnknownList4;


public class TestScheduler {
	public static void main(String[] args){
		VTLockFreeList ds = new VTLockFreeList();
		ds.add(1);
		
		Controller ctl = new Controller(2);
		
		MyThread mt1 = new MyThread("Thread1",ds,0,ctl);
		MyThread mt2 = new MyThread("Thread2",ds,1,ctl);
		mt1.start();
		mt2.start();
		try {
			mt1.join();
			mt2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println(mt1.result);
		System.out.println(mt2.result);
		
	}
}

class MyThread extends Thread{
	
	private VTLockFreeList data;
	
	public boolean result;
		
	private Controller controller;
	
	private int traceNumber;
	
	private int threadID;
	
	public MyThread(String name, VTLockFreeList ds,int id,Controller c){
		super(name);
		data = ds;
		traceNumber = 10;
		threadID = id;
		controller = c;
		
	}
	public void run(){
		Random r = new Random();
		
		for(int i = 0;i < traceNumber;i++){
			int opThisBlock = r.nextInt(1)+1;
			int[] op = new int[opThisBlock];
			for(int j = 0;j < opThisBlock;j++){
				op[j] = r.nextInt(2);
				
			}
			boolean f = controller.uploadThreadInfo(threadID,op);
			if(!f)
				continue;
		
			data.setScheduler(controller.getScheduler());
			for(int k = 0;k< opThisBlock;k++){
				switch(op[k]){
				case 0:
					int add_1 = r.nextInt(100);
					boolean add_ret = data.add(add_1);
					break;
				case 1:
					int remove_1 = r.nextInt(100);
					boolean remove_ret = data.remove(1);
					break;
				}
			}
			
			controller.handleEnd(threadID);
		}
	}

}

class VTLockFreeList{
	
	private BugUnknownList4 lfl;
	
	public VTLockFreeList(){
		lfl = new BugUnknownList4();
	}
	
	public boolean add(int item){
		return lfl.add(item);
	}
	
	public boolean remove(int item){
		return lfl.remove(item);
	}
	
	public void setScheduler(Scheduler s){
		lfl.resetScheduler(s);
	}

	
}