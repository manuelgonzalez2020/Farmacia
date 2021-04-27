package excepciones;
public class CamposVaciosException extends Exception{
	private static final long serialVersionUID = 1L;
	public CamposVaciosException(String campo) {
		// TODO Auto-generated constructor stub
		super("El campo "+campo+" está vacío");
	}
}
