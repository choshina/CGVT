package wgl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ConfigParser {
	
	private String fileName;

	private String specification;

	private int threadNum;

	private int[] traceNum;
	
	private String testlog;
	
	private String jvmlog;

	public ConfigParser(String n) {
		fileName = n;
	}

	public void parse() {

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(fileName)));
			String content = br.readLine();
			specification = content.substring(content.indexOf(':') + 1);
			content = br.readLine();
			threadNum = Integer.parseInt(content.substring(content.indexOf(':')+1));
			traceNum = new int[threadNum];
			for(int i = 0;i < threadNum;i++){
				content = br.readLine();
				int tNum = Integer
						.parseInt(content.substring(content.indexOf(':')+1));
				traceNum[i] = tNum; 
			}
			
			
			
			content = br.readLine();
			testlog = content;
			content = br.readLine();
			jvmlog = content;
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getSpecification() {
		return specification;
	}

	public int getThreadNum() {
		return threadNum;
	}

	public int[] getTraceNum() {
		return traceNum;
	}
	
	public String getTestlog(){
		return testlog;
	}
	
	public String getJvmlog(){
		return jvmlog;
	}

}
