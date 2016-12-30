// 0: add(int):boolean; 1: remove(int):boolean; 
package test.UnsafeSizeListAddRemoveSize ; 


import jtrace.ArgType;
import jtrace.ArgInt;
import jtrace.ArgBoolean;
import jtrace.ArgChar;
import jtrace.ArgString;
import jtrace.TraceRecord;
import java.io.* ;
import java.util.Random ;
import java.util.List ;
import java.util.LinkedList ;


class VTUnsafeSizeListTest { 
  private UnsafeSizeList data ; 
  VTUnsafeSizeListTest () { 
    this.data = new UnsafeSizeList() ; 
  } 
    public boolean vtMethod0(int x1) { return data.add(x1) ; } ; 
    public boolean vtMethod1(int x1) { return data.remove(x1) ; } ;
    public int vtMethod2(){return data.size();}
    
	public void setScheduler(Scheduler s){
		data.resetScheduler(s);
	}
} 

class TestThread1 extends Thread { 
  private VTUnsafeSizeListTest data ; 
  private int elemBase ; 
  private TraceRecord[] trace ; 
	private int threadID;
	private Controller2 controller2;
	private int uselen;

  TestThread1 (String name, VTUnsafeSizeListTest q, TraceRecord[] tr, int tid,Controller2 c) { 
    super(name) ; 
    this.data = q ; 
    this.elemBase = 0 ; 
     this.trace = tr ; 
	threadID = tid;
	controller2 = c;
	uselen = 0;
  } 

  public void run() { 
    int len = 300 ; 
    Random r = new Random() ; 
    int add__1; 
    boolean add__ret  ; 
    int remove__1; 
    boolean remove__ret ;
    int size__ret;
	
	
 
    for (int i = 0,ej = 0; ej < len; ) {
  //  System.out.println(threadID);	
	int opThisBlock = 1;
	
	int[] op = new int[opThisBlock];
	for(int j = 0; j < opThisBlock;j++){
		op[j]= 2;
	}
	controller2.uploadThreadInfo(threadID,op);
	data.setScheduler(controller2.getScheduler());

	for(int k = i; (k < opThisBlock+i);k++){
		
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
          e.printStackTrace();
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
          System.err.println(e);
        } 
        
        break ;
        
      case 2: 
        //  remove__1 = -2 ; 
         // args.add(new ArgInt(remove__1)) ; 
          try { 
            size__ret = data.vtMethod2() ; 
            trace[k] = new TraceRecord(args, size__ret, mid, false ) ; 
          } catch (Exception e) { 
            trace[k] = new TraceRecord(args, -1, mid, true) ;
            System.err.println(e);
          } 
          
          break ;
      default :  
      } 
	}
	controller2.handleEnd(threadID);
//	System.out.println("i   "+threadID+ i);
	i+= opThisBlock;
	uselen = i;
	ej++;
    } 
  }
  
  public int getUselen(){
	  return uselen;
  }
} 


class TestThread2 extends Thread { 
	  private VTUnsafeSizeListTest data ; 
	  private int elemBase ; 
	  private TraceRecord[] trace ; 
		private int threadID;
		private Controller2 controller2;
		private int uselen;

	  TestThread2 (String name, VTUnsafeSizeListTest q, TraceRecord[] tr, int tid,Controller2 c) { 
	    super(name) ; 
	    this.data = q ; 
	    this.elemBase = 0 ; 
	     this.trace = tr ; 
		threadID = tid;
		controller2 = c;
		uselen = 0;
	  } 

	  public void run() { 
	    int len = 300 ; 
	    Random r = new Random() ; 
	    int add__1; 
	    boolean add__ret  ; 
	    int remove__1; 
	    boolean remove__ret ;
		
		
	 
	    for (int i = 0,ej=0; ej < len; ) { 
//	    System.out.println(threadID);
		int opThisBlock = 2;
		
	
		
		int[] op = new int[opThisBlock];
		for(int j = 0; j < opThisBlock;j++){
			op[j]= r.nextInt(2);
		}
		controller2.uploadThreadInfo(threadID,op);
		data.setScheduler(controller2.getScheduler());

		for(int k = i; (k < opThisBlock+i);k++){
			
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
	          e.printStackTrace();
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
	          System.err.println(e);
	        } 
	        
	        break ; 
	      default :  
	      } 
		}
		controller2.handleEnd(threadID);
//		System.out.println("i   "+threadID+ i);
		i+= opThisBlock;
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
    int tdNum = 2, mdNum = 2, trLen = 1000; 
    VTUnsafeSizeListTest testObj = new VTUnsafeSizeListTest() ; 
	Controller2 ctl = new Controller2(2);
    TraceRecord[][] tr = new TraceRecord[tdNum][trLen] ; 
    
   
    

    for (int i = 0; i < tdNum; i++) { 
      tr[i] = new TraceRecord[trLen] ; 
      
    } 
    
    TestThread1 thd1 = new TestThread1(Integer.toString(0), testObj, tr[0],0,ctl);
    TestThread2 thd2 = new TestThread2(Integer.toString(1), testObj, tr[1],1,ctl);
    thd1.start();
    thd2.start();
    
    
    try { 
      thd1.join();
      thd2.join();
    } catch (Exception e) { 
      e.printStackTrace() ; 
    } 
    String fn = argv[0] ; 
   
    
      FileWriter fw;
	try {
		fw = new FileWriter(fn);
	
      BufferedWriter out = new BufferedWriter(fw) ; 
      out.write("# <MethodIndex> <ArgumentValue> <ReturnValue>\n") ; 
      out.write("2 500 BugUnknownList4 add remove\n") ; 
      int[] uslen = {thd1.getUselen(),thd2.getUselen()};
      
      /*for(int u = 0;u < tdNum;u++){
    	  for(int v = 0;v< uslen[u];v++){
    		  System.out.println("as  "+u + "   "+ v+(tr[u][v]==null));
    	  }
      }*/
      
      
      for (int i=0; i < tdNum; i++) { 
        out.write("Thread " + i + "\n") ; 
        
        for (int j=0; j < uslen[i]; j++) {
        	
        	out.write(tr[i][j].toString() + "\n") ;
        }
      } 
      out.close() ; 
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
     
  } 
} 
