package wgl;

/**
 * @author xavi
 *
 */
public class Linearized {
	static int[] arrayofThread;
	
	private boolean[] arrayofLinear;
	
	private int threadNum;
	public int getThreadNum() {
		return threadNum;
	}

	private int[] traceNum;
	
	private boolean[][] linearThread;
	
	public Linearized(int thr,int[] tra){
		
		int length = 0;
		for(int i = 0;i < thr;i++){
			length+=tra[i];
		}
		arrayofLinear = new boolean[length];
		threadNum = thr;
		traceNum = tra;
		linearThread = new boolean[threadNum][];
		for(int j = 0;j < threadNum;j++){
			linearThread[j] = new boolean[traceNum[j]];
		}
		
	}
	
	public Linearized copy(){
		int length = arrayofLinear.length;
		Linearized re = new Linearized(threadNum,traceNum);
		
		for(int i = 0;i < length;i++){
			re.arrayofLinear[i] = arrayofLinear[i];
		}
		for(int m = 0;m<threadNum;m++){
			for(int n = 0;n < traceNum[m];n++){
				re.linearThread[m][n] = this.linearThread[m][n];
			}
		}
		return re;
	}
	
	
	
	public boolean compare(Linearized l){
		int length = arrayofLinear.length;
		for(int i = 0;i < length;i++){
			if(arrayofLinear[i]!=l.arrayofLinear[i])
				return false;
		}
		return true;
	}
	
	public void modifyLinearByIndex(int index,boolean l){
		arrayofLinear[index] = l;
		int t = arrayofThread[index];
		int count = 0;
		for(int i = 0;i < index;i++){
			if(arrayofThread[i] == t)
				count++;
		}
		linearThread[t][count] = l;
	}
	
//	public boolean readLinearByIndex(int index){
//		return arrayofLinear[index];
//	}
	
	public int length(){
		return arrayofLinear.length;
	}

	public static void setTho(int[] thro) {

		arrayofThread = thro;
	}
	
	public int getFarthestTrue(int threadID){
	
		int remlast = -1;
		for(int i = 0;i < length();i++){

			if(arrayofThread[i]==threadID&&arrayofLinear[i]==false){
				return remlast;
			}else if(arrayofThread[i] == threadID&&arrayofLinear[i]==true){
				remlast = i;
			}
		}
		return remlast;//never occurs
	}
	
	int howManyOnes(){
		int count = 0;
		for(int i = 0;i < length();i++){
			if(arrayofLinear[i]==true)
				count++;
		}
		return count;
	}
	
	
/*	*//**
	 * 和上一个方法的区别在于，这个返回的是方法在本线程的操作中最远的true，上一个是所有的
	 * @param threadID
	 * @return
	 *//*
	public int getlocalfarthesttrue(int threadID){
		int last = 0;
		while(true){
			if(last == traceNum){
				break;
			}
			if(linearThread[threadID][last]==false){
				return last-1;
			}
			last++;
		}
		return traceNum;
	}*/
	
/*	public void formLinearThread(){
		int length = length();
		int[] index = new int[threadNum];
		for(int j = 0;j < length;j++){
			index[j] = 0;
		}
		for(int i = 0;i < length;i++){
			int tt = arrayofThread[i];
			linearThread[tt][index[tt]] = arrayofLinear[i];
			index[tt]++;
		}//可优化，因为当遇到0时后面所有的都为0
		
	}*/
	
	/*public int trueNumber(){
		int c = 0;
		for(int i = 0;i < length();i++){
			if(arrayofLinear[i]==true){
				c++;
			}
		}
		return c;
	}
*/
	/*public boolean shorterthan(Linearized l) {
		boolean re = true;
		for(int i = 0;i< threadNum;i++){
			int m = 0;
			for(;m < traceNum;m++){
				if(this.linearThread[i][m]==false)
					break;
			}
			int n = 0;
			for(;n < traceNum;n++){
				if(l.linearThread[i][n]==false)
					break;
			}
			if(m>n){
				re = false;
				break;
			}
		}
		return re;
	}*/
	
	public String toString(){
		String s="";
		for(int i = 0;i < arrayofLinear.length;i++){
			s+=arrayofLinear[i];
		}
		return s;
	}
	
	public String toStringEachThreads(){
		String s = "(";
		int[] c = new int[threadNum];
		for(int j = 0;j < threadNum;j++){
			c[j] = 0;
		}
		for(int i = 0;i < arrayofLinear.length;i++){
			if(arrayofLinear[i]==true){
				c[arrayofThread[i]]++;
			}
		}
		int k = 0;
		while(true){
			s = s+c[k];
			if(k+1==threadNum){
				s+=")";
				break;
			}else{
				s+=",";
				k++;
			}
		}
		return s;
	}
	
}
