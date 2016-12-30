package wgl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public abstract class HistoryParser {
	class TmpOp{
		int opT;
		int start;
		int end;
		
		int arg;
		int ret;
		TmpOp(int o,int s,int e,int a,int r){
			opT = o;
			start = s;
			end = e;
			arg = a;
			ret = r;
		}
	}
	
	protected DoubleLinkedList dll;
	
	protected int threadNum;
	protected int[] traceNum;
	protected TmpOp[][] s;
	protected String testlog;
	protected String jvmlog;
	
	public HistoryParser(String t,String j){
		dll = new DoubleLinkedList();
		testlog = t;
		jvmlog = j;
	}
	
	protected void readFile(){
		
		try{
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(jvmlog)));
		BufferedReader br2 = new BufferedReader(new InputStreamReader(
				new FileInputStream(testlog)));
		String data = null;
		String opData = null;
		br.readLine();
		br.readLine();
		br2.readLine();
		br2.readLine();
		for(int i = 0;i < threadNum;i++){
			data = br.readLine();
			
			opData = br2.readLine();
			
			int tId = Integer.parseInt(data.substring(data.indexOf(' ')+1));//一开始写错了
			
		
			for(int j = 0; j < traceNum[i];j++){
				
				data = br.readLine();
				int fb = data.indexOf(' ');
				int sb = data.indexOf(' ', fb+1);
				
				
				
				opData = br2.readLine();
				int fb2 = opData.indexOf(' ');
				int sb2 = opData.indexOf(' ',fb2+1);
				//System.out.println(fb2);
				//System.out.println(sb2);
				
				
				int temp_arg = 0;
				int temp_ret = 0;
				if(sb2 == -1){
					temp_arg = -1;
					temp_ret = Integer.parseInt(opData.substring(fb2+1));
				}else{
					temp_arg = Integer.parseInt(opData.substring(sb2+1));
					temp_ret = Integer.parseInt(opData.substring(fb2+1,sb2));
				}
			
				
				s[tId][j] = new TmpOp(Integer.parseInt(data.substring(0, fb)),
						Integer.parseInt(data.substring(fb+1,sb)),Integer.parseInt(data.substring(sb+1)),
						temp_arg,temp_ret);
				
			}
		}
		br.close();
		br2.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

	
	public DoubleLinkedList parse(int threNum,int[] traNum){
		threadNum = threNum;
		traceNum = traNum;
		dll.setThreadNum(threNum);
		dll.setTraceNum(traNum);
		s = new TmpOp[threadNum][];
		for(int i = 0;i < threadNum;i++){
			s[i] = new TmpOp[traNum[i]];
		}
		readFile();
		formHistory();
		
		return dll;
	}
	


	protected abstract void formHistory();
	
	
}
