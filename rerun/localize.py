import os
import sys

from sets import ImmutableSet

admitArgTypes = ImmutableSet(["int", "boolean", "char", "char[]", "String"])
admitReturnTypes = ImmutableSet(["int", "obj[int]", "boolean", "char", "char[]", "String", "obj[String]", "StringBuffer"])
admitParamTypes = ImmutableSet(["Integer", "String"])

class ParseError(Exception) : 
    def __init__(self, line, value) : 
        self.line = line
        self.value = value
    def __str__(self) :
        if self.line > 0 :
            return ("Line " + repr(self.line) + ": " + self.value)
        else : 
            return (self.value)

class TestCase :
    def __init__(self, impath, clsname, types, mthds, thdNum, evtNum, outf) :
        self.importpath = impath
        self.classname = clsname
        self.typeParams = types
        self.methods = mthds # list of triples (methodName, args, return)
        self.threadNum = thdNum 
        self.traceLength = evtNum 
        self.outFile = outf


    def __repr__(self) :
        if len(self.methods) <= 0 :
            return "Testing " + self.classname + ": no method is given."
        str = self.methods[0]
        if len(self.methods) > 1 :
            for s in self.methods[1:] :
                str = str + ", " + s 
        return "Testing " + self.classname + " (" + str + ") with " + repr(self.threadNum) + " threads." 


try : 
    vtHomePath = os.environ['VT_HOME'].rstrip("/ ")
except KeyError:
    print "Please set the VT_HOME environment variable!" 
    exit(0)
if vtHomePath == "" :
    print "Please set the VT_HOME environment variable!" 
    exit (0) 

def parseTestConfig(filename):
    f = open(filename, "r")
    i = 0
    hasThreadNum, hasLength, hasLogName = False, False, False
    hasImport, hasClassName, hasOpt, hasProc = False, False, False, False
    mthds, mthdName, mthdArgs, mthdReturn, hasArgs, hasReturn = [], "", [], "void", False, False
    impath, optim, typeParams, proc = "", "", [], 1
    for line in f : 
        i = i+1
        l = line.strip() 
        if (l != "") and (l[0] != "#"): 
            words = filter(lambda x: x != "", map(lambda x: x.strip(), line.strip().split("=")))
            if words[0] == "thread" :
                if len(words) >= 2: 
                    if hasThreadNum :
                        raise ParseError(i, "Already have thread value") 
                    else : 
                        thdNum = int(words[1])
                        hasThreadNum = True

            elif words[0] == "tracelength" : 
                if len(words) >= 2: 
                    if hasLength :
                        raise ParseError(i, "Already have tracelength value") 
                    else : 
                        trLeng = int(words[1])
                        hasLength = True

            elif words[0] == "logname" :
                if len(words) >= 2: 
                    if hasLogName :
                        raise ParseError(i, "Already have logname value") 
                    else : 
                        outf = words[1]
                        hasLogName = True

            elif words[0] == "classname" :
                if len(words) >= 2: 
                    if hasClassName :
                        raise ParseError(i, "Already have classname value") 
                    else : 
                        nameEnd = words[1].find("<")
                        if nameEnd > 0 :
                            typeEnd = words[1].find(">")
                            if typeEnd <= nameEnd: 
                                raise ParseError(i, "Invalid class name")
                            clsname = words[1][0:nameEnd]
                            typeParams = filter(lambda x: x != "", \
                                                    map(lambda x: x.strip(), words[1][nameEnd+1:typeEnd].split(",")))
                            for t in typeParams : 
                                if not (t in admitParamTypes) :
                                    raise ParseError(i, t + " is not a supported type")
                        else : 
                            clsname = words[1] 
                        hasClassName = True

            elif words[0] == "import" :
                if hasImport :
                    raise ParseError(i, "Already have import value") 
                elif len(words) == 2: 
                    impath = words[1]
                    hasImport = True


            elif words[0] == "method" :
                if len(words) < 2: 
                    raise ParseError(i, "No value for method") 
                else: 
                    if mthdName != "" :
                        mthds.append((mthdName, mthdArgs, mthdReturn)) 
                        hasArgs = False
                        hasReturn = False
                        methdName = ""
                        mthdArgs = [] 
                        mthdReturn = "void"
                    mthdName = words[1] 

            elif words[0] == "arguments" :
                if hasArgs : 
                    raise ParseError(i, "Already have arguments value")  
                elif len(words) < 2 : 
                    mthdArgs = [] 
                    hasArgs = True 
                else : 
                    mthdArgs = words[1].split() 
                    for arg in mthdArgs : 
                        if not (arg in admitArgTypes) : 
                            raise ParseError(i, arg + " is not a supported type")
                    hasArgs = True

            elif words[0] == "return" :
                if hasReturn : 
                    raise ParseError(i, "Already have return value")  
                elif len(words) < 2 : 
                    mthdReturn = "void"
                    hasReturn = True
                else : 
                    if not (words[1] in admitReturnTypes) :
                            raise ParseError(i, words[1] + " is not a supported type")
                    else : 
                        mthdReturn = words[1]
                        hasReturn = True
    if mthdName != "" :
        mthds.append((mthdName, mthdArgs, mthdReturn)) 
    if not hasThreadNum :
        raise ParseError(-1, "No value for thread") 
    if not hasLength :
        raise ParseError(-1, "No value for tracelength") 
    if not hasLogName :
        raise ParseError(-1, "No value for logname") 
    if not hasClassName :
        raise ParseError(-1, "No value for classname") 
    if mthds == [] :
        raise ParseError(-1, "No method for testing") 
    return TestCase(impath, clsname, typeParams, mthds, thdNum, trLeng, outf) # last three arguments: verbose, repeat, keepsrc

def parseCommandLine() :
    testcase = parseTestConfig(vtHomePath+'/'+sys.argv[1])
    return testcase

try : 
    test = parseCommandLine ()
except ParseError as err :
    print "Parse error: " + str(err)
    exit(0)


t1 = test.traceLength
t2 = test.traceLength

whichline = int(sys.argv[2])
methodInfo ={}

mdNum = len(test.methods)
vtIdxMethod = {}

tdNum1 = test.traceLength
tdNum2 = test.traceLength

conffolder = vtHomePath + '/rerun/'+test.classname + '_conf'
prepoutputfolder = vtHomePath + '/wgl/preprocess/__'+test.classname

pkgPath = 'test.' + test.classname
testoutputfolder = vtHomePath + '/test/'+test.classname
testlog = vtHomePath + '/tracelog/'+ test.outFile
for m in test.methods:
	pkgPath = pkgPath + m[0].capitalize()
	testoutputfolder = testoutputfolder + m[0].capitalize()
	testlog = testlog +  '_' + m[0].lower()
pkgPath = pkgPath + '.opttc'
testoutputfolder = testoutputfolder + '/opttc'
testlog = testlog + '.temp.testlog'

with open(conffolder + '/methodInfo','r') as mr:#method reader
	for line in mr.readlines():
		met = line.strip().split(' ')
		methodInfo[met[0]] = (met[1],met[2],met[3])

linearizedstates = {}
lineno = 0
with open(conffolder + '/cacheInfo','r') as lr:#linearized reader
	for line in lr.readlines():
		lin = line.strip()
		linearizedstates[lineno] = lin
		lineno = lineno + 1


lengthofprefix = 0
if whichline != -1:
	with open(prepoutputfolder + '/__' + str(whichline) + '.prep','w') as pre:
		t=linearizedstates[whichline].split(' ')                   # lineno
		objs = t[1].split(',')
		for m in objs:
			met = methodInfo[m]
			pre.write(met[0]+' '+met[1]+'\n')

bugloc1 = 0
bugloc2 = 0
tt = linearizedstates[lineno-1].split(' ')
for mm in tt[1].split(','):
	mett = methodInfo[mm]
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
	with open( testoutputfolder + '/RerunTrace'+test.classname+ nameno +'.java','w') as g:  ###There should be some folder information here
		
		g.write('package '+ pkgPath+';\n\n')  ###also here
			
		g.write('import java.io.*;\n')
		g.write('import java.util.*;\n')
		g.write('import src.concurrency.*;\n')
		g.write('import jtrace.ArgType;\n')
		g.write('import jtrace.ArgInt;\n')
		g.write('import jtrace.ArgBoolean;\n')
		g.write('import jtrace.ArgChar;\n')
		g.write('import jtrace.ArgString;\n')
		g.write('import jtrace.TraceRecord;\n\n')
		
		
		g.write('class VT'+test.classname+'Test{\n')
		g.write('\tprivate '+test.classname+' data;\n')
		g.write('\tVT'+test.classname+'Test(){\n')
		g.write('\t\tthis.data = new '+test.classname+'();\n')
		g.write('\t}\n')
		for i in range(len(test.methods)) :
			
       			m = test.methods[i] 
			vtIdxMethod[i] = m
      			mDef = "vtMethod" + str(i) + "(" 
        		mCall = m[0] + "(" 
        		j = 0 
        		for argtype in m[1] :
            			j = j + 1
            			varname = "x" + str(j) 
            			mDef = mDef + argtype + " " + varname 
            			mCall = mCall + varname 
            			if j < len(m[1]) :
                			mDef = mDef + ", " 
                			mCall = mCall + ", "
        		mDef = mDef + ")"
        		mCall = mCall + ")"
        		if m[2] == "obj[int]" :
            			g.write("    public Integer " + mDef + " { return data." + mCall + "; } \n") 
       	 		elif m[2] == "obj[String]" : 
            			g.write("    public String " + mDef + " { return data." + mCall + "; } \n") 
        		elif m[2] == "void" :
            			g.write("    public void " + mDef + " { data." + mCall + "; } \n") 
        		else :
            			g.write("    public " + m[2] + " " + mDef + " { return data." + mCall + " ; } ; \n") 
    			g.write("} \n\n")
#########################################################################################################
		

		
#########################################################################################################

	

		g.write('public class RerunTrace'+test.classname+ nameno+'{\n')
		g.write('\tpublic static void main(String[] argv){\n')
		g.write('\t\tint tdNum = '+str(test.threadNum)+',mdNum = '+str(mdNum)+', trLen = '+str(test.traceLength)+';\n')
		g.write('\t\tVT'+test.classname+'Test testobj = new VT'+test.classname+'Test();\n')
		g.write('\t\tTraceRecord[][] tr = new TraceRecord[tdNum][];\n')
		

		for j in range(0,int(lenseq[0])):
			temp = methodInfo[seq[j]]
			if temp[1] == '-1':
				g.write('\t\ttestobj.vtMethod'+temp[0]+'('+temp[1]+');\n')
			else:
				g.write('\t\ttestobj.vtMethod'+temp[0]+'();\n')
			if temp[2] == '0':
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
		g.write('\t\t\tout.write("2 1000 '+test.classname+' add remove\\n");\n')
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
		g.write('\tprivate VT'+test.classname+'Test data;\n')
		g.write('\tprivate TraceRecord[] trace;\n')
		
		g.write('\tTestThread0(String name,VT'+test.classname+'Test q,TraceRecord[] tr){\n')
		g.write('\t\tsuper(name);\n')
		g.write('\t\tthis.data = q;\n')
		g.write('\t\tthis.trace = tr;\n')
		g.write('\t}\n\n')

			
		g.write('\tpublic void run(){\n')

		for m in test.methods : 
        		i = 0
        		for arg in m[1] :
            			i = i + 1
            			g.write("    " + arg + " " + m[0] + "__" + str(i) + "; \n") 
        		if m[2] == "obj[int]" :
            			g.write("    Integer " + m[0] + "__ret ; \n") 
        		elif m[2] == "obj[String]" : 
            			g.write("    String " + m[0] + "__ret ; \n") 
        		elif m[2] != "void" :
            			g.write("    " + m[2] + " " + m[0] + "__ret ; \n") 
	
		g.write('\t\tLinkedList<ArgType> args;\n') 
		
		with open(testlog,'r') as f:
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
						g.write('\tprivate VT'+test.classname+'Test data;\n')
						g.write('\tprivate TraceRecord[] trace;\n')
	
						g.write('\tTestThread1(String name,VT'+test.classname+'Test q,TraceRecord[] tr){\n')
						g.write('\t\tsuper(name);\n')
						g.write('\t\tthis.data = q;\n')
						g.write('\t\tthis.trace = tr;\n')
						g.write('\t}\n\n')
	
						g.write('\tpublic void run(){\n')
						
						for m in test.methods : 
        						i = 0
        						for arg in m[1] :
            							i = i + 1
            							g.write("    " + arg + " " + m[0] + "__" + str(i) + "; \n") 
        						if m[2] == "obj[int]" :
            							g.write("    Integer " + m[0] + "__ret ; \n") 
        						elif m[2] == "obj[String]" : 
            							g.write("    String " + m[0] + "__ret ; \n") 
        						elif m[2] != "void" :
            							g.write("    " + m[2] + " " + m[0] + "__ret ; \n") 
						g.write('\t\tLinkedList<ArgType> args;\n')

						g.write('\t\targs = new LinkedList<ArgType>();\n')
						continue
 
				elif argu[0].startswith('#'):
					continue
				elif idx < tdNum1 - t1:
					idx = idx+1
					continue
				elif idx < bugloc1+1:
					
					g.write('\t\targs = new LinkedList<ArgType>();\n')
				
					m = test.methods[int(argu[0])]
					if len(m[1]) == 0:
						if m[2] == 'void':
							g.write('\t\t'+'data.vtMethod'+argu[0]+'();\n')
							g.write('\t\ttrace['+ str(idx-tdNum1+t1)+']=new TraceRecord(args,-1,'+argu[0]+',false);\n')
						else:
							g.write('\t\t'+m[0]+'__ret = data.vtMethod'+argu[0]+'();\n')
							g.write('\t\ttrace['+str(idx-tdNum1+t1)+'] = new TraceRecord(args,'+m[0]+'__ret,'+argu[0]+',false);\n')
					else:
						g.write('\t\t'+m[0]+'__1' +' ='+ argu[2]+';\n' )
						g.write('\t\targs.add(new ArgInt('+m[0]+'__1'+'));\n')
						if m[2] == 'void':
							g.write('\t\t'+'data.vtMethod'+argu[0]+'('+m[0]+'__1);\n')
							g.write('\t\ttrace['+ str(idx-tdNum1+t1)+']=new TraceRecord(args,-1,'+argu[0]+',false);\n')
						else:	
							g.write('\t\t'+m[0]+'__ret = data.vtMethod'+argu[0]+'('+m[0]+'__1);\n')
							g.write('\t\ttrace['+str(idx-tdNum1+t1)+'] = new TraceRecord(args,'+m[0]+'__ret,'+argu[0]+',false);\n')
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

					m = test.methods[int(argu[0])]
#					g.write('\t\t'+m[0]+'__1' +' ='+ argu[2]+';\n' )
#					g.write('\t\targs.add(new ArgInt('+m[0]+'__1'+'));\n')
					if len(m[1]) == 0:
						if m[2] == 'void':
							g.write('\t\t'+'data.vtMethod'+argu[0]+'();\n')
							g.write('\t\ttrace['+ str(idx-tdNum1-tdNum2+t2)+']=new TraceRecord(args,-1,'+argu[0]+',false);\n')
						else:
							g.write('\t\t'+m[0]+'__ret = data.vtMethod'+argu[0]+'();\n')
							g.write('\t\ttrace['+ str(idx-tdNum1-tdNum2+t2)+'] = new TraceRecord(args,'+m[0]+'__ret,'+argu[0]+',false);\n')
					else:
						g.write('\t\t'+m[0]+'__1' +' ='+ argu[2]+';\n' )
						g.write('\t\targs.add(new ArgInt('+m[0]+'__1'+'));\n')
						if m[2] == 'void':
							g.write('\t\t'+'data.vtMethod'+argu[0]+'('+m[0]+'__1);\n')
							g.write('\t\ttrace['+ str(idx-tdNum1-tdNum2+t2)+']=new TraceRecord(args,-1,'+argu[0]+',false);\n')
						else:	
							g.write('\t\t'+m[0]+'__ret = data.vtMethod'+argu[0]+'('+m[0]+'__1);\n')
							g.write('\t\ttrace['+ str(idx-tdNum1-tdNum2+t2)+'] = new TraceRecord(args,'+m[0]+'__ret,'+argu[0]+',false);\n')

#					if int(argu[0])==0:
#						g.write('\t\tadd_1 = '+argu[2]+';\n')
#						g.write('\t\targs.add(new ArgInt(add_1));\n')
#						g.write('\t\tadd_ret = data.vtMethod0(add_1);\n')
#						g.write('\t\ttrace['+str(idx-tdNum1-tdNum2+t2)+'] = new TraceRecord(args,add_ret,0,false);\n')
#					else:
#						g.write('\t\tremove_1 = '+argu[2]+';\n')
#						g.write('\t\targs.add(new ArgInt(remove_1));\n')
#						g.write('\t\tremove_ret = data.vtMethod1(remove_1);\n')
#						g.write('\t\ttrace['+str(idx-tdNum1-tdNum2+t2)+'] = new TraceRecord(args,remove_ret,1,false);\n')
#
					idx = idx + 1
					continue
				else:
					idx = idx + 1
					continue
		g.write('\t}\n')
		g.write('}\n\n')
