// 0: add(int):boolean; 1: remove(int):boolean; 
package test.BugUnknownList2AddRemove ; 
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

class VTBugUnknownList2Test { 
  private BugUnknownList2 data ; 
  VTBugUnknownList2Test () { 
    this.data = new BugUnknownList2() ; 
  } 
    public boolean vtMethod0(int x1) { return data.add(x1) ; } ; 
    public boolean vtMethod1(int x1) { return data.remove(x1) ; } ; 
} 

class TestThread extends Thread { 
  private VTBugUnknownList2Test data ; 
  private int elemBase ; 
  private TraceRecord[] trace ; 

  TestThread (String name, VTBugUnknownList2Test q, TraceRecord[] tr) { 
    super(name) ; 
    this.data = q ; 
    this.elemBase = 0 ; 
     this.trace = tr ; 
  } 

  public void run() { 
    int len = 500 ; 
    Random r = new Random() ; 
    int add__1; 
    boolean add__ret ; 
    int remove__1; 
    boolean remove__ret ; 
    for (int i = 0; i < len; i++) { 
      LinkedList<ArgType> args = new LinkedList<ArgType> () ; 
      int mid = r.nextInt(2) ; 
      switch (mid) { 
      case 0: 
        add__1 = elemBase + r.nextInt(100) ; 
        args.add(new ArgInt(add__1)) ; 
        try { 
          add__ret = data.vtMethod0(add__1) ; 
          trace[i] = new TraceRecord(args, add__ret, mid, false ) ; 
        } catch (Exception e) { 
          trace[i] = new TraceRecord(args, -1, mid, true) ; 
        } 
        break ; 
      case 1: 
        remove__1 = elemBase + r.nextInt(100) ; 
        args.add(new ArgInt(remove__1)) ; 
        try { 
          remove__ret = data.vtMethod1(remove__1) ; 
          trace[i] = new TraceRecord(args, remove__ret, mid, false ) ; 
        } catch (Exception e) { 
          trace[i] = new TraceRecord(args, -1, mid, true) ; 
        } 
        break ; 
      default :  
      } 
    } 
  } 
} 

class TestingBugUnknownList2T2L500AddRemove { 
  public static void main(String argv[]) { 
    int tdNum = 2, mdNum = 2, trLen = 500; 
    VTBugUnknownList2Test testObj = new VTBugUnknownList2Test() ; 
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
      out.write("2 500 BugUnknownList2 add remove\n") ; 
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
