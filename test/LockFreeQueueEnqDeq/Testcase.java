package test.LockFreeQueueEnqDeq;

public class Testcase {
	
	private int threadNumber;

	
	private int[][] thdInfo;
	
	
	
	public Testcase(int[][] n){
		threadNumber = n.length;
		thdInfo = n;
	}
	
	
	public int hash(){
		int sum = 0;
		for(int i = 0;i < threadNumber;i++){
			sum = sum*10+thdInfo[i].length;
			for(int j = 0; j < thdInfo[i].length;j++){
				sum = sum*10+thdInfo[i][j];
			}
		}
		return sum;
	}
	
	public int getThreadNumber(){
		return threadNumber;
	}
	
	public int getInstructions(){
		int sum = 0;
		for(int i = 0;i < threadNumber;i++){
			for(int j = 0; j < thdInfo[i].length;j++){
				sum+=thdInfo[i][j];
			}
		}
		return sum;
	}
	
}
