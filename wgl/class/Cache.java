package wgl;

import java.util.HashSet;
import java.util.Iterator;

public class Cache {

	
	private HashSet<LinearState> cache;
	
	public Cache(){
		cache = new HashSet<LinearState>();
		
	}
	
	public void add(LinearState e){
		cache.add(e);
	}
	
	public Cache copy(){
		Cache c = new Cache();
		//HashSet<LinearState> hs = new HashSet<LinearState>();
		Iterator<LinearState> it = cache.iterator();
		while(it.hasNext()){
			LinearState ls = it.next();
			LinearState ls2 = new LinearState(ls.linearized,ls.cs,ls.opOrder,ls.op);
			c.add(ls2);
		}
		return c;
	}
	
	/*
	 * 参数给定一个LinearState对象，查看这个对象，在当前的cache中是否存在
	 * 存在的含义是指linearized及state都一致
	 */
	public boolean exist(LinearState ls){
		Iterator<LinearState> it = cache.iterator();
		while(it.hasNext()){
			LinearState ln = it.next();
			boolean t1 = ln.getLinearized().compare(ls.getLinearized());
			boolean t2 = ln.cs.equal(ls.cs);
			if(t1==true&&t2==true){
				return true;
			}
			
		}
		return false;
	}
	
	/*
	 * 返回cache对象
	 */
	public HashSet<LinearState> getCache(){
		return cache;
	}

	
	/*
	 * 在当前的cache中，返回最大的LinearState对象
	 * 最大的含义是LinearState的linearized最长
	 */
	/*public LinearState getMaximum(){
		Iterator<LinearState> it = cache.iterator();
		LinearState re = it.next();
		while(it.hasNext()){
			LinearState ls = it.next();
			if(re.shorterthan(ls))
				re = ls;
		}
		return re;
	}*/
	
	private HashSet<CacheOrder> co;
	
	
	/*
	 * 将WGLChecker中返回的cachepath设置给co
	 */
	public void SetCacheOrder(HashSet<CacheOrder> h){
		co = h;
	}
	
	/*
	 * 取出最大的cacheOrde，也就是最接近真实情况的cacheOrder
	 */
	public CacheOrder longestPath(){
		Iterator<CacheOrder> it = co.iterator();
		int longest = -1;
		CacheOrder re = null;
		while(it.hasNext()){
			
			CacheOrder temp = it.next();
			int tsize = temp.size(); 
			if(tsize > longest){
				longest = tsize;
				re = temp;
			}
		}
		return re;
	}
	
	public String toString(){
		Iterator<LinearState> it = cache.iterator();
		String s = "";
		while(it.hasNext()){
			s= s+it.next().toString()+"\n";
		}
		return s;
	}
	
	
}
