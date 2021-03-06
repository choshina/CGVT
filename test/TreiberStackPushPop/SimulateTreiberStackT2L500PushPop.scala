// 0: push(int):void; 1: pop():int; 
package test.TreiberStackPushPop ; 
import src.concurrency._ ; 

import jtrace.ArgType ; 
import jtrace.ArgInt ; 
import jtrace.ArgBoolean ; 
import jtrace.ArgChar ; 
import jtrace.ArgString ; 
import jtrace.Simulation ; 
import java.io.File ;
import scala.collection.JavaConverters._; 

class SimulateTreiberStackT2L500PushPop(logName: String, verbose: Boolean) extends Simulation(logName, verbose) { 
  type T = TreiberStack 
  val methodLog : MethodTable = parseLog 
  val traceSegmentation : List[List[(Int,Int)]] = partitionTrace 
  val initSimuState : SimuState = List( (new T(), Nil) ) 

  // optimization code can be supplied here by defining ST and encodeObject 
  type ST = String 
  def encodeObject (o: T) = o.toString 

  def parseTestLine (line: String) : List[ArgType]= { 
    val words = line.split(' ') 
    val isExcept = (words(0) == "E") 
    val methodID = if (isExcept) words(1).toInt else words(0).toInt 
    methodID match { 
      case 0 => { 
        val mRet = if (isExcept) new ArgInt(-1, true) else new ArgInt("void") 
        val arg__2 = new ArgInt(words(2).toInt) 
        List(mRet, arg__2) 
      } 
      case 1 => { 
        val mRet = if (isExcept) new ArgInt(-1, true) else new ArgInt(words(1).toInt) 
        List(mRet) 
      } 
      case _ => { 
        val mRet = new ArgInt(-1) 
        List(mRet) 
      } 
    } 
  } 

  def sequentialExecute (tr: List[(Int, Int)]) (init: T) (verbose: Boolean) = { 
    var consistent = true  
    val obj: T = new T(init) 
    for ((tid, midx) <- tr; if consistent ) { 
      val mEvent = methodLog(tid)(midx) 
      mEvent.methodID match { 
        case 0 => { 
          val arg__0= mEvent.arguments(0).toInt 
          try { 
            val ret = obj.push(arg__0) 
            if (verbose) println("Execute method {" + mEvent.toString + "} gets: " + ret.toString) 
          } catch { 
            case ex : java.lang.Exception => if (! mEvent.retValue.isException) { consistent = false } 
          } 
        } 
        case 1 => { 
          try { 
            val ret = obj.pop() 
            if (ret != mEvent.retValue.toInt) { consistent = false } 
            if (verbose) println("Execute method {" + mEvent.toString + "} gets: " + ret.toString) 
          } catch { 
            case ex : java.lang.Exception => if (! mEvent.retValue.isException) { consistent = false } 
          } 
        } 
      } 
    } 
    if (consistent) Some(obj) else None 
  } 
} 

object Simulate { 
  def main (args : Array[String]) { 
  val (verbose, logName) = if (args(0) == "-v") (true, args(1)) else (false, args(0)) 
  val simu : Simulation = new SimulateTreiberStackT2L500PushPop(logName, verbose) 
  val finalState = simu.execute () 
  if (! finalState.isEmpty) sys.exit(0) else sys.exit(1) 
  } 
} 
