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
	static String[] rechazados = new String[0];
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		mostrarMenu();

		System.out.print("Ingrese opción: ");
		int opcion = scanner.nextInt();
		System.out.println("");

		while (opcion < 1 || opcion > 7) {

			System.out.println("Opción invalida, intentelo nuevamente");
			System.out.println("");
			mostrarMenu();
			System.out.print("Ingrese opción: ");
			opcion = scanner.nextInt();
			System.out.println("");

		}

		while (opcion != 7) {

			switch (opcion) {

			case 1:

				cargarArchivos();

				System.out.println("Archivos cargados con exito!");
				System.out.println("- " + nombres.length + " alumnos en la lista.");
				System.out.println("- " + solicitudes.length + " solicitudes de ingreso.");
				System.out.println("");
				mostrarMenu();
				break;

			case 2:

				procesarSolicitudes();
				System.out.println("");
				mostrarMenu();
				break;

			case 3:
				inscripcionManual();
				System.out.println("");
				mostrarMenu();
				break;
			case 4:
				administracionCurso();
				System.out.println("");
				mostrarMenu();
				break;
			case 5:
				generarReportes();
				System.out.println("");
				mostrarMenu();
				break;
			case 6:
				analisisEstadistico();
				System.out.println("");
				mostrarMenu();
				break;
			case 7:
				break;

			}

			System.out.print("Ingrese opción: ");
			opcion = scanner.nextInt();
			System.out.println("");

			while (opcion < 1 || opcion > 7) {

				System.out.println("Opción invalida, intentelo nuevamente.");
				System.out.println("");
				mostrarMenu();
				System.out.print("Ingrese opción: ");
				opcion = scanner.nextInt();
				System.out.println("");

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
		System.out.println("");

	}

	private static void analisisEstadistico() {
		
		if (ruts == null || solicitudes == null) {
			System.out.println("Primero debe cargar los archivos.");
			return;
		}

		int totalSolicitudes = solicitudes.length;
		int totalRechazados = rechazados.length;
		int totalAdmitidos = totalSolicitudes - totalRechazados;

		int alumnosC1 = 0;
		int alumnosC2 = 0;

		for (int i = 0; i < paralelos.length; i++) {

			if (paralelos[i].equals("C1")) {
				alumnosC1++;
			} else if (paralelos[i].equals("C2")) {
				alumnosC2++;
			}
		}

		double porcentajeRechazados = 0;
		double porcentajeC1 = 0;
		double porcentajeC2 = 0;
		double tasaAdmision = 0;

		if (totalSolicitudes > 0) {
			porcentajeRechazados = (totalRechazados * 100.0) / totalSolicitudes;
			tasaAdmision = (totalAdmitidos * 100.0) / totalSolicitudes;
		}

		if (nombres.length > 0) {
			porcentajeC1 = (alumnosC1 * 100.0) / nombres.length;
			porcentajeC2 = (alumnosC2 * 100.0) / nombres.length;
		}

		System.out.println("===== ANALISIS ESTADISTICO =====");
		System.out.println("");

		System.out.println("1) Solicitudes rechazadas:");
		System.out.println("- Total de intentos: " + totalSolicitudes);
		System.out.println("- Total rechazadas: " + totalRechazados);
		System.out.println("- Porcentaje de rechazo: " + porcentajeRechazados + "%");
		System.out.println("");

		System.out.println("2) Alumnos por paralelo:");
		System.out.println("- C1: " + alumnosC1 + " alumnos (" + porcentajeC1 + "%)");
		System.out.println("- C2: " + alumnosC2 + " alumnos (" + porcentajeC2 + "%)");
		System.out.println("");

		System.out.println("3) Tasa de admision:");
		System.out.println("- Total admitidos: " + totalAdmitidos);
		System.out.println("- Tasa de admision: " + tasaAdmision + "%");

	}

	private static void generarReportes() {
		
		if (ruts == null || solicitudes == null) {
			System.out.println("Los reportes estan vacios, cargue los archivos primero.");
			return;
		}

		menuGenerarReportes();
		System.out.print("Ingrese opcion: ");
		int opcion = scanner.nextInt();
		System.out.println("");

		while (opcion < 1 || opcion > 3) {

			System.out.println("Opción invalida, intentelo nuevamente.");
			System.out.print("Ingrese opcion: ");
			opcion = scanner.nextInt();
			System.out.println("");

		}

		switch (opcion) {
		case 1:
			generarReportesC1();
			break;
		case 2:
			generarReportesC2();
			break;
		case 3:
			generarReportesRechazados();
			break;
		}

	}

	private static void generarReportesRechazados() {

		try {

			int versionRechazados = 1;

			File archivoRechazados = new File("Rechazados-V" + versionRechazados + ".txt");

			while (archivoRechazados.exists()) {

				versionRechazados++;
				archivoRechazados = new File("Rechazados-V" + versionRechazados + ".txt");
			}

			FileWriter escritorArchivoRechazados = new FileWriter(archivoRechazados);
			BufferedWriter escritorRechazados = new BufferedWriter(escritorArchivoRechazados);

			escritorRechazados.write("=== Solicitudes rechazadas ===");
			escritorRechazados.newLine();

			for (int i = 0; i < rechazados.length; i++) {

				escritorRechazados.write(rechazados[i] + "\n");
			}

			escritorRechazados.close();

			System.out.println("Reporte de rechazados generado correctamente.");
			System.out.println("");

		} catch (IOException e) {

			System.out.println("Error al crear el reporte de rechazados.");
			System.out.println("");
		}

	}

	private static void generarReportesC2() {

		try {

			int versionC2 = 1;

			File archivoC2 = new File("ReporteC2-V" + versionC2 + ".txt");

			while (archivoC2.exists()) {

				versionC2++;
				archivoC2 = new File("ReporteC2-V" + versionC2 + ".txt");
			}

			FileWriter escritorArchivoC2 = new FileWriter(archivoC2);
			BufferedWriter escritorC2 = new BufferedWriter(escritorArchivoC2);

			escritorC2.write("=== Miembros del grupo - Paralelo C2 ===");
			escritorC2.newLine();

			for (int i = 0; i < nombres.length; i++) {

				if (paralelos[i].equals("C2")) {

					escritorC2.write(nombres[i] + " - " + ruts[i] + "\n");
				}
			}

			escritorC2.close();

			System.out.println("Reporte C2 generado correctamente.");
			System.out.println("");

		} catch (IOException e) {

			System.out.println("Error al crear el reporte C2.");
			System.out.println("");
		}

	}

	private static void generarReportesC1() {

		try {

			int versionC1 = 1;

			File archivoC1 = new File("ReporteC1-V" + versionC1 + ".txt");

			while (archivoC1.exists()) {

				versionC1++;
				archivoC1 = new File("ReporteC1-V" + versionC1 + ".txt");
			}

			FileWriter escritorArchivoC1 = new FileWriter(archivoC1);
			BufferedWriter escritorC1 = new BufferedWriter(escritorArchivoC1);

			escritorC1.write("=== Miembros del grupo - Paralelo C1 ===");
			escritorC1.newLine();

			for (int i = 0; i < nombres.length; i++) {

				if (paralelos[i].equals("C1")) {

					escritorC1.write(nombres[i] + " - " + ruts[i] + "\n");
				}
			}

			escritorC1.close();

			System.out.println("Reporte C1 generado correctamente.");
			System.out.println("");

		} catch (IOException e) {

			System.out.println("Error al crear el reporte C1.");
			System.out.println("");

		}

	}

	private static void menuGenerarReportes() {

		System.out.println("===== Generar reportes =====");
		System.out.println("");
		System.out.println("1) Reporte Paralelo C1");
		System.out.println("2) Reporte Paralelo C2");
		System.out.println("3) Reporte de solicitudes rechazadas");
		System.out.println("");

	}

	private static void administracionCurso() {

		if (ruts == null || solicitudes == null) {
			System.out.println("Primero debe cargar los archivos.");
			return;
		}

		menuAdministracionCurso();

		System.out.print("Ingrese opcion: ");
		int opcion = scanner.nextInt();
		System.out.println("");

		switch (opcion) {
		case 1:
			cambiarParalelo();
			break;
		case 2:
			eliminarAlumno();
			break;
		case 3:
			nuevoAlumno();
			break;
		}

	}

	private static void nuevoAlumno() {

		scanner.nextLine();

		System.out.print("Ingrese nombre: ");
		String nombre = scanner.nextLine();

		System.out.print("Ingrese apellido: ");
		String apellido = scanner.nextLine();

		System.out.print("Ingrese RUT (xxxxxxxx-x): ");
		String rut = scanner.nextLine();

		System.out.print("Ingrese paralelo (C1 o C2): ");
		String paralelo = scanner.nextLine().toUpperCase();

		while (!paralelo.equalsIgnoreCase("C1") && !paralelo.equalsIgnoreCase("C2")) {

			System.out.print("Paralelo incorrecto, intentelo nuevamente: ");
			paralelo = scanner.nextLine().toUpperCase();
		}

		String[] nuevosNombres = new String[nombres.length + 1];
		String[] nuevosRuts = new String[ruts.length + 1];
		String[] nuevosParalelos = new String[paralelos.length + 1];

		for (int i = 0; i < nombres.length; i++) {

			nuevosNombres[i] = nombres[i];
			nuevosRuts[i] = ruts[i];
			nuevosParalelos[i] = paralelos[i];
		}

		nuevosNombres[nombres.length] = nombre + " " + apellido;
		nuevosRuts[ruts.length] = rut;
		nuevosParalelos[paralelos.length] = paralelo;

		nombres = nuevosNombres;
		ruts = nuevosRuts;
		paralelos = nuevosParalelos;

		System.out.println("Alumno agregado correctamente.");
		System.out.println("");

		guardarAlumnos();

	}

	private static void eliminarAlumno() {

		System.out.print("Ingrese RUT del alumno: ");
		String rut = scanner.next();

		int posicion = -1;

		for (int i = 0; i < ruts.length; i++) {

			if (rut.equals(ruts[i])) {

				posicion = i;
				break;

			}
		}

		if (posicion == -1) {

			System.out.println("Alumno no encontrado.");
			System.out.println("");

		} else {

			String[] nuevosNombres = new String[nombres.length - 1];
			String[] nuevosRuts = new String[ruts.length - 1];
			String[] nuevosParalelos = new String[paralelos.length - 1];

			int j = 0;

			for (int i = 0; i < nombres.length; i++) {

				if (i != posicion) {

					nuevosNombres[j] = nombres[i];
					nuevosRuts[j] = ruts[i];
					nuevosParalelos[j] = paralelos[i];

					j++;
				}
			}

			nombres = nuevosNombres;
			ruts = nuevosRuts;
			paralelos = nuevosParalelos;

			System.out.println("Alumno eliminado.");
			System.out.println("");

			guardarAlumnos();

		}

	}

	private static void guardarAlumnos() {

		try {

			File archivo = new File("Alumnos.txt");

			FileWriter escritorArchivo = new FileWriter(archivo);
			BufferedWriter escritor = new BufferedWriter(escritorArchivo);

			for (int i = 0; i < nombres.length; i++) {

				String[] partes = nombres[i].split(" ");

				escritor.write(partes[0] + ";" + partes[1] + ";" + ruts[i] + ";" + paralelos[i] + "\n");

			}

			escritor.close();

		} catch (IOException e) {

			System.out.println("Error al guardar Alumnos.txt.");
			System.out.println("");

		}

	}

	private static void cambiarParalelo() {

		System.out.print("Ingrese RUT del alumno: ");
		String rut = scanner.next();

		boolean encontrado = false;

		for (int i = 0; i < ruts.length; i++) {

			if (rut.equals(ruts[i])) {

				if (paralelos[i].equals("C1")) {

					paralelos[i] = "C2";

				} else {

					paralelos[i] = "C1";

				}

				System.out.println("Nuevo paralelo: " + paralelos[i]);
				System.out.println("");

				encontrado = true;
				break;
			}
		}

		if (!encontrado) {

			System.out.println("Alumno no encontrado.");
			System.out.println("");

		}

	}

	private static void menuAdministracionCurso() {

		System.out.println("===== Administración del curso =====");
		System.out.println("1) Cambiar paralelo");
		System.out.println("2) Eliminar alumno");
		System.out.println("3) Inscribir alumno nuevo");
		System.out.println("");

	}

	private static void inscripcionManual() {
		
		if (ruts == null || solicitudes == null) {
			System.out.println("Primero debe cargar los archivos.");
			return;
		}

		System.out.println("Como desea inscribir a la persona?");
		System.out.println("1) Por nombre completo");
		System.out.println("2) Por RUT");
		System.out.print("Ingrese opcion: ");
		int opcion = scanner.nextInt();
		scanner.nextLine();

		while (opcion != 1 && opcion != 2) {

			System.out.println("Opción invalida, intentelo nuevamente");
			System.out.print("Ingrese opción: ");
			opcion = scanner.nextInt();
			scanner.nextLine();
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

			if (rut.equals(ruts[i])) {

				System.out.println(rut + " Admitido.");
				System.out.println("");
				encontrado = true;
				break;

			}

		}

		if (!encontrado) {

			System.out.println(rut + " No encontrado, añadido al registro de rechazados");
			System.out.println("");
			modificarRegistroRechazados("No tenemos su nombre, solo su rut: " + rut);

		}

	}

	private static void inscripcionNombre(String nombre) {

		boolean encontrado = false;

		for (int i = 0; i < nombres.length; i++) {

			if (nombre.toUpperCase().equals(nombres[i].toUpperCase())) {

				System.out.println(nombre + " Admitido.");
				System.out.println("");
				encontrado = true;
				break;

			}

		}

		if (!encontrado) {

			System.out.println(nombre + " No encontrado, añadido al registro de rechazados");
			System.out.println("");
			modificarRegistroRechazados(nombre);

		}

	}

	private static void modificarRegistroRechazados(String dato) {

		String[] listaTemporal = new String[rechazados.length + 1];

		for (int i = 0; i < rechazados.length; i++) {
			listaTemporal[i] = rechazados[i];
		}

		listaTemporal[rechazados.length] = dato;

		rechazados = listaTemporal;

	}

	private static void procesarSolicitudes() {

		if (ruts == null || solicitudes == null) {
			System.out.println("Primero debe cargar los archivos.");
			return;
		}

		int admitidos = 0;
		int rechazados = 0;

		for (int i = 0; i < solicitudes.length; i++) {

			boolean encontrado = false;

			for (int j = 0; j < nombres.length; j++) {

				if (solicitudes[i].toUpperCase().equals(nombres[j].toUpperCase())) {

					System.out.println("[OK]       " + nombres[j] + " -> admitido en " + paralelos[j]);
					admitidos++;
					encontrado = true;
					break;

				}

			}

			if (!encontrado) {

				System.out.println("[RECHAZO]  " + solicitudes[i] + " -> no pertenece a ningun paralelo");
				rechazados++;

				modificarRegistroRechazados(solicitudes[i] + " - No pertenece a ningun paralelo del curso");

			}

		}

		System.out.println("");
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
