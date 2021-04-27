package controlador;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import excepciones.CamposVaciosException;
import excepciones.CodigoBarrasException;
import modelo.Medicamento;
public class FarmaciaController {
private List<Medicamento> listaMediCam=new ArrayList<Medicamento>();
private BufferedReader br;
	public FarmaciaController() throws IOException, CamposVaciosException, CodigoBarrasException, ParseException {
		// TODO Auto-generated constructor stub
		File file=new File("medicamentos.txt");
		if(!file.exists()) file.createNewFile();
		FileReader fr=new FileReader("medicamentos.txt");
		br = new BufferedReader(fr);
		String nombre, fechaCaducidad, fechaCompra, laboratorio, codBarras;
		String precio;
		String[]datosMedicamentos=null;
		String linea="";
		Medicamento medicamento=null;
		while((linea=br.readLine())!=null) {
			datosMedicamentos=linea.split(",");
			nombre=datosMedicamentos[0];
			fechaCaducidad=datosMedicamentos[1];
			fechaCompra=datosMedicamentos[2];
			precio=datosMedicamentos[3];
			laboratorio=datosMedicamentos[4];
			codBarras=datosMedicamentos[5];
			medicamento=new Medicamento(nombre, fechaCaducidad, fechaCompra, precio, laboratorio, codBarras);
			listaMediCam.add(medicamento);
			medicamento=null;
		}
	}
	public List<Medicamento> getListaMediCam() {
		return listaMediCam;
	}
	public void setListaMediCam(List<Medicamento> listaMediCam) {
		this.listaMediCam = listaMediCam;
	}
	public void mostrarMedicamentos() {
		for(Medicamento medic:listaMediCam) {
			System.out.println(medic);
		}
	}
	public boolean agregarMedicamento(Medicamento medicamento){
		boolean agregado=false;
		if(!listaMediCam.contains(medicamento)) {
			listaMediCam.add(medicamento);
			agregado=true;
		}
		return agregado;
	}
	public Medicamento buscarMedicamento(String codBarras) {
		Medicamento medicamento=null;
		for(Medicamento medic:listaMediCam) {
			if(medic.getCodBarras().equals(codBarras)) {
				medicamento=medic;
			}
		}
		return medicamento;
	}
	public boolean modificarMedicamento(String codBarras, String precio) {
		boolean modificado=false;
		Medicamento medicamento=buscarMedicamento(codBarras);
		if(medicamento!=null) {
			medicamento.setPrecio(precio);
			modificado=true;
		}
		return modificado;
	}
	public boolean retirarMedicamento(Medicamento medic) {
		boolean retirado=false;
		listaMediCam.remove(medic);
		retirado=true;
		return retirado;
	}
	public List<Medicamento> filtrarMedicamento(String nombre){
		List<Medicamento> listMedicamentos=new ArrayList<Medicamento>();
		for(Medicamento medicament:listaMediCam) {
			if(medicament.getNombre().equals(nombre))
				listMedicamentos.add(medicament);
		}
		if(listMedicamentos.size()==0) listMedicamentos=null;
		return listMedicamentos;		
	}
	public boolean salvarDatos() throws IOException {
		boolean salvado=false;
		FileWriter fw=new FileWriter("medicamentos2.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		for(Medicamento medic:listaMediCam) {
			bw.write(medic.toString());
			bw.newLine();
		}
		fw.flush();
		bw.close();
		fw.close();
		salvado=true;
		return salvado;
	}
}