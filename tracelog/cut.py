import sys
with open('tracelog/'+sys.argv[1],'r') as f:
	p = f.readlines()
with open('tracelog/'+sys.argv[2],'w') as w:
	i = 0
	for line in p:
		if i < 3:
			w.write(line);
			i +=1
		elif i < int(sys.argv[3]) + 3:
			i +=1
		else:
			w.write(line)
