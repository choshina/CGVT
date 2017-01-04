package pct;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class OpInstruction {
	class OpIns{
		//int opType;
		int seq;
		int loop;
		int ifi;
		
		public OpIns(int b,int c,int d){
			//opType = a;
			seq = b;
			loop = c;
			ifi = d;
		}
	}
	
	private Map<Integer,OpIns> opi;
	
	private int[] percent;
	
	public OpInstruction(int n, String filename){
		opi = new HashMap<Integer,OpIns>();
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
											new FileInputStream(filename)));
			
			for(int i = 0;i < n;i++){
				String d1 = br.readLine();
				int type = Integer.parseInt(d1.substring(d1.indexOf(":")+1));
				String d2 = br.readLine();
				int tseq = Integer.parseInt(d2.substring(d2.indexOf(":")+1));
				String d3 = br.readLine();
				int tloop = Integer.parseInt(d3.substring(d3.indexOf(":")+1));
				String d4 = br.readLine();
				int tifi = Integer.parseInt(d4.substring(d4.indexOf(":")+1));
				
				OpIns o = new OpIns(tseq,tloop,tifi);
				opi.put(type, o);
			}
			
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	public int calculate(int[][] thdInfo){
		int sum = 0;
		int seqsum = 0;
		int loopsum = 0;
		int ifsum = 0;
		for(int i = 0;i < thdInfo.length;i++){
			for(int j = 0;j < thdInfo[i].length;j++){
//				System.out.println(thdInfo[i][j]);
				seqsum += opi.get(thdInfo[i][j]).seq;
				loopsum += opi.get(thdInfo[i][j]).loop;
				ifsum += opi.get(thdInfo[i][j]).ifi;
			}
		}
		sum = seqsum+loopsum*3+ifsum;
		System.out.println(sum);
		int base = 100/(sum);
		return base;
	}
	
	
}
