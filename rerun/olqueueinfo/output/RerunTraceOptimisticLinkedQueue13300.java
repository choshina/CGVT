package test.OptimisticLinkedQueueOfferPoll;
import java.io.*;
import java.util.*;
import src.concurrency.*;
import jtrace.ArgType;
import jtrace.ArgInt;
import jtrace.ArgBoolean;
import jtrace.ArgChar;
import jtrace.ArgString;
import jtrace.TraceRecord;

class VTOptimisticLinkedQueueTest{
	private OptimisticLinkedQueue data;
	VTOptimisticLinkedQueueTest(){
		this.data = new OptimisticLinkedQueue();
	}
	public void vtMethod0(int x1){data.offer(x1);}
	public int vtMethod1(){return data.poll();}
}
public class RerunTraceOptimisticLinkedQueue13300{
	public static void main(String[] argv){
		int tdNum = 2,mdNum = 2;
		VTOptimisticLinkedQueueTest testobj = new VTOptimisticLinkedQueueTest();
		TraceRecord[][] tr = new TraceRecord[tdNum][];
		testobj.vtMethod1();
		testobj.vtMethod0(47);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(99);
		testobj.vtMethod0(27);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(10);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(32);
		testobj.vtMethod0(16);
		testobj.vtMethod1();
		testobj.vtMethod0(80);
		testobj.vtMethod0(2);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(69);
		testobj.vtMethod0(22);
		testobj.vtMethod0(24);
		testobj.vtMethod0(61);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(53);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(25);
		testobj.vtMethod0(4);
		testobj.vtMethod0(5);
		testobj.vtMethod1();
		testobj.vtMethod0(48);
		testobj.vtMethod1();
		testobj.vtMethod0(16);
		testobj.vtMethod0(8);
		testobj.vtMethod0(77);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(55);
		testobj.vtMethod0(84);
		testobj.vtMethod0(93);
		testobj.vtMethod0(51);
		testobj.vtMethod0(32);
		testobj.vtMethod0(41);
		testobj.vtMethod0(99);
		testobj.vtMethod1();
		testobj.vtMethod0(30);
		testobj.vtMethod1();
		testobj.vtMethod0(73);
		testobj.vtMethod0(85);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(1);
		testobj.vtMethod0(72);
		testobj.vtMethod0(30);
		testobj.vtMethod0(70);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(18);
		testobj.vtMethod1();
		testobj.vtMethod0(46);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(71);
		testobj.vtMethod1();
		testobj.vtMethod0(2);
		testobj.vtMethod1();
		testobj.vtMethod0(47);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(59);
		testobj.vtMethod1();
		testobj.vtMethod0(63);
		testobj.vtMethod1();
		testobj.vtMethod0(22);
		testobj.vtMethod0(73);
		testobj.vtMethod0(61);
		testobj.vtMethod1();
		testobj.vtMethod0(30);
		testobj.vtMethod1();
		testobj.vtMethod0(70);
		testobj.vtMethod0(62);
		testobj.vtMethod0(71);
		testobj.vtMethod0(60);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(8);
		testobj.vtMethod0(43);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(73);
		testobj.vtMethod0(65);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(42);
		testobj.vtMethod0(85);
		testobj.vtMethod0(38);
		testobj.vtMethod1();
		testobj.vtMethod0(29);
		testobj.vtMethod1();
		testobj.vtMethod0(35);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(73);
		testobj.vtMethod1();
		testobj.vtMethod0(94);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(31);
		testobj.vtMethod0(18);
		testobj.vtMethod0(89);
		testobj.vtMethod0(83);
		testobj.vtMethod0(86);
		testobj.vtMethod0(68);
		testobj.vtMethod0(44);
		testobj.vtMethod0(96);
		testobj.vtMethod0(1);
		testobj.vtMethod0(2);
		testobj.vtMethod1();
		testobj.vtMethod0(36);
		testobj.vtMethod1();
		testobj.vtMethod0(28);
		testobj.vtMethod0(77);
		testobj.vtMethod0(5);
		testobj.vtMethod0(9);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(74);
		testobj.vtMethod0(24);
		testobj.vtMethod0(97);
		testobj.vtMethod0(62);
		testobj.vtMethod1();
		testobj.vtMethod0(8);
		testobj.vtMethod1();
		testobj.vtMethod0(52);
		testobj.vtMethod1();
		testobj.vtMethod0(81);
		testobj.vtMethod1();
		testobj.vtMethod0(37);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(7);
		testobj.vtMethod0(33);
		testobj.vtMethod0(80);
		testobj.vtMethod0(32);
		testobj.vtMethod0(34);
		testobj.vtMethod0(40);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(67);
		testobj.vtMethod1();
		testobj.vtMethod0(6);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(77);
		testobj.vtMethod1();
		testobj.vtMethod0(21);
		testobj.vtMethod0(78);
		testobj.vtMethod0(88);
		testobj.vtMethod0(69);
		testobj.vtMethod1();
		testobj.vtMethod0(6);
		testobj.vtMethod0(1);
		testobj.vtMethod1();
		testobj.vtMethod0(19);
		testobj.vtMethod0(23);
		testobj.vtMethod1();
		testobj.vtMethod0(20);
		testobj.vtMethod0(28);
		testobj.vtMethod0(16);
		testobj.vtMethod1();
		testobj.vtMethod0(50);
		testobj.vtMethod1();
		testobj.vtMethod0(48);
		testobj.vtMethod1();
		testobj.vtMethod0(38);
		testobj.vtMethod0(69);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(56);
		testobj.vtMethod1();
		testobj.vtMethod0(21);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(94);
		testobj.vtMethod0(28);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(15);
		testobj.vtMethod1();
		testobj.vtMethod0(15);
		testobj.vtMethod0(93);
		testobj.vtMethod0(37);
		testobj.vtMethod0(90);
		testobj.vtMethod0(87);
		testobj.vtMethod1();
		testobj.vtMethod0(75);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(82);
		testobj.vtMethod0(7);
		testobj.vtMethod1();
		testobj.vtMethod0(46);
		testobj.vtMethod0(73);
		testobj.vtMethod0(12);
		testobj.vtMethod1();
		testobj.vtMethod0(63);
		testobj.vtMethod0(54);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(18);
		testobj.vtMethod1();
		testobj.vtMethod0(48);
		testobj.vtMethod0(32);
		testobj.vtMethod0(8);
		testobj.vtMethod0(61);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(63);
		testobj.vtMethod1();
		testobj.vtMethod0(95);
		testobj.vtMethod0(86);
		testobj.vtMethod1();
		testobj.vtMethod0(67);
		testobj.vtMethod0(38);
		testobj.vtMethod0(55);
		testobj.vtMethod1();
		testobj.vtMethod0(87);
		testobj.vtMethod0(22);
		testobj.vtMethod0(84);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(19);
		testobj.vtMethod0(45);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(43);
		testobj.vtMethod0(54);
		testobj.vtMethod0(94);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(21);
		testobj.vtMethod0(39);
		testobj.vtMethod1();
		testobj.vtMethod0(51);
		testobj.vtMethod0(30);
		testobj.vtMethod0(8);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(13);
		testobj.vtMethod0(44);
		testobj.vtMethod1();
		testobj.vtMethod0(27);
		testobj.vtMethod0(21);
		testobj.vtMethod0(10);
		testobj.vtMethod0(24);
		testobj.vtMethod1();
		testobj.vtMethod0(20);
		testobj.vtMethod0(86);
		testobj.vtMethod1();
		testobj.vtMethod0(53);
		testobj.vtMethod0(77);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(34);
		testobj.vtMethod0(49);
		testobj.vtMethod0(25);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(82);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(7);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(9);
		testobj.vtMethod1();
		testobj.vtMethod0(85);
		testobj.vtMethod0(17);
		testobj.vtMethod1();
		testobj.vtMethod0(55);
		testobj.vtMethod1();
		testobj.vtMethod0(4);
		testobj.vtMethod0(75);
		testobj.vtMethod0(85);
		testobj.vtMethod0(74);
		testobj.vtMethod0(26);
		testobj.vtMethod0(48);
		testobj.vtMethod0(55);
		testobj.vtMethod0(49);
		testobj.vtMethod0(63);
		testobj.vtMethod1();
		testobj.vtMethod0(96);
		testobj.vtMethod0(60);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(27);
		testobj.vtMethod1();
		testobj.vtMethod0(42);
		testobj.vtMethod1();
		testobj.vtMethod0(43);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(73);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(73);
		testobj.vtMethod0(5);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(84);
		testobj.vtMethod0(28);
		testobj.vtMethod0(16);
		testobj.vtMethod1();
		testobj.vtMethod0(51);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(55);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(49);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(67);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(88);
		testobj.vtMethod0(66);
		testobj.vtMethod1();
		testobj.vtMethod0(69);
		testobj.vtMethod1();
		testobj.vtMethod0(9);
		testobj.vtMethod1();
		testobj.vtMethod0(12);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(82);
		testobj.vtMethod0(81);
		testobj.vtMethod0(70);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(76);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(49);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(61);
		testobj.vtMethod0(21);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(19);
		testobj.vtMethod0(47);
		testobj.vtMethod0(90);
		testobj.vtMethod0(94);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(40);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(75);
		testobj.vtMethod1();
		testobj.vtMethod0(0);
		testobj.vtMethod0(18);
		testobj.vtMethod1();
		testobj.vtMethod0(90);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(45);
		testobj.vtMethod1();
		testobj.vtMethod0(2);
		testobj.vtMethod0(98);
		testobj.vtMethod1();
		testobj.vtMethod0(84);
		testobj.vtMethod1();
		testobj.vtMethod0(87);
		testobj.vtMethod0(90);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(87);
		testobj.vtMethod0(47);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(36);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(79);
		testobj.vtMethod0(28);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(62);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(34);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(84);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(27);
		testobj.vtMethod1();
		testobj.vtMethod0(4);
		testobj.vtMethod0(21);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(37);
		testobj.vtMethod1();
		testobj.vtMethod0(80);
		testobj.vtMethod0(95);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(27);
		testobj.vtMethod0(4);
		testobj.vtMethod1();
		testobj.vtMethod0(61);
		testobj.vtMethod0(34);
		testobj.vtMethod0(79);
		testobj.vtMethod0(86);
		testobj.vtMethod0(31);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(89);
		testobj.vtMethod1();
		testobj.vtMethod0(44);
		testobj.vtMethod0(16);
		testobj.vtMethod1();
		testobj.vtMethod0(13);
		testobj.vtMethod0(66);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(83);
		testobj.vtMethod1();
		testobj.vtMethod0(21);
		testobj.vtMethod0(57);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(87);
		testobj.vtMethod0(74);
		testobj.vtMethod0(77);
		testobj.vtMethod0(85);
		testobj.vtMethod1();
		testobj.vtMethod0(37);
		testobj.vtMethod0(66);
		testobj.vtMethod0(24);
		testobj.vtMethod0(8);
		testobj.vtMethod0(84);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(39);
		testobj.vtMethod0(9);
		testobj.vtMethod0(72);
		testobj.vtMethod0(77);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(84);
		testobj.vtMethod1();
		testobj.vtMethod0(90);
		testobj.vtMethod0(87);
		testobj.vtMethod0(38);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(50);
		testobj.vtMethod1();
		testobj.vtMethod0(52);
		testobj.vtMethod1();
		testobj.vtMethod0(1);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(65);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(41);
		testobj.vtMethod0(66);
		testobj.vtMethod0(83);
		testobj.vtMethod0(5);
		testobj.vtMethod0(75);
		testobj.vtMethod0(37);
		testobj.vtMethod0(77);
		testobj.vtMethod1();
		testobj.vtMethod0(67);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(69);
		testobj.vtMethod0(60);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(85);
		testobj.vtMethod0(5);
		testobj.vtMethod0(64);
		testobj.vtMethod0(45);
		testobj.vtMethod0(19);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(47);
		testobj.vtMethod0(0);
		testobj.vtMethod1();
		testobj.vtMethod0(84);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(34);
		testobj.vtMethod1();
		testobj.vtMethod0(81);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(99);
		testobj.vtMethod0(74);
		testobj.vtMethod0(4);
		testobj.vtMethod1();
		testobj.vtMethod0(8);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(64);
		testobj.vtMethod1();
		testobj.vtMethod0(72);
		testobj.vtMethod0(60);
		testobj.vtMethod0(48);
		testobj.vtMethod0(37);
		testobj.vtMethod0(81);
		testobj.vtMethod0(3);
		testobj.vtMethod0(88);
		testobj.vtMethod0(57);
		testobj.vtMethod0(12);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(49);
		testobj.vtMethod1();
		testobj.vtMethod0(7);
		testobj.vtMethod1();
		testobj.vtMethod0(69);
		testobj.vtMethod0(66);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(6);
		testobj.vtMethod0(14);
		testobj.vtMethod1();
		testobj.vtMethod0(40);
		testobj.vtMethod0(45);
		testobj.vtMethod0(80);
		testobj.vtMethod1();
		testobj.vtMethod0(85);
		testobj.vtMethod0(96);
		testobj.vtMethod1();
		testobj.vtMethod0(91);
		testobj.vtMethod0(0);
		testobj.vtMethod0(62);
		testobj.vtMethod0(61);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(78);
		testobj.vtMethod0(30);
		testobj.vtMethod1();
		testobj.vtMethod0(54);
		testobj.vtMethod0(62);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(23);
		testobj.vtMethod1();
		testobj.vtMethod0(58);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(89);
		testobj.vtMethod0(34);
		testobj.vtMethod0(96);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(15);
		testobj.vtMethod0(64);
		testobj.vtMethod0(48);
		testobj.vtMethod0(86);
		testobj.vtMethod0(96);
		testobj.vtMethod1();
		testobj.vtMethod0(27);
		testobj.vtMethod0(7);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(36);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(53);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(86);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(91);
		testobj.vtMethod0(63);
		testobj.vtMethod0(12);
		testobj.vtMethod1();
		testobj.vtMethod0(71);
		testobj.vtMethod1();
		testobj.vtMethod0(73);
		testobj.vtMethod0(37);
		testobj.vtMethod0(27);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(32);
		testobj.vtMethod1();
		testobj.vtMethod0(3);
		testobj.vtMethod0(92);
		testobj.vtMethod1();
		testobj.vtMethod0(85);
		testobj.vtMethod1();
		testobj.vtMethod0(7);
		testobj.vtMethod0(71);
		testobj.vtMethod0(13);
		testobj.vtMethod1();
		testobj.vtMethod0(26);
		testobj.vtMethod1();
		testobj.vtMethod0(77);
		testobj.vtMethod0(58);
		testobj.vtMethod0(88);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(62);
		testobj.vtMethod0(31);
		testobj.vtMethod0(70);
		testobj.vtMethod0(77);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(80);
		testobj.vtMethod1();
		testobj.vtMethod0(24);
		testobj.vtMethod0(81);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(0);
		testobj.vtMethod0(97);
		testobj.vtMethod1();
		testobj.vtMethod0(14);
		testobj.vtMethod0(96);
		testobj.vtMethod0(12);
		testobj.vtMethod0(3);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(10);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(3);
		testobj.vtMethod0(12);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(13);
		testobj.vtMethod1();
		testobj.vtMethod0(45);
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod1();
		testobj.vtMethod0(41);
		testobj.vtMethod0(36);
		testobj.vtMethod0(81);
		tr[0] = new TraceRecord[1];
		tr[1] = new TraceRecord[2];
		TestThread0 thd0 = new TestThread0("0",testobj,tr[0]);
		thd0.start();
		TestThread1 thd1 = new TestThread1("1",testobj,tr[1]);
		thd1.start();
		try{
			thd0.join();
			thd1.join();
		}catch(Exception e){
		}
		try{
			FileWriter fw = new FileWriter("tracelog/loc_OptimisticLinkedQueue_13300.temp.testlog");
			BufferedWriter out = new BufferedWriter(fw);
			out.write("# <MethodIndex> <ArgumentValue> <ReturnValue>\n");
			out.write("2 1000 OptimisticLinkedQueue add remove\n");
			for(int i = 0; i < tdNum;i++){
				out.write("Thread "+i+"\n");
				for(int j = 0;j < tr[i].length;j++){
					out.write(tr[i][j].toString()+"\n");
				}
			}
			out.close();
		}catch(Exception e){
			System.out.println("ss"+e);
		}
	}
}
class TestThread0 extends Thread{
	private VTOptimisticLinkedQueueTest data;
	private TraceRecord[] trace;
	TestThread0(String name,VTOptimisticLinkedQueueTest q,TraceRecord[] tr){
		super(name);
		this.data = q;
		this.trace = tr;
	}

	public void run(){
		int offer_1;
		int poll_ret;
		LinkedList<ArgType> args;
		args = new LinkedList<ArgType>();
		poll_ret = data.vtMethod1();
		trace[0] = new TraceRecord(args,poll_ret,1,false);
	}
}

class TestThread1 extends Thread{
	private VTOptimisticLinkedQueueTest data;
	private TraceRecord[] trace;
	TestThread1(String name,VTOptimisticLinkedQueueTest q,TraceRecord[] tr){
		super(name);
		this.data = q;
		this.trace = tr;
	}

	public void run(){
		int offer_1;
		int poll_ret;
		LinkedList<ArgType> args;
		args = new LinkedList<ArgType>();
		args = new LinkedList<ArgType>();
		poll_ret = data.vtMethod1();
		trace[0] = new TraceRecord(args,poll_ret,1,false);
		args = new LinkedList<ArgType>();
		poll_ret = data.vtMethod1();
		trace[1] = new TraceRecord(args,poll_ret,1,false);
	}
}

