#!/bin/bash
t=0
f=0
for i in `seq 100`
do
	out=`java -agentpath:/home/xavi/VeriTrace1/jvmagent/TraceAgent.so=/home/xavi/VeriTrace1/tracelog/loc_OptimisticLinkedQueue_13302.temp.temp,2,1000,OptimisticLinkedQueueOfferPoll,OptimisticLinkedQueue,2 test.OptimisticLinkedQueueOfferPoll.RerunTraceOptimisticLinkedQueue13302`
	py=`python tracelog/cut.py loc_OptimisticLinkedQueue_13302.temp.temp.jvmlog loc_OptimisticLinkedQueue_13302.temp.jvmlog 751`
	result=`java wgl.Tester test wglconfig/loc_olqueue_13302_config wgl/preprocess`
	echo $result
	if [ "$result" = "true" ]; then
		t=$(($t+1))
	elif [ "$result" = "false" ]; then
		f=$(($f+1))
	fi
done
echo "$t"
echo "$f"
