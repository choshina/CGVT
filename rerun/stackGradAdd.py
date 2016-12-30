

classname = ''
t1 = 0
t2 = 0
tdNum1 = 0
tdNum2 = 0
whichline = 0
methods = {}
folder = 'treiberstackinfo/'
with open(folder+'methodInfo','r') as mr:#method reader
	for line in mr.readlines():
		met = line.strip().split(' ')
		methods[met[0]] = (met[1],met[2],met[3]) #met[2] is argument, -1->no arguments

linearizedstates = {}
lineno = 0
with open(folder+'cacheInfo','r') as lr:#linearized reader
	for line in lr.readlines():
		lin = line.strip()
		linearizedstates[lineno] = lin
		lineno = lineno + 1

with open(folder+'conf','r') as cf:
	classname = cf.readline().strip()
	tdNum1 = int(cf.readline())
	tdNum2 = int(cf.readline())
	t1 = tdNum1
	t2 = tdNum2
	whichline = int(cf.readline())

lengthofprefix = 0
if whichline != -1:
	with open('preprocess','w') as pre:
		t=linearizedstates[whichline].split(' ')                   # lineno
		objs = t[1].split(',')
		for m in objs:
			met = methods[m]
			pre.write(met[0]+' '+met[1]+'\n')

bugloc1 = 0
bugloc2 = 0
tt = linearizedstates[lineno-1].split(' ')
for mm in tt[1].split(','):
	mett = methods[mm]
	if mett[2] == '0':
		bugloc1 += 1
	else:
		bugloc2 += 1
#print(bugloc1)
#print(bugloc2)

for i in range(whichline,whichline+1):
	
	if i == -1:
		metseq = []
		lenseq = [0]
		seq = []
	else:
		metseq = linearizedstates[i]
		lenseq = metseq.split(' ')
		seq = lenseq[1].split(',')
	
	nameno  = ''
	if i == -1:
		nameno = 'whole'
	else:
		nameno = str(i)
	with open('RerunTrace'+classname+ nameno +'.java','w') as g:
		
		g.write('package test.'+classname+'PushPop;\n')
			
		g.write('import java.io.*;\n')
		g.write('import java.util.*;\n')
		g.write('import src.concurrency.*;\n')
		g.write('import jtrace.ArgType;\n')
		g.write('import jtrace.ArgInt;\n')
		g.write('import jtrace.ArgBoolean;\n')
		g.write('import jtrace.ArgChar;\n')
		g.write('import jtrace.ArgString;\n')
		g.write('import jtrace.TraceRecord;\n\n')
		
		
		g.write('class VT'+classname+'Test{\n')
		g.write('\tprivate '+classname+' data;\n')
		g.write('\tVT'+classname+'Test(){\n')
		g.write('\t\tthis.data = new '+classname+'();\n')
		g.write('\t}\n')
		g.write('\tpublic void vtMethod0(int x1){data.push(x1);}\n')
		g.write('\tpublic int vtMethod1(){return data.pop();}\n')	
		g.write('}\n')
#########################################################################################################
		

		
#########################################################################################################

		

		g.write('public class RerunTrace'+classname+ nameno+'{\n')
		g.write('\tpublic static void main(String[] argv){\n')
		g.write('\t\tint tdNum = 2,mdNum = 2;\n')
		g.write('\t\tVT'+classname+'Test testobj = new VT'+classname+'Test();\n')
		g.write('\t\tTraceRecord[][] tr = new TraceRecord[tdNum][];\n')
		
	#	t1 = 500
	#	t2 = 500
		for j in range(0,int(lenseq[0])):
			temp = methods[seq[j]]
			if temp[0] == '0' :
				g.write('\t\ttestobj.vtMethod0('+ temp[1] +');\n')
				if(temp[2] == '0'):
					t1 = t1 - 1
				else:
					t2 = t2 - 1
			else:
				g.write('\t\ttestobj.vtMethod1();\n')
				if(temp[2] == '0'):
					t1 = t1 - 1
				else:
					t2 = t2 - 1
		
		print(tdNum1-t1)
		print(tdNum2-t2)
		g.write('\t\ttr[0] = new TraceRecord['+ str(bugloc1+1-(tdNum1-t1)) + '];\n')
		g.write('\t\ttr[1] = new TraceRecord['+ str(bugloc2+1-(tdNum2-t2)) + '];\n')


		g.write('\t\tTestThread0 thd0 = new TestThread0("0",testobj,tr[0]);\n')
		g.write('\t\tthd0.start();\n')
		g.write('\t\tTestThread1 thd1 = new TestThread1("1",testobj,tr[1]);\n')
		g.write('\t\tthd1.start();\n')

		g.write('\t\ttry{\n')
		g.write('\t\t\tthd0.join();\n')
		g.write('\t\t\tthd1.join();\n')
		g.write('\t\t}catch(Exception e){\n\t\t}\n')
		
		g.write('\t\ttry{\n')
		g.write('\t\t\tFileWriter fw = new FileWriter("tracelog/'+str(i)+'.temp.testlog'+'");\n')
		g.write('\t\t\tBufferedWriter out = new BufferedWriter(fw);\n')
		g.write('\t\t\tout.write("# <MethodIndex> <ArgumentValue> <ReturnValue>\\n");\n')
		g.write('\t\t\tout.write("2 1000 '+classname+' push pop\\n");\n')
		g.write('\t\t\tfor(int i = 0; i < tdNum;i++){\n')
		g.write('\t\t\t\tout.write("Thread "+i+"\\n");\n')
		g.write('\t\t\t\tfor(int j = 0;j < tr[i].length;j++){\n')
		g.write('\t\t\t\t\tout.write(tr[i][j].toString()+"\\n");\n')
		g.write('\t\t\t\t}\n')
		g.write('\t\t\t}\n')
		g.write('\t\t\tout.close();\n')
		g.write('\t\t}catch(Exception e){\n')
		g.write('\t\t\tSystem.out.println("ss"+e);\n')
		g.write('\t\t}\n')
		g.write('\t}\n')
		g.write('}\n')

######################################################################################################33

		g.write('class TestThread0 extends Thread{\n')
		g.write('\tprivate VT'+classname+'Test data;\n')
		g.write('\tprivate TraceRecord[] trace;\n')
		
		g.write('\tTestThread0(String name,VT'+classname+'Test q,TraceRecord[] tr){\n')
		g.write('\t\tsuper(name);\n')
		g.write('\t\tthis.data = q;\n')
		g.write('\t\tthis.trace = tr;\n')
		g.write('\t}\n\n')

			
		g.write('\tpublic void run(){\n')
		g.write('\t\tint push_1;\n')
	#	g.write('\t\tboolean add_ret;\n')
	#	g.write('\t\tint remove_1;\n')
		g.write('\t\tint pop_ret;\n')
		g.write('\t\tLinkedList<ArgType> args;\n') 
		
		with open(folder+'book_treiberstack_push_pop.temp.testlog','r') as f:
			idx = 0
			for line in f.readlines():
				argu = line.strip().split(' ')
				if argu[0] == 'Thread':
					if argu[1] == '0':
						continue
					else:
						g.write('\t}\n')
						g.write('}\n\n')

						g.write('class TestThread1 extends Thread{\n')
						g.write('\tprivate VT'+classname+'Test data;\n')
						g.write('\tprivate TraceRecord[] trace;\n')
	
						g.write('\tTestThread1(String name,VT'+classname+'Test q,TraceRecord[] tr){\n')
						g.write('\t\tsuper(name);\n')
						g.write('\t\tthis.data = q;\n')
						g.write('\t\tthis.trace = tr;\n')
						g.write('\t}\n\n')
	
						g.write('\tpublic void run(){\n')
						g.write('\t\tint push_1;\n')
					#	g.write('\t\tboolean add_ret;\n')
					#	g.write('\t\tint remove_1;\n')
						g.write('\t\tint pop_ret;\n')
						g.write('\t\tLinkedList<ArgType> args;\n')

						g.write('\t\targs = new LinkedList<ArgType>();\n')
						continue
 
				elif argu[0] == '#':
					continue
				elif idx < tdNum1 - t1:
					idx = idx+1
					continue
				elif idx < bugloc1+1:
					
					g.write('\t\targs = new LinkedList<ArgType>();\n')
				
					if int(argu[0])==0:
						g.write('\t\tpush_1 = '+argu[2]+';\n')
						g.write('\t\targs.add(new ArgInt(push_1));\n')
						g.write('\t\tdata.vtMethod0(push_1);\n')
						g.write('\t\ttrace['+str(idx - tdNum1 + t1)+'] = new TraceRecord(args,-1,0,false);\n')
					else:
						#g.write('\t\tremove_1 = '+argu[2]+';\n')
						#g.write('\t\targs.add(new ArgInt(remove_1));\n')
						g.write('\t\tpop_ret = data.vtMethod1();\n')
						g.write('\t\ttrace['+str(idx - tdNum1 + t1)+'] = new TraceRecord(args,pop_ret,1,false);\n')
				
					idx = idx + 1
					continue
				elif idx < tdNum1:
					idx = idx + 1
					continue
				elif idx < tdNum1+tdNum2-t2:
					idx = idx + 1
					continue
				elif idx < tdNum1+bugloc2+1:
	
					g.write('\t\targs = new LinkedList<ArgType>();\n')	
					if int(argu[0])==0:
						g.write('\t\tpush_1 = '+argu[2]+';\n')
						g.write('\t\targs.add(new ArgInt(push_1));\n')
						g.write('\t\tdata.vtMethod0(push_1);\n')
						g.write('\t\ttrace['+str(idx-tdNum1-tdNum2+t2)+'] = new TraceRecord(args,-1,0,false);\n')
					else:
						#g.write('\t\tremove_1 = '+argu[2]+';\n')
						#g.write('\t\targs.add(new ArgInt(remove_1));\n')
						g.write('\t\tpop_ret = data.vtMethod1();\n')
						g.write('\t\ttrace['+str(idx-tdNum1-tdNum2+t2)+'] = new TraceRecord(args,pop_ret,1,false);\n')
					idx = idx + 1
					continue
				else:
					idx = idx + 1
					continue
		g.write('\t}\n')
		g.write('}\n\n')
