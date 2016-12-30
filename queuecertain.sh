#!/bin/bash
t=0
f=0
for i in `seq 100`
do
	out=`java -agentpath:/home/xavi/VeriTrace1/jvmagent/TraceAgent.so=/home/xavi/VeriTrace1/tracelog/loc_OptimisticLinkedQueue_whole.temp,2,1000,OptimisticLinkedQueueOfferPoll,OptimisticLinkedQueue,2 test.OptimisticLinkedQueueOfferPoll.RerunTraceOptimisticLinkedQueuewhole`
	result=`java wgl.Tester test wglconfig/loc_olqueue_whole_config`
	echo $result
	if [ "$result" = "true" ]; then
		t=$(($t+1))
	else
		f=$(($f+1))
	fi
done
echo "$t"
echo "$f"		

