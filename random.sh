#!/bin/bash
t=0
f=0
for i in `seq 100`
do
	output=`python veritrace.py test test/lockfreelist.conf`
	result=`java wgl.Tester wglconfig/config`
	echo $result
	if [ "$result" = "true" ]; then
		t=$(($t+1))
	else
		f=$(($f+1))
	fi
done
echo "$t"
echo "$f"		

