package excepciones;
public class CodigoBarrasException extends Exception{
	private static final long serialVersionUID = 1L;
	public CodigoBarrasException() {
		// TODO Auto-generated constructor stub
		super("El c�digo de barras no es correcto");
	}

}
