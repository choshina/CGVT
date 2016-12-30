#!/bin/bash
t=0
f=0
for i in `seq 100`
do
	out=`java -agentpath:/home/xavi/VeriTrace1/jvmagent/TraceAgent.so=/home/xavi/VeriTrace1/tracelog/pct_unsafesizelist.temp,2,500,UnsafeSizeListAddRemoveSize,UnsafeSizeList,2 test.UnsafeSizeListAddRemoveSize.TestingUnsafeSizeListT2L500AddRemoveSize tracelog/pct_unsafesizelist.temp.testlog`
#	py=`python tracelog/cut.py 180.temp.temp.jvmlog 180.temp.jvmlog 121`
	result=`java wgl.Tester test wglconfig/pctdet_unsafesizelist`
	echo $result
	if [ "$result" = "true" ]; then
		t=$(($t+1))
	elif [ "$result" = "false" ]; then
		f=$(($f+1))
	fi
done
echo "$t"
echo "$f"
