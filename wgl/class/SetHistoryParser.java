package wgl;

public class SetHistoryParser extends HistoryParser{

	public SetHistoryParser(String s,String t){
		super(s,t);
	}
	
	
	@Override
	protected void formHistory() {
		// TODO Auto-generated method stub
		int index[] = new int[threadNum];
		int callOrRet[] = new int[threadNum];
		HistoryNode [] mat = new HistoryNode[threadNum];
		int tmpOpId[] = new int[threadNum];
		for(int i = 0;i < threadNum;i++){
			index[i] = 0;
			callOrRet[i] = 0;
			mat[i] = null;
			tmpOpId[i] = 0;
		}
		
		int nodeId = 0;
		int opId = 0;
		
		while(true){
			int currentMin = Integer.MAX_VALUE;
			int minIndex = -1;
			
			
			for(int m = 0;m < threadNum;m++){
				if(index[m]==traceNum[m]){
					continue;
				}
				int cur = callOrRet[m]==0?s[m][index[m]].start:s[m][index[m]].end;
				
				if(cur < currentMin){
					currentMin = cur;
					minIndex = m;
				}
			}
			
			
			TmpOp to = s[minIndex][index[minIndex]];
			int tOpId = callOrRet[minIndex]==0?opId:tmpOpId[minIndex];
			
			boolean tRet = to.ret==0?false:true;//set-specific code
			HistoryNode dn = new HistoryNode(nodeId,tOpId,
											new SetOperation(tOpId,to.opT,minIndex,to.arg,tRet,to.start,to.end));//set-specific code
			
			
			
			if(callOrRet[minIndex]==0){
				callOrRet[minIndex] = 1;
				mat[minIndex] = dn;
				tmpOpId[minIndex] = opId;
				opId++;
			}else{
				index[minIndex]++;
				callOrRet[minIndex] = 0;
				mat[minIndex].setMatch(dn);//有可能出错
				
			}
			
			/****************************************************************************************/
			
			dll.insert(dn);
			
			
			/****************insert the right value*************************************/
			
			/****************************************************************************************/
			
			int quitFlag = 1;
			for(int n = 0;n < threadNum;n++){
				if(index[n]!=traceNum[n]){
					quitFlag = 0;
					break;
				}
			}
			if(quitFlag == 1)
				break;
			
			nodeId++;
			
		}
	}
	
	/*public static void main(String[] args){
	//	SetHistoryParser shp = new SetHistoryParser();
		DoubleLinkedList dll = shp.parse(3, 1);
		dll.print();
	}*/
	
}
