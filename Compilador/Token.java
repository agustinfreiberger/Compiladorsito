package Compilador;

public class Token {
	private int token;
	private int linea;
	
	public Token (int l) {
		this.token =0;
		this.linea = l;
	}
	public Token(int t, int l) {
		this.token = t;
		this.linea = l;
	}

	public int getToken() {
		return token;
	}

	public void setToken(int token) {
		this.token = token;
	}

	public int getLinea() {
		return linea;
	}

	public void setLinea(int linea) {
		this.linea = linea;
	}
	
}
