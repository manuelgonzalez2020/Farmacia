package excepciones;
public class CamposVaciosException extends Exception{
	public CamposVaciosException(String campo) {
		// TODO Auto-generated constructor stub
		super("El campo "+campo+" está vacío");
	}
}
