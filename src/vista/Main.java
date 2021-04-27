package vista;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import controlador.FarmaciaController;
import excepciones.CamposVaciosException;
import excepciones.CodigoBarrasException;
import modelo.Medicamento;
public class Main {
	private static Scanner leer;
	public Main() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		leer = new Scanner(System.in);
		Medicamento medic=null;
		try {
			medic=new Medicamento("Ibuprofeno", "2021-03-24", "2021-04-03 20:03:23.334", "34.32", "Laboratorio de Análisis de Nottingham", "5901234123457");
			System.out.println(medic);
		} catch (CamposVaciosException | CodigoBarrasException | ParseException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		FarmaciaController listMed=null;
		try {
			listMed=new FarmaciaController();
		} catch (IOException | CamposVaciosException | CodigoBarrasException | ParseException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		int opcion=0;
		do {
			System.out.println("1.-Mostrar Medicamentos");
			System.out.println("2.-Agregar Medicamento");
			System.out.println("3.-Buscar Medicamento");
			System.out.println("4.-Modificar Medicamento");
			System.out.println("5.-Retirar Medicamento");
			System.out.println("6.-Filtrar Medicamento");
			System.out.println("7.-Salvar Datos");
			System.out.println("8.-Salir");
			opcion=leer.nextInt();
			switch(opcion) {
			case 1:
				listMed.mostrarMedicamentos();
				break;
			case 2:
				try {
					medic=new Medicamento("ibuprofeno", "2022-08-08", "2021-08-01 12:24:38.123", "4.8", "tata", "5411786054708");
					if(listMed.agregarMedicamento(medic)) System.out.println("Medicamento agregado correctamente");
					else System.err.println("No se pudo guardar");
				} catch (CamposVaciosException | CodigoBarrasException | ParseException e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("Introduzca el código de barras");
				String codBarras=leer.next();
				medic=listMed.buscarMedicamento(codBarras);
				if(medic!=null) System.out.println(medic);
				else System.err.println("No se encontró el medicamento");
				break;
			case 4:
				System.out.println("Introduzca el código de barras del medicamento a modificar");
				codBarras=leer.next();
				System.out.println("Introduzca su nuevo precio");
				String precio=leer.next();
				if (listMed.modificarMedicamento(codBarras, precio)) System.out.println("Precio modificado");
				else System.out.println("Error en la modificación");
				break;
			case 5:
				System.out.println("Introduzca el código de barras del medicamento a retirar");
				codBarras=leer.next();
				medic=listMed.buscarMedicamento(codBarras);
				if(medic!=null) {
					int op=0;
					System.out.println("¿Desea retirar dicho medicamento?");
					System.out.println("1.-Sí 2.-No");
					op=leer.nextInt();
					if(op==1) {
						if(listMed.retirarMedicamento(medic)) System.out.println("Medicamento retirado correctamente");
						else System.err.println("Error al retirar");
					}else if (op==2) System.err.println("Error al retirar");
				}else System.err.println("Medicamento no encontrado");		
				break;
			case 6:
				System.out.println("Introduzca el nombre del medicamento");
				String nombre=leer.next();
				List <Medicamento> list=new ArrayList<Medicamento>();
				list=listMed.filtrarMedicamento(nombre);
				if(list!=null) System.out.println(list);
				else System.err.println("No hay medicamentos con ese nombre");
				break;
			case 7:
				try {
					if(listMed.salvarDatos()) System.out.println("Cambios guardados");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}	
				break;
			case 8:
				System.out.println("Gracias por visitar nuestra Farmacia");
			}
		}while(opcion!=8);
	}
}
