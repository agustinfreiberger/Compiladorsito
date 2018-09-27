package Compilador;
public class Casilla {

	int estado=-1;
	private AccionSemantica a;
	
	public Casilla (int e, AccionSemantica a) {
		this.a = a;
		this.estado = e;
	}
	
	public int getEstado() {
		return this.estado;
	}
	
	public AccionSemantica getAccion() {
		return this.a;
	}
	
	
}