import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Proceso {     private static final int MAX_PATIENT_NAME_LEN = 50;
    private Cita[] citas; // Arreglo para almacenar las citas
    private int totalCitas; // Contador de citas almacenadas

    public Proceso() {
        citas = new Cita[100]; // Inicializar el arreglo de citas con una capacidad de 100
        totalCitas = 0; // Inicializar el contador de citas en 0
    }

    public void iniciar() {
        int choice;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Agendar cita");
            System.out.println("2. Buscar cita");
            System.out.println("3. Reagendar cita");
            System.out.println("4. Salir");
            System.out.print("Ingrese su opción: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    agendarCita(); // Llamar al método para agendar una cita
                    break;
                case 2:
                    buscarCita(); // Llamar al método para buscar una cita
                    break;
                case 3:
                    reagendarCita(); // Llamar al método para reagendar una cita
                    break;
                case 4:
                    guardarCitasEnArchivo(); // Llamar al método para guardar las citas en un archivo
                    scanner.close(); // Cerrar el scanner
                    System.exit(0); // Salir del programa
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
    }

    public void agendarCita() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del paciente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el número de cédula (10 dígitos): ");
        String cedula = scanner.nextLine();
        System.out.print("Ingrese la fecha de la cita: ");
        String fecha = scanner.nextLine();

        citas[totalCitas] = new Cita(nombre, cedula, fecha); // Crear una nueva cita y agregarla al arreglo
        totalCitas++; // Incrementar el contador de citas
        System.out.println("Cita creada con éxito");
    }

    public void buscarCita() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Buscar cita por:");
        System.out.println("1. Número de cédula");
        System.out.println("2. Nombre");
        System.out.print("Ingrese su opción: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        if (option == 1) {
            System.out.print("Ingrese el número de cédula (10 dígitos): ");
            String cedula = scanner.nextLine();

            boolean encontrado = false;

            for (int i = 0; i < totalCitas; i++) {
                if (citas[i].getCedula().equals(cedula)) {
                    System.out.println("Nombre del paciente: " + citas[i].getNombre());
                    System.out.println("Número de cédula: " + citas[i].getCedula());
                    System.out.println("Fecha de la cita: " + citas[i].getFecha());
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("Cita no encontrada");
            }
        } else if (option == 2) {
            System.out.print("Ingrese el nombre del paciente: ");
            String nombre = scanner.nextLine();

            boolean encontrado = false;

            for (int i = 0; i < totalCitas; i++) {
                if (citas[i].getNombre().equalsIgnoreCase(nombre)) {
                    System.out.println("Nombre del paciente: " + citas[i].getNombre());
                    System.out.println("Número de cédula: " + citas[i].getCedula());
                    System.out.println("Fecha de la cita: " + citas[i].getFecha());
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("Cita no encontrada");
            }
        } else {
            System.out.println("Opción inválida");
        }
    }

    public void reagendarCita() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número de cédula (10 dígitos): ");
        String cedula = scanner.nextLine();

        boolean encontrado = false;

        for (int i = 0; i < totalCitas; i++) {
            if (citas[i].getCedula().equals(cedula)) {
                System.out.print("Ingrese la nueva fecha de la cita: ");
                String nuevaFecha = scanner.nextLine();
                citas[i].setFecha(nuevaFecha);
                System.out.println("Cita reagendada exitosamente");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Cita no encontrada");
        }
    }

    public void guardarCitasEnArchivo() {
        try (FileWriter fileWriter = new FileWriter("citas.txt")) {
            for (int i = 0; i < totalCitas; i++) {
                fileWriter.write("Nombre del paciente: " + citas[i].getNombre() + "\n");
                fileWriter.write("Número de cédula: " + citas[i].getCedula() + "\n");
                fileWriter.write("Fecha de la cita: " + citas[i].getFecha() + "\n");
                fileWriter.write("\n");
            }
            System.out.println("Citas guardadas en el archivo citas.txt");
        } catch (IOException e) {
            System.out.println("Error al guardar las citas en el archivo");
        }
    }
}