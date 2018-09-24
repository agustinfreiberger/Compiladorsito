public abstract class AccionSemantica {

	String Buffer = "";
	
	public abstract int execute(String Buffer, char c);
	
	public String getBuffer(){
		return this.Buffer;
	}
	public boolean acomodarLinea(){
		return false;
	}
}