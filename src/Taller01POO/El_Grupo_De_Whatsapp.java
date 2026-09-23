//Luis Lanas - 21.960.962-0 - ICCI

package Taller01POO;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class El_Grupo_De_Whatsapp {

	static String[] nombres;
	static String[] ruts;
	static String[] paralelos;
	static String[] solicitudes;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		mostrarMenu();

		System.out.print("Ingrese opción: ");
		int opcion = scanner.nextInt();

		while (opcion < 1 || opcion > 7) {

			System.out.println("Opción invalida, intentelo nuevamente");
			System.out.print("Ingrese opción: ");
			opcion = scanner.nextInt();

		}

		while (opcion != 7) {

			switch (opcion) {

			case 1:

				cargarArchivos();

				System.out.println("Archivos cargados con exito!");
				System.out.println("- " + nombres.length + " alumnos en la lista.");
				System.out.println("- " + solicitudes.length + " solicitudes de ingreso.");

				break;

			case 2:

				procesarSolicitudes();
				break;

			case 3:
				inscripcionManual();
				break;
			case 4:
				administracionCurso();
				break;
			case 5:
				generarReportes();
				break;
			case 6:
				analisisEstadistico();
				break;
			case 7:
				break;

			}

			System.out.print("Ingrese opción: ");
			opcion = scanner.nextInt();

			while (opcion < 1 || opcion > 7) {

				System.out.println("Opción invalida, intentelo nuevamente");
				System.out.print("Ingrese opción: ");
				opcion = scanner.nextInt();

			}

		}

		System.out.println("Adios.");

	}

	private static void mostrarMenu() {

		System.out.println("===== Sistema de Control del Grupo POO =====");
		System.out.println("1) Cargar archivos (Alumnos y Solicitudes)");
		System.out.println("2) Procesar solicitudes (Filtrado automatico)");
		System.out.println("3) Inscripcion manual al grupo");
		System.out.println("4) Administracion del curso");
		System.out.println("5) Generar reportes");
		System.out.println("6) Analisis estadistico");
		System.out.println("7) Salir");

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

		Scanner scanner = new Scanner(System.in);
		int opcion = scanner.nextInt();

		System.out.println("Como desea inscribir a la persona?");
		System.out.println("1) Por nombre completo");
		System.out.println("2) Por RUT");
		System.out.print("Ingrese opcion: ");

		while (opcion != 1 || opcion != 2) {

			System.out.println("Opción invalida, intentelo nuevamente");
			System.out.print("Ingrese opción: ");
			opcion = scanner.nextInt();
			scanner.next();
		}

		switch (opcion) {

		case 1:
			System.out.print("Ingrese nombre completo: ");
			String nombre = scanner.nextLine();
			inscripcionNombre(nombre);
			break;
		case 2:
			System.out.print("Ingrese RUT: ");
			String rut = scanner.nextLine();
			inscripcionRut(rut);
			break;
		}

	}

	private static void inscripcionRut(String rut) {

		boolean encontrado = false;

		for (int i = 0; i < nombres.length; i++) {

			if (rut.equals(nombres[i])) {

				System.out.println(rut + " Admitido.");
				encontrado = true;
				break;

			}

		}

		if (!encontrado) {

			System.out.println(rut + " No encontrado, añadido al registro de rechazados");
			modificarRegistroRechazados(rut); //Si se intentó por RUT y no está → como no tenemos su nombre, se registra el mensaje especial indicando que solo se dispone del RUT.

		}

	}

	private static void inscripcionNombre(String nombre) {

		boolean encontrado = false;

		for (int i = 0; i < nombres.length; i++) {

			if (nombre.equals(nombres[i])) {

				System.out.println(nombre + " Admitido.");
				encontrado = true;
				break;

			}

		}

		if (!encontrado) {

			System.out.println(nombre + " No encontrado, añadido al registro de rechazados");
			modificarRegistroRechazados(nombre);

		}

	}

	private static void modificarRegistroRechazados(String nombre) {
		// TODO Auto-generated method stub
		
	}

	private static void procesarSolicitudes() {

		int admitidos = 0;
		int rechazados = 0;

		for (int i = 0; i < solicitudes.length; i++) {

			boolean encontrado = false;

			for (int j = 0; j < nombres.length; j++) {

				if (solicitudes[i].equals(nombres[j])) {

					System.out.println("[OK]       " + nombres[j] + " -> admitido en " + paralelos[j]);
					admitidos++;
					encontrado = true;
					break;

				}

			}

			if (!encontrado) {

				System.out.println("[RECHAZO]  " + solicitudes[i] + " -> no pertenece a ningun paralelo");
				rechazados++;

			}

		}

		System.out.println("Resumen: " + admitidos + " admitidos / " + rechazados + " rechazados.");

	}

	private static void cargarArchivos() {

		try {

			File Alumnos = new File("Alumnos.txt");
			File Solicitudes = new File("Solicitudes.txt");

			Scanner lectorA = new Scanner(Alumnos);
			Scanner lectorS = new Scanner(Solicitudes);

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

			nombres = new String[contAlumnos];
			ruts = new String[contAlumnos];
			paralelos = new String[contAlumnos];
			solicitudes = new String[contSolicitudes];

			File Alumnos2 = new File("Alumnos.txt");
			File Solicitudes2 = new File("Solicitudes.txt");

			Scanner lectorA2 = new Scanner(Alumnos2);
			Scanner lectorS2 = new Scanner(Solicitudes2);

			int posicion = 0;

			while (lectorA2.hasNextLine()) {

				String linea = lectorA2.nextLine();
				String[] partes = linea.split(";");
				nombres[posicion] = partes[0] + " " + partes[1];
				ruts[posicion] = partes[2];
				paralelos[posicion] = partes[3];
				posicion++;
			}

			posicion = 0;

			while (lectorS2.hasNextLine()) {

				String linea = lectorS2.nextLine();
				String[] partes = linea.split("-");
				solicitudes[posicion] = partes[0] + " " + partes[1];
				posicion++;

			}

			lectorA.close();
			lectorS.close();
			lectorA2.close();
			lectorS2.close();

		} catch (FileNotFoundException e) {

			System.out.println("Archivo no encontrado, lo sentimos.");
		}

	}

}
