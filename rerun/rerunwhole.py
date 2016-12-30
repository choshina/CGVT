#author zhang 
#date 2016/08/30-2016/09/??


with open('RerunWhole.java','w') as g:
	g.write('import java.io.*;\n')
	g.write('import java.util.*;\n')
	g.write('import src.concurrency.*\n')
	g.write('import jtrace.*;\n\n')
	g.write('class VTLockFreeListTest{\n')
	g.write('\tprivate LockFreeList<Integer> data;\n')
	g.write('\tVTLockFreeListTest(){\n')
	g.write('\t\tthis.data = new LockFreeList<Integer>();\n')
	g.write('\t}\n')
	g.write('\tpublic boolean vtMethod0(int x1){return data.add(x1);}\n')
	g.write('\tpublic boolean vtMethod1(int x1){return data.remove(x1);}\n')	
	g.write('}\n')


############################################################################################
	g.write('class TestThread0 extends Thread{\n')
	g.write('\tprivate VTLockFreeListTest data;\n')
	g.write('\tprivate TraceRecord[] trace;\n')
	
	g.write('\tTestThread0(String name,VTLockFreeListTest q,TraceRecord[] tr){\n')
	g.write('\t\tsuper(name);\n')
	g.write('\t\tthis.data = q;\n')
	g.write('\t\tthis.trace = tr;\n')
	g.write('\t}\n\n')


	g.write('\tpublic void run(){\n')
	g.write('\t\tint add_1;\n')
	g.write('\t\tboolean add_ret;\n')
	g.write('\t\tint remove_1;\n')
	g.write('\t\tboolean remove_ret;\n')
	g.write('\t\tLinkedList<ArgType> args;\n') 
	


	argu = []
	with open('book_lockfreelist_add_remove.temp.testlog','r') as f:
		i = 0
		for line in f.readlines():
			argu = line.strip().split(' ')
			if argu[0]=='Thread':
				continue
			elif i<1000:
					
				g.write('\t\targs = new LinkedList<ArgType>();\n')
			
				if int(argu[0])==0:
					g.write('\t\tadd_1 = '+argu[2]+';\n')
					g.write('\t\targs.add(new ArgInt(add_1));\n')
					g.write('\t\tadd_ret = data.vtMethod0(add_1);\n')
					g.write('\t\ttrace['+str(i)+'] = new TraceRecord(args,add_ret,0,false);\n')
				else:
					g.write('\t\tremove_1 = '+argu[2]+';\n')
					g.write('\t\targs.add(new ArgInt(remove_1));\n')
					g.write('\t\tremove_ret = data.vtMethod1(remove_1);\n')
					g.write('\t\ttrace['+str(i)+'] = new TraceRecord(args,remove_ret,1,false);\n')
				
				i = i+1
			elif i == 1000:
				
				g.write('\t}\n')
				g.write('}\n\n')
	#############################################################################
	
				g.write('class TestThread1 extends Thread{\n')
				g.write('\tprivate VTLockFreeListTest data;\n')
				g.write('\tprivate TraceRecord[] trace;\n')
	
				g.write('\tTestThread1(String name,VTLockFreeListTest q,TraceRecord[] tr){\n')
				g.write('\t\tsuper(name);\n')
				g.write('\t\tthis.data = q;\n')
				g.write('\t\tthis.trace = tr;\n')
				g.write('\t}\n\n')


				g.write('\tpublic void run(){\n')
				g.write('\t\tint add_1;\n')
				g.write('\t\tboolean add_ret;\n')
				g.write('\t\tint remove_1;\n')
				g.write('\t\tboolean remove_ret;\n')
				g.write('\t\tLinkedList<ArgType> args;\n') 



				g.write('\t\targs = new LinkedList<ArgType>();\n')

				if int(argu[0])==0:
					g.write('\t\tadd_1 = '+argu[2]+';\n')
					g.write('\t\targs.add(new ArgInt(add_1));\n')
					g.write('\t\tadd_ret = data.vtMethod0(add_1);\n')
					g.write('\t\ttrace['+str(i-1000)+'] = new TraceRecord(args,add_ret,0,false);\n')
				else:
					g.write('\t\tremove_1 = '+argu[2]+';\n')
					g.write('\t\targs.add(new ArgInt(remove_1));\n')
					g.write('\t\tremove_ret = data.vtMethod1(remove_1);\n')
					g.write('\t\ttrace['+str(i-1000)+'] = new TraceRecord(args,remove_ret,1,false);\n')
				i+=1
				
			else:		
				g.write('\t\targs = new LinkedList<ArgType>();\n')

				if int(argu[0])==0:
					g.write('\t\tadd_1 = '+argu[2]+';\n')
					g.write('\t\targs.add(new ArgInt(add_1));\n')
					g.write('\t\tadd_ret = data.vtMethod0(add_1);\n')
					g.write('\t\ttrace['+str(i-1000)+'] = new TraceRecord(args,add_ret,0,false);\n')
				else:
					g.write('\t\tremove_1 = '+argu[2]+';\n')
					g.write('\t\targs.add(new ArgInt(remove_1));\n')
					g.write('\t\tremove_ret = data.vtMethod1(remove_1);\n')
					g.write('\t\ttrace['+str(i-1000)+'] = new TraceRecord(args,remove_ret,1,false);\n')
	
				i+=1
		
	g.write('\t}\n')
	g.write('}\n\n')

	#############################################################################
	g.write('class TestingLockFreeListxxx{\n')
	g.write('\tpublic static void main(String[] argv){\n')
	g.write('\t\tint tdNum = 2,mdNum = 2,trLen = 1000;\n')
	g.write('\t\tVTLockFreeListTest testobj = new VTLockFreeListTest();\n')
	g.write('\t\tTraceRecord[][] tr = new TraceRecord[tdNum][trLen];\n')
	g.write('\t\tTestThread0 thd0 = new TestThread0("0",testobj,tr[0]);\n')
	g.write('\t\tthd0.start();\n')
	g.write('\t\tTestThread1 thd1 = new TestThread1("1",testobj,tr[1]);\n')
	g.write('\t\tthd1.start();\n')
	g.write('\t\tthd0.join();\n')
	g.write('\t\tthd1.join();\n')
	g.write('\t}\n}\n')

