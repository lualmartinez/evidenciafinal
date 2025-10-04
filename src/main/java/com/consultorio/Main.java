
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;




/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LMartinez
 */
public class Main {
    
    
    public static List<Doctor> listaDoctores = new ArrayList<>();
    static List<Paciente> listaPacientes = new ArrayList<>();
    static List<Cita> listaCitas = new ArrayList<>();
    
    public static void main(String[] args) {
        
        Scanner scanner =new Scanner(System.in);
        String usuario,contrasena;
        System.out.println("Bienvenidos al Consultorio nueva generacion");
        System.out.println("Introduce tus credenciales");
        System.out.println("Usuario:");
        usuario=scanner.nextLine();
        System.out.println("Contrasena:");
        contrasena=scanner.nextLine();
        boolean esUsuarioValido=validarCredenciales(usuario, contrasena);
        if(esUsuarioValido==true){
            cargarBaseDatos(); 
            cargarPacientes();
            cargarCitas();
            menu();
            
            
        }
    }
    
    static boolean validarCredenciales(String usuario, String contrasena){
    
        if(usuario.equals("luis")&& contrasena.equals("nicolas01"))
        {
            System.out.println("El usuario es correcto");
            return true;
        }
            System.out.println("Usuario incorrecto");
            return false;
        }
    
    static void altaMedico(){
           Scanner scanner = new Scanner(System.in);
           Doctor doctor = new Doctor();

           System.out.println("=== Alta de Medico ===");
           System.out.print("ID del doctor: ");
           doctor.id = scanner.nextInt();
           scanner.nextLine(); // limpiar buffer

           System.out.print("Nombre del doctor: ");
           doctor.nombre = scanner.nextLine();
           System.out.print("Especialidad: ");
           doctor.especialidad = scanner.nextLine();

    listaDoctores.add(doctor);
    System.out.println("Doctor registrado correctamente.");
    scanner.nextLine();    
    }
    
    static void verDoctores(){
    System.out.println("\n=== Lista de Doctores ===");
    if(listaDoctores.isEmpty()){
        System.out.println("No hay doctores registrados.");
    } else {
        for (Doctor doctor : listaDoctores) {
            System.out.println("ID: " + doctor.id);
            System.out.println("Nombre: " + doctor.nombre);
            System.out.println("Especialidad: " + doctor.especialidad);
            System.out.println("----------------------");
        }
    }
}
    
    static void guardarDoctores(){
        String json = new Gson().toJson(listaDoctores);
        
        try {
            FileWriter writer = new FileWriter("listaDoctores.json");
            writer.write(json);
            writer.close();    
        } catch (IOException e){
            System.out.println("Ocurrio un error: " + e.getMessage());
        }
        
    } 
    
    static void altaPaciente(){
        Scanner scanner = new Scanner(System.in);
        Paciente paciente = new Paciente();

        System.out.println("=== Alta de Paciente ===");
        System.out.print("ID del paciente: ");
        paciente.id = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        System.out.print("Nombre del paciente: ");
        paciente.nombre = scanner.nextLine();
        System.out.print("Edad: ");
        paciente.edad = scanner.nextLine();

        listaPacientes.add(paciente);
        System.out.println("Paciente registrado correctamente.");
    }

    static void verPacientes(){
    System.out.println("\n=== Lista de Pacientes ===");
    if(listaPacientes.isEmpty()){
        System.out.println("No hay pacientes registrados.");
    } else {
        for (Paciente paciente : listaPacientes) {
            System.out.println("ID: " + paciente.id);
            System.out.println("Nombre: " + paciente.nombre);
            System.out.println("Edad: " + paciente.edad);
            System.out.println("----------------------");
        }
    }
}
    static void guardarPacientes(){
        String json = new Gson().toJson(listaPacientes);
        try {
            FileWriter writer = new FileWriter("listaPacientes.json");
            writer.write(json);
            writer.close();
            System.out.println("Pacientes guardados correctamente en listaPacientes.json");
        } catch (IOException e){
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }
    
    static void altaCita(){
    Scanner scanner = new Scanner(System.in);
    Cita cita = new Cita();

    System.out.println("=== Crear nueva Cita ===");
    System.out.print("Numero de cita: ");
    cita.nCita = scanner.nextLine();

    System.out.print("Fecha: ");
    cita.fecha = scanner.nextLine();

    
    if (listaPacientes.isEmpty()) {
        System.out.println("No hay pacientes registrados.");
        return;
    }
    System.out.println("\nSelecciona un paciente:");
    for (int i = 0; i < listaPacientes.size(); i++) {
        System.out.println((i+1) + ". " + listaPacientes.get(i).nombre);
    }
    System.out.print("Opcion: ");
    int opPaciente = scanner.nextInt(); scanner.nextLine();
    cita.paciente = listaPacientes.get(opPaciente - 1);

    // Seleccionar doctor
    if (listaDoctores.isEmpty()) {
        System.out.println("No hay doctores registrados.");
        return;
    }
    System.out.println("\nSelecciona un doctor:");
    for (int i = 0; i < listaDoctores.size(); i++) {
        System.out.println((i+1) + ". " + listaDoctores.get(i).nombre + " (" + listaDoctores.get(i).especialidad + ")");
    }
    System.out.print("Opcion: ");
    int opDoctor = scanner.nextInt(); scanner.nextLine();
    cita.doctor = listaDoctores.get(opDoctor - 1);

    listaCitas.add(cita);
    System.out.println("Cita registrada con exito.");
}

static void verCitas(){
    System.out.println("\n=== Lista de Citas ===");
    if (listaCitas.isEmpty()) {
        System.out.println("No hay citas registradas.");
    } else {
        for (Cita cita : listaCitas) {
            System.out.println("Numero de cita: " + cita.nCita);
            System.out.println("Fecha: " + cita.fecha);
            System.out.println("Paciente: " + cita.paciente.nombre);
            System.out.println("Doctor: " + cita.doctor.nombre + " (" + cita.doctor.especialidad + ")");
            System.out.println("----------------------");
        }
    }
}

static void guardarCitas(){
    String json = new Gson().toJson(listaCitas);
    try (FileWriter writer = new FileWriter("listaCitas.json")) {
        writer.write(json);
        System.out.println("Citas guardadas correctamente en listaCitas.json");
    } catch (IOException e) {
        System.out.println("Error al guardar: " + e.getMessage());
    }
}
    
    static void menu(){
        Scanner scanner = new Scanner(System.in);
        Integer opcion = 0;
        while (opcion!=10){
        System.out.println("1. Dar de alta medico");
        System.out.println("2. Dar de alta paciente");
        System.out.println("3. Dar de alta cita");
        System.out.println("4. Ver Medicos");
        System.out.println("5. Ver Pacientes");
        System.out.println("6. Ver Citas");
        System.out.println("7. Guardar Doctores");
        System.out.println("8. Guardar Pacientes");
        System.out.println("9. Guardar Citas");
        System.out.println("10. Salir");
        System.out.println("Escribe una opcion: ");
        opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion){
            
            case 1:
                altaMedico();
                break;
            case 2:
                altaPaciente();
                break;
            case 3:
                altaCita();
                break;
            case 4:
                verDoctores();
                break;
            case 5:
                verPacientes();
                break;
            case 6:
                verCitas();
                break;
            case 7:
                guardarDoctores();
                break;
            case 8:
                guardarPacientes();
                break;
            case 9:
                guardarCitas();
                break;
            case 10:
                break;
            default:
                opcion = 10;
        }
       }
    }
    
    public static void cargarBaseDatos(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("listaDoctores.json"));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
                Type listType = new TypeToken<List<Doctor>>(){
                }.getType();
                Gson gson = new Gson();
                
                listaDoctores = gson.fromJson(linea, listType);
                
                }            
            }catch (IOException e){
                e.printStackTrace();
        }
    }
    
    public static void cargarPacientes(){
    try {
        BufferedReader br = new BufferedReader(new FileReader("listaPacientes.json"));
        String linea;
        while ((linea = br.readLine()) != null) {
            Type listType = new TypeToken<List<Paciente>>(){}.getType();
            Gson gson = new Gson();
            listaPacientes = gson.fromJson(linea, listType);
        }
        br.close();
        System.out.println("Pacientes cargados desde listaPacientes.json");
    } catch (IOException e){
        System.out.println("No se encontro listaPacientes.json, iniciando vacio.");
    }
}

    public static void cargarCitas(){
    try {
        BufferedReader br = new BufferedReader(new FileReader("listaCitas.json"));
        String linea;
        while ((linea = br.readLine()) != null) {
            Type listType = new TypeToken<List<Cita>>(){}.getType();
            Gson gson = new Gson();
            listaCitas = gson.fromJson(linea, listType);
        }
        br.close();
        System.out.println("Citas cargadas desde listaCitas.json");
    } catch (IOException e){
        System.out.println("No se encontro listaCitas.json, iniciando vacio.");
    }
}
    

}
