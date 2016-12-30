package src.concurrency;

public class PairSnapShot<T> {
	class pair{
		T _1;
		T _2;
		pair(T _1,T _2){
			this._1=_1;
			this._2=_2;
		}
		
	}
	T[] data;
	
	@SuppressWarnings("unchecked")
	public PairSnapShot(){
		data =(T[]) new Object[2];
	}
	
	public void write(int i,T x){
		data[i] = x;
	}
	
	public pair readpair(int i, int j){
		while(true){
			T x = data[i];
			T y = data[j];
			T x2 = data[i];
			if(x==x2){
				return new pair(x,y);
			}
		}
	}
	
	public PairSnapShot<T> clone(){
		PairSnapShot<T> pss = new PairSnapShot<T>();
		pss.data[0] = data[0];
		pss.data[1] = data[1];
		return pss;
	}
	
	public String toString(){
		String s = "{";
		s+=data[0]+","+data[1]+"}";
		return s;
	}
}
