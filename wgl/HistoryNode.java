package wgl;

public class HistoryNode implements Cloneable{
	protected HistoryNode prev;
	protected HistoryNode next;
	protected HistoryNode match;
	private int nodeId;
	private int opId;
	private Operation op;
	public HistoryNode(int n,int o,Operation op){
		nodeId = n;
		opId = o;
		this.op = op;
	}
	
	public boolean happenbefore(HistoryNode h){
		if(this.op.getEndtime()<h.op.getBegintime()){
			return true;
		}else
			return false;
	}
	
	public boolean hasNext(){
		return !(next == null);
	}
	
	public Operation getOperation(){
		return op;
	}
	
	public HistoryNode getPrev(){
		return prev;
	}
	
	public HistoryNode getNext(){
		return next;
	}
	
	public boolean isCall(){
		return !(match == null);
	}
	
	public void setMatch(HistoryNode hn){
		match = hn;
	}
	
	public int getType(){
		return op.getOpType();
	}
	
	public int getArg(){
		return op.getOpArg();
	}
	
	public int getRet(){
		return op.getOpRet();
	}
	
	public Object deepCopy(){
		HistoryNode n = new HistoryNode(this.nodeId,this.opId,(Operation)op.deepCopy());
		n.prev = this.prev;
		n.next = this.next;
		n.match = this.match;
		return n;
		
	}

	public int getNodeId() {
		return nodeId;
	}
	
	public int getThreadId(){
		return op.getThreadId();
	}

	public String toString(){
		if(nodeId == -1){
			return "head";
		}
		String s = "node ID:"+nodeId+" opID:"+opId+" 操作类型："+op.getOpType()+" iscall?:"+isCall()+" 参数："+op.getOpArg()+" 返回："+op.getOpRet()+" "+(next==null);
		if(next != null){
			s =s+" "+ next.getNodeId()+" "+next.getOpId();
		}
		if(prev != null){
			s = s+ " "+prev.getNodeId()+" "+prev.getOpId();
		}
		
		return s;
	}

	public int getOpId() {
		// TODO Auto-generated method stub
		return opId;
	}
	
	public void setOpRet(int i){
		op.setOpRet(i);
	}
	
}
