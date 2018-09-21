package compilator;

public class AS_IniciarBuffer extends AccionSemantica{

	@Override
	public int execute(String Buffer, char c) {
		Buffer = "";
		Buffer = Buffer + c;
		return 0;
	}
}
