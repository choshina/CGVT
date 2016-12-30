// 0: push(int):void; 1: pop():int; 
package test.TreiberStackPushPop ; 
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

class VTTreiberStackTest { 
  private TreiberStack data ; 
  VTTreiberStackTest () { 
    this.data = new TreiberStack() ; 
  } 
    public void vtMethod0(int x1) { data.push(x1); } 
    public int vtMethod1() { return data.pop() ; } ; 
} 

class TestThread extends Thread { 
  private VTTreiberStackTest data ; 
  private int elemBase ; 
  private TraceRecord[] trace ; 

  TestThread (String name, VTTreiberStackTest q, TraceRecord[] tr) { 
    super(name) ; 
    this.data = q ; 
    this.elemBase = 0 ; 
     this.trace = tr ; 
  } 

  public void run() { 
    int len = 2000 ; 
    Random r = new Random() ; 
    int push__1; 
    int pop__ret ; 
    for (int i = 0; i < len; i++) { 
      LinkedList<ArgType> args = new LinkedList<ArgType> () ; 
      int mid = r.nextInt(2) ; 
      switch (mid) { 
      case 0: 
        push__1 = elemBase + r.nextInt(100) ; 
        args.add(new ArgInt(push__1)) ; 
        try { 
          data.vtMethod0(push__1) ; 
          trace[i] = new TraceRecord(args, -1, mid, false) ; 
        } catch (Exception e) { 
          trace[i] = new TraceRecord(args, -1, mid, true) ; 
        } 
        break ; 
      case 1: 
        try { 
          pop__ret = data.vtMethod1() ; 
          trace[i] = new TraceRecord(args, pop__ret, mid, false ) ; 
        } catch (Exception e) { 
          trace[i] = new TraceRecord(args, -1, mid, true) ; 
        } 
        break ; 
      default :  
      } 
    } 
  } 
} 

class TestingTreiberStackT2L2000PushPop { 
  public static void main(String argv[]) { 
    int tdNum = 2, mdNum = 2, trLen = 2000; 
    VTTreiberStackTest testObj = new VTTreiberStackTest() ; 
    TraceRecord[][] tr = new TraceRecord[tdNum][trLen] ; 
    TestThread[] thd = new TestThread[tdNum] ; 
    for (int i = 0; i < tdNum; i++) { 
      tr[i] = new TraceRecord[trLen] ; 
      thd[i] = new TestThread(Integer.toString(i), testObj, tr[i]) ; 
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
      out.write("2 2000 TreiberStack push pop\n") ; 
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
