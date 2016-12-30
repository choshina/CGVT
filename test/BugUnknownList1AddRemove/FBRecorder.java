package test.BugUnknownList1AddRemove;

import java.util.HashMap;

public class FBRecorder {
	
	private HashMap<Integer,CaseInfo> data;
	
	public FBRecorder(){
		data = new HashMap<Integer,CaseInfo>();
	
	}
	
	public void put(int key, CaseInfo c){
		data.put(key, c);
	}
	
	public int get(int key){
		if(data.get(key)== null){
			return 0;
		}else{
			return data.get(key).getCounter();
		}
	}
	
	private void updateCounter(int key){
		data.get(key).add1();
	}
	
	/*
	 * firstly, check if the number of this testcase has been up to the limit.
	 * if not, return true and add 1 to that counter
	 * if it has, according to a random value to decide whether it would be added,
	 * and decrease its probability if it would be added.
	 * 
	 * */
	public boolean check(Testcase t,int dpt){
		int key = t.hash();
		if(get(key) == 0){
			int lmt = (int) (t.getThreadNumber() * (Math.pow(t.getInstructions(),dpt-1)));
			CaseInfo c = new CaseInfo(1,lmt);
			data.put(key, c);
			return true;
		}else{
			int crt = get(key);
			int lmt = data.get(key).getExpected();
			if(crt < lmt){
				updateCounter(key);
				return true;
			}else{
				return false;
			}
		}
	}
	
}

class CaseInfo{
	
	int counter;
	int expected;
	
	public CaseInfo(int a , int b){
		counter = a;
		expected = b;
	}
	
	public int getExpected() {
		// TODO Auto-generated method stub
		return expected;
	}

	public int getCounter(){
		return counter;
	}
	
	public void add1(){
		counter = counter+1;
	}
}
