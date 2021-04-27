package modelo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import excepciones.CamposVaciosException;
import excepciones.CodigoBarrasException;
public class Medicamento {
	private String nombre;
	private java.sql.Date fechaCaducidad;
	private java.sql.Timestamp fechaCompra;
	private double precio;
	private String laboratorio;
	private String codBarras;
	public Medicamento() {
		// TODO Auto-generated constructor stub
		this.nombre=null;
		this.fechaCaducidad=null;
		this.fechaCompra=null;
		this.precio=0.0;
		this.laboratorio=null;
		this.codBarras=null;
	}
	public Medicamento(String nombre, String fechaCaducidad, String fechaCompra, String precio, String laboratorio,
			String codBarras) throws CamposVaciosException, CodigoBarrasException, ParseException {
		super();
		this.setNombre(nombre);
		this.setFechaCaducidad(fechaCaducidad);
		this.setFechaCompra(fechaCompra);
		this.setPrecio(precio);
		this.setLaboratorio(laboratorio);
		this.setCodBarras(codBarras);
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) throws CamposVaciosException{
		if(nombre.equals("")) throw new CamposVaciosException("nombre");
		this.nombre = nombre;
	}
	public java.sql.Date getFechaCaducidad() {
		return fechaCaducidad;
	}
	public void setFechaCaducidad(String fechaCaducidad) throws ParseException {
		java.util.Date fecha=null;
		SimpleDateFormat formateador=new SimpleDateFormat("yyyy-MM-dd");
		formateador.setLenient(false);
		fecha=formateador.parse(fechaCaducidad);
		this.fechaCaducidad = new java.sql.Date(fecha.getTime());
	}
	public java.sql.Timestamp getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(String fechaCompra) throws ParseException {
		java.util.Date fecha=null;
		SimpleDateFormat formateador=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		fecha=formateador.parse(fechaCompra);
		this.fechaCompra = new java.sql.Timestamp(fecha.getTime());
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio =Double.parseDouble(precio);
	}
	public String getLaboratorio() {
		return laboratorio;
	}
	public void setLaboratorio(String laboratorio) throws CamposVaciosException {
		if(laboratorio.equals("")) throw new CamposVaciosException("laboratorio");
		this.laboratorio = laboratorio;
	}
	public String getCodBarras() {
		return codBarras;
	}
	public void setCodBarras(String codBarras) throws CodigoBarrasException, CamposVaciosException {
		if (codBarras.equals("")) {
			throw new CamposVaciosException("codigoBarras");
		}
		int suma=Integer.parseInt(codBarras.substring(0,1));
		for (int x=1;x<codBarras.length()-1;x++) {
			if (x%2!=0) {
				suma+=Integer.parseInt(codBarras.substring(x,x+1))*3;
			}else {
				suma+=Integer.parseInt(codBarras.substring(x,x+1))*1;
			}
		}
		int DC=Integer.parseInt(codBarras.substring(codBarras.length()-1));
		int resto=suma%10;
		int decenaSuperior=0;
		if (suma%10>0){
			decenaSuperior=suma+(10-resto);
		}
		int digitoControl=decenaSuperior-suma;
		if (DC==digitoControl){
			this.codBarras = codBarras;
		}else {
			throw new CodigoBarrasException();
		}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codBarras == null) ? 0 : codBarras.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicamento other = (Medicamento) obj;
		if (codBarras == null) {
			if (other.codBarras != null)
				return false;
		} else if (!codBarras.equals(other.codBarras))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Medicamento [nombre=" + nombre + ", fechaCaducidad=" + fechaCaducidad + ", fechaCompra=" + fechaCompra
				+ ", precio=" + precio + ", laboratorio=" + laboratorio + ", codBarras=" + codBarras + "]";
	}
}