package compilator;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class SymbolTable {
	private Integer index = 0;
	private HashMap <Integer, String> st = new HashMap <Integer, String>(); 
	
	public SymbolTable () {};
	
	
	public void add(String att) {			//agrego entrada a TS
		st.put(index, att);
		index++;
	}
	
	public String getPointer(String att) {       //devuelve puntero a hash como string
		if (st.containsValue(att))
			return (st.keySet().toString());
		else
			return "#";                      //sino esta devuelvo eso para diferenciar
	}
	
	
	//public String getValue(String att) 
		
	
	
	public boolean contains(String att) {
		return st.containsValue(att);
	}
}
