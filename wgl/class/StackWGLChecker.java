package wgl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class StackWGLChecker extends WGLChecker{
	
	public StackWGLChecker(){
		sTemp = new CloneableStack();
		sequentialSpec = new CloneableStack();
	}
	

	@Override
	public void preprocess(String p) {
		// TODO Auto-generated method stub
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader
					(new FileInputStream(p)));
		
			String line = "";
			while((line = br.readLine())!=null){
				int sp = line.indexOf(" ");
				int type = Integer.parseInt(line.substring(0, sp));
				int argu = Integer.parseInt(line.substring(sp+1));
				StackOperation so = null;
				if(type == 0){
					so = new StackOperationPush(type,argu);
				}else if(type == 1){
					so = new StackOperationPop(type);
				}
				
				sequentialSpec.apply(so);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
