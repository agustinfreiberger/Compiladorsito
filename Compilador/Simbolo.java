package Compilador;

public class Simbolo {
	private String valor;
	private String tipo;
	private String uso;
	
	public Simbolo(String valor) {
		this.valor = valor;
	}
	
	public Simbolo(String valor, String tipo) {
		this.valor = valor;
		this.tipo = tipo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUso() {
		return uso;
	}

	public void setUso(String uso) {
		this.uso = uso;
	}
	
	public boolean equals(Simbolo s) {
		if (this.valor == s.getValor())   //Capas hay que agregar algun otro parametro de comparacion
			return true;
		return false;
	}
	
	
}
