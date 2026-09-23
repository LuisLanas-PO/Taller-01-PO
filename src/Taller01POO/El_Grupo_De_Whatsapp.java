//Luis Lanas - 21.960.962-0 - ICCI

package Taller01POO;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class El_Grupo_De_Whatsapp {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("===== Sistema de Control del Grupo POO =====");
		System.out.println("1) Cargar archivos (Alumnos y Solicitudes)");
		System.out.println("2) Procesar solicitudes (Filtrado automatico)");
		System.out.println("3) Inscripcion manual al grupo");
		System.out.println("4) Administracion del curso");
		System.out.println("5) Generar reportes");
		System.out.println("6) Analisis estadistico");
		System.out.println("7) Salir");

		int opcion = scanner.nextInt();

		while (opcion < 1 || opcion > 7) {

			System.out.println("Opción invalida, intentelo nuevamente");
			opcion = scanner.nextInt();
		}

		switch (opcion) {

		case 1:
			cargarArchivos();
		case 2:
			procesarSolicitudes();
		case 3:
			inscripcionManual();
		case 4:
			administracionCurso();
		case 5:
			generarReportes();
		case 6:
			analisisEstadistico();
		case 7:
			break;
		}

	}

	private static void analisisEstadistico() {
		// TODO Auto-generated method stub

	}

	private static void generarReportes() {
		// TODO Auto-generated method stub

	}

	private static void administracionCurso() {
		// TODO Auto-generated method stub

	}

	private static void inscripcionManual() {
		// TODO Auto-generated method stub

	}

	private static void procesarSolicitudes() {
		// TODO Auto-generated method stub

	}

	private static void cargarArchivos() {
		
		try {
			
			File alumnos = new File("Alumnos.txt");
			File solicitudes = new File("Solicitudes.txt");
			
			Scanner lectorA = new Scanner(alumnos);
			Scanner lectorS = new Scanner(solicitudes);
			
			int contAlumnos = 0;
			int contSolicitudes = 0;
			
			while (lectorA.hasNextLine()) {
				
				contAlumnos++;
				lectorA.nextLine();
			}
			
			while (lectorS.hasNextLine()) {
				
				contSolicitudes++;
				lectorS.nextLine();
			}
			
		} catch (Exception e) {
			
			System.out.println("Archivo no encontrado, lo sentimos.");
			
		}

	}

}
