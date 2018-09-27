package Compilador;
public class AS_IniciarBuffer extends AccionSemantica{

	@Override
	public int execute(String Buffer, char c) {
		this.Buffer = "";
		this.Buffer = this.Buffer + c;
		return 0;
	}
	
	public String getBuffer(){
		return this.Buffer;
	}
	
}
