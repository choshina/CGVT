// 0: add(int):boolean; 1: remove(int):boolean; 2: size():int; 
package test.pct_UnsafeSizeListAddRemoveSize ; 

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

import pct.*;

class VTUnsafeSizeListTest { 
  private UnsafeSizeList data ; 
  VTUnsafeSizeListTest () { 
    this.data = new UnsafeSizeList() ; 
  } 
    public boolean vtMethod0(int x1) { return data.add(x1) ; } ; 
    public boolean vtMethod1(int x1) { return data.remove(x1) ; } ; 
    public int vtMethod2() { return data.size() ; } ; 
    public void setScheduler(Scheduler s){
        data.resetScheduler(s);
    }
    }

class TestThread extends Thread { 
  private VTUnsafeSizeListTest data ; 
  private int elemBase ; 
  private TraceRecord[] trace ; 
  private int threadID;
  private Controller controller;
  private int uselen;

  TestThread (String name, VTUnsafeSizeListTest q, TraceRecord[] tr,int tid, Controller c) { 
    super(name) ; 
    this.data = q ; 
    this.elemBase = 0 ; 
     this.trace = tr ; 
    threadID = tid;
    controller = c;
    uselen = 0;
  } 

  public void run() { 
    int len = 500 ; 
    Random r = new Random() ; 
    int add__1; 
    boolean add__ret ; 
    int remove__1; 
    boolean remove__ret ; 
    int size__ret ; 
    for(int i = 0,ej = 0;ej < len;){
        int opThisBlock = r.nextInt(3)+1;
        int[] op = new int[opThisBlock];
        for(int j = 0;j < opThisBlock;j++){
            op[j] = r.nextInt(3);
        }
    controller.uploadThreadInfo(threadID,op);
    data.setScheduler(controller.getScheduler());
    for(int k = i; k < opThisBlock + i;k++){
      LinkedList<ArgType> args = new LinkedList<ArgType> () ; 
      int mid = op[k-i]; 
      switch (mid) { 
      case 0: 
        add__1 = elemBase + r.nextInt(100) ; 
        args.add(new ArgInt(add__1)) ; 
        try { 
          add__ret = data.vtMethod0(add__1) ; 
          trace[k] = new TraceRecord(args, add__ret, mid, false ) ; 
        } catch (Exception e) { 
          trace[k] = new TraceRecord(args, -1, mid, true) ; 
        } 
        break ; 
      case 1: 
        remove__1 = elemBase + r.nextInt(100) ; 
        args.add(new ArgInt(remove__1)) ; 
        try { 
          remove__ret = data.vtMethod1(remove__1) ; 
          trace[k] = new TraceRecord(args, remove__ret, mid, false ) ; 
        } catch (Exception e) { 
          trace[k] = new TraceRecord(args, -1, mid, true) ; 
        } 
        break ; 
      case 2: 
        try { 
          size__ret = data.vtMethod2() ; 
          trace[k] = new TraceRecord(args, size__ret, mid, false ) ; 
        } catch (Exception e) { 
          trace[k] = new TraceRecord(args, -1, mid, true) ; 
        } 
        break ; 
      default :  
      } 
    } 
    controller.handleEnd(threadID);
    i+=opThisBlock;
    ej++;
    uselen = i;
  } 
} 

    public int getUselen(){
        return uselen;
    }
}

class TestingUnsafeSizeListT2L500AddRemoveSize { 
  public static void main(String argv[]) { 
    int tdNum = 2, mdNum = 3, trLen = 1500; 
    VTUnsafeSizeListTest testObj = new VTUnsafeSizeListTest() ; 
    Controller ctl = new Controller(tdNum,mdNum);
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
      out.write("# <MethodIndex> <ReturnValue> <ArgumentValue>\n") ; 
      out.write("2 500 UnsafeSizeList add remove size\n") ; 
        int[] uslen = new int[tdNum];
    for(int w= 0;w < tdNum;w++){
        uslen[w] = thd[w].getUselen();
    }
      for (int i=0; i < tdNum; i++) { 
        out.write("Thread " + i + "\n") ; 
        for (int j=0; j < uslen[i]; j++) out.write(tr[i][j].toString() + "\n") ; 
      } 
      out.close() ; 
    } catch (Exception e) { 
      System.err.println("Error: " + e) ; 
    } 
  } 
} 
