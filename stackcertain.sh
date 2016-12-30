#!/bin/bash
t=0
f=0
for i in `seq 100`
do
	out=`java -agentpath:/home/xavi/VeriTrace1/jvmagent/TraceAgent.so=/home/xavi/VeriTrace1/tracelog/treiberstack_whole.temp,2,1000,TreiberStackPushPop,TreiberStack,2 test.TreiberStackPushPop.RerunTraceTreiberStackwhole`
	result=`java wgl.Tester test wglconfig/tsconf`
	echo $result
	if [ "$result" = "true" ]; then
		t=$(($t+1))
	else
		f=$(($f+1))
	fi
done
echo "$t"
echo "$f"		

