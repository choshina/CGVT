package wgl;

import java.util.HashSet;

enum Classname {SET,QUEUE,STACK};


public class Tester {
	
	private HistoryParser hp;
	
	private DoubleLinkedList history;
	
	private DoubleLinkedList historyAnalyzed;
	
	private WGLChecker checker;
	
	private ConfigParser cp;
	
	private Cache cache;
	
	private int threadNum;
	
	private int[] traceNum;
	
	private String classname;
	
	public Tester(String c){
		cp = new ConfigParser(c);
		cp.parse();
		//cache = new HashSet<LinearState>();
		
		test("");
	}
	
	public Tester(String c,String p){
		cp = new ConfigParser(c);
		cp.parse();
		//cache = new HashSet<LinearState>();
		
		test(p);
	}
	

	
	public void test(String p){
	
		String sp = cp.getSpecification();
			
		if(sp.equals("Queue")){
			classname = "Queue";
			hp = new QueueHistoryParser(cp.getTestlog(),cp.getJvmlog());	
			checker = new QueueWGLChecker();
			if(p.equals("")){
				
			}else{
				checker.preprocess(p);
			}
		}else if(sp.equals("Set")){
			classname = "Set";
			hp = new SetHistoryParser(cp.getTestlog(),cp.getJvmlog());
			checker = new SetWGLChecker();
			if(p.equals("")){
				
			}else{
				checker.preprocess(p);
			}
		}else if(sp.equals("Stack")){
			classname = "Stack";
			hp = new StackHistoryParser(cp.getTestlog(),cp.getJvmlog());
			checker = new StackWGLChecker();
		}
		
		threadNum = cp.getThreadNum();

		traceNum = cp.getTraceNum();

		history = hp.parse(threadNum,traceNum);//形成history

		historyAnalyzed = history.copy();
		
		//history.print();

		boolean f = checker.check(history);
		
		//System.out.println("rr");
		System.out.println(f);
		
		cache = checker.deepCopyCache();
		HashSet<CacheOrder> hsco = checker.getcachepath();
		cache.SetCacheOrder(hsco);
	
	}
	
	public DoubleLinkedList getHistory(){
		return historyAnalyzed;
	}
	
	public Cache getCache(){
		//test();
		
		return cache;
	}
	
	public String getClassname(){
		return classname;
	}
	
	public int getOpLength(){
		return history.length()/2;
	}
	
//	public int[] getThreadOrder(){
//		return threadOrder;
//	}
	
	public int getThreadNum(){
		return threadNum;
	}
	
	public int[] getTraceNum(){
		return traceNum;
	}
	
	public static void main(String [] args){
		
		Tester t;
		if(args[0].equals("output")){//output
			t = new Tester(args[1]);
			CacheToSeperateTrace c = new CacheToSeperateTrace(t.getCache());
			c.output(args[2]);
			t.getHistory().outputHistory(args[3]);
		}else if(args.length == 3){//args[0]=test +preprocess;
			t = new Tester(args[1],args[2]);
		}else{// test
			long startTime = System.currentTimeMillis();			
			String cfg = args[1];
			t = new Tester(cfg);
			long endTime = System.currentTimeMillis();
		//	System.out.println("time: "+(endTime - startTime) + "ms");
		}
		
	}
}
