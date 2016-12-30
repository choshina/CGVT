// 0: push(int):void; 1: pop():int; 
package test.KStackPushPop ; 
import src.concurrency.* ; 

import jtrace.ArgType ; 
import jtrace.ArgInt ; 
import jtrace.ArgBoolean ; 
import jtrace.ArgChar ; 
import jtrace.ArgString ; 
import jtrace.TraceRecord ; 
import java.io.* ;
import java.util.Random ;
import java.util.List ;
import java.util.LinkedList ;

class VTKStackTest { 
  private KStack data ; 
  VTKStackTest () { 
    this.data = new KStack() ; 
  } 
    public void vtMethod0(int x1) { data.push(x1); } 
    public int vtMethod1() { return data.pop() ; } ; 
	public void setScheduler(Scheduler s){
		data.resetScheduler(s);
	}
} 

class TestThread extends Thread { 
  private VTKStackTest data ; 
  private int elemBase ; 
  private TraceRecord[] trace ; 
	private int threadID;
	private Controller controller;

  TestThread (String name, VTKStackTest q, TraceRecord[] tr, int tid, Controller c) { 
    super(name) ; 
    this.data = q ; 
    this.elemBase = 0 ; 
     this.trace = tr ; 
	threadID = tid;
	controller = c;
  } 

  public void run() { 
    int len = 50 ; 
    Random r = new Random() ; 
    int push__1; 
    int pop__ret ; 
    for (int i = 0; i < len; ) {
	int opThisBlock = r.nextInt(1)+1;
	int []op = new int[opThisBlock];
	for(int j = 0 ; j < opThisBlock;j++){
		op[j] = r.nextInt(2);
	}
	controller.uploadThreadInfo(threadID,op);
	data.setScheduler(controller.getScheduler());
	
	for(int k = i;(k < opThisBlock+i)&&(k < len);k++){
 
      LinkedList<ArgType> args = new LinkedList<ArgType> () ; 
      int mid = op[k-i] ; 
      switch (mid) { 
      case 0: 
        push__1 = elemBase + r.nextInt(100) ; 
        args.add(new ArgInt(push__1)) ; 
        try { 
          data.vtMethod0(push__1) ; 
          trace[k] = new TraceRecord(args, -1, mid, false) ; 
        } catch (Exception e) { 
          trace[k] = new TraceRecord(args, -1, mid, true) ; 
        } 
        break ; 
      case 1: 
        try { 
          pop__ret = data.vtMethod1() ; 
          trace[k] = new TraceRecord(args, pop__ret, mid, false ) ; 
        } catch (Exception e) { 
          trace[k] = new TraceRecord(args, -1, mid, true) ; 
        } 
        break ; 
      default :  
      } 
	}
	controller.handleEnd(threadID);
	i += opThisBlock;
    } 
  } 
} 

class TestingKStackT2L50PushPop { 
  public static void main(String argv[]) { 
    int tdNum = 2, mdNum = 2, trLen = 50; 
    VTKStackTest testObj = new VTKStackTest() ; 
    TraceRecord[][] tr = new TraceRecord[tdNum][trLen] ; 
    TestThread[] thd = new TestThread[tdNum] ; 
	Controller ctl = new Controller(2);
    for (int i = 0; i < tdNum; i++) { 
      tr[i] = new TraceRecord[trLen] ; 
      thd[i] = new TestThread(Integer.toString(i), testObj, tr[i],i ,ctl) ; 
      thd[i].start() ; 
    } 
    try { 
      for (int i = 0; i < tdNum; i++)  
      thd[i].join() ; 
    } catch (Exception e) { 
      e.printStackTrace() ; 
    } 
    String fn = argv[0] ; 
    try { 
      FileWriter fw = new FileWriter(fn) ; 
      BufferedWriter out = new BufferedWriter(fw) ; 
      out.write("# <MethodIndex> <ArgumentValue> <ReturnValue>\n") ; 
      out.write("2 50 KStack push pop\n") ; 
      for (int i=0; i < tdNum; i++) { 
        out.write("Thread " + i + "\n") ; 
        for (int j=0; j < tr[i].length; j++) out.write(tr[i][j].toString() + "\n") ; 
      } 
      out.close() ; 
    } catch (Exception e) { 
      System.err.println("Error: " + e) ; 
    } 
  } 
} 
