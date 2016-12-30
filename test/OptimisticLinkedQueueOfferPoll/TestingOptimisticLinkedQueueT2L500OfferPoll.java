// 0: offer(int):void; 1: poll():int; 
package test.OptimisticLinkedQueueOfferPoll ; 
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

class VTOptimisticLinkedQueueTest { 
  private OptimisticLinkedQueue data ; 
  VTOptimisticLinkedQueueTest () { 
    this.data = new OptimisticLinkedQueue() ; 
  } 
    public void vtMethod0(int x1) { data.offer(x1); } 
    public int vtMethod1() { return data.poll() ; } ; 
	public void setScheduler(Scheduler s){
		data.resetScheduler(s);
	}
} 

class TestThread extends Thread { 
  private VTOptimisticLinkedQueueTest data ; 
  private int elemBase ; 
  private TraceRecord[] trace ; 
private int threadID;
	private Controller controller;

  TestThread (String name, VTOptimisticLinkedQueueTest q, TraceRecord[] tr,int tid, Controller c) { 
    super(name) ; 
    this.data = q ; 
    this.elemBase = 0 ; 
     this.trace = tr ; 
	threadID = tid;
	controller = c;
  } 

  public void run() { 
    int len = 500 ; 
    Random r = new Random() ; 
    int offer__1; 
    int poll__ret ; 
	

    for (int i = 0; i < len; ) { 
	int opThisBlock = r.nextInt(2)+1;
	int [] op = new int[opThisBlock];
	for(int j = 0; j < opThisBlock;j++){
		op[j] = r.nextInt(2);
	}	
	controller.uploadThreadInfo(threadID,op);
	data.setScheduler(controller.getScheduler());

	for(int k = i; (k < opThisBlock+i)&&(k < len);k++){
      LinkedList<ArgType> args = new LinkedList<ArgType> () ; 
      int mid = op[k-i] ; 
      switch (mid) { 
      case 0: 
        offer__1 = elemBase + r.nextInt(100) ; 
        args.add(new ArgInt(offer__1)) ; 
        try { 
          data.vtMethod0(offer__1) ; 
          trace[k] = new TraceRecord(args, -1, mid, false) ; 
        } catch (Exception e) { 
          trace[k] = new TraceRecord(args, -1, mid, true) ; 
        } 
        break ; 
      case 1: 
        try { 
          poll__ret = data.vtMethod1() ; 
          trace[k] = new TraceRecord(args, poll__ret, mid, false ) ; 
        } catch (Exception e) { 
          trace[k] = new TraceRecord(args, -1, mid, true) ; 
        } 
        break ; 
      default :  
      } 
	}
	controller.handleEnd(threadID);
	i+=opThisBlock;
    } 
  } 
} 

class TestingOptimisticLinkedQueueT2L500OfferPoll { 
  public static void main(String argv[]) { 
    int tdNum = 2, mdNum = 2, trLen = 500; 
    VTOptimisticLinkedQueueTest testObj = new VTOptimisticLinkedQueueTest() ; 
	Controller ctl  = new Controller(2);
    TraceRecord[][] tr = new TraceRecord[tdNum][trLen] ; 
    TestThread[] thd = new TestThread[tdNum] ; 
    for (int i = 0; i < tdNum; i++) { 
      tr[i] = new TraceRecord[trLen] ; 
      thd[i] = new TestThread(Integer.toString(i), testObj, tr[i],i,ctl) ; 
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
      out.write("2 500 OptimisticLinkedQueue offer poll\n") ; 
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
