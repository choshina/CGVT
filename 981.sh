#!/bin/bash
t=0
f=0
for i in `seq 100`
do
	out=`java -agentpath:/home/xavi/VeriTrace1/jvmagent/TraceAgent.so=/home/xavi/VeriTrace1/tracelog/180.temp.temp,2,1000,LockFreeListAddRemove,LockFreeList,2 test.LockFreeListAddRemove.RerunTraceLockFreeList180`
	py=`python tracelog/cut.py 180.temp.temp.jvmlog 180.temp.jvmlog 121`
	result=`java wgl.Tester wglconfig/config wgl/preprocess`
	echo $result
	if [ "$result" = "true" ]; then
		t=$(($t+1))
	elif [ "$result" = "false" ]; then
		f=$(($f+1))
	fi
done
echo "$t"
echo "$f"
