package jtrace;
public class ArgPair extends ArgType{

	
    class pair(){
	int _1;
	int _2;
	pair(int _1,int _2){
	    this._1 = _1;
	    this._2 = _2;
	}
    }
    private pair value;
    public ArgPair(pair v){
	argType = "pair";
	value = v;
    }

    public pair toPair(){
	return value;
    }

    public String toString(){
	String s="(";
	s+=value._1+","+value._2+")";
	return s;
    }


}
