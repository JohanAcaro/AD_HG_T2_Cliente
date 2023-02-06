package org.example;



import org.example.infrastructure.dto.IncidenciaDto;
import org.example.infrastructure.entities.Client;
import org.example.infrastructure.entities.TipoIncidencia;
import org.example.infrastructure.entities.TipoReparacion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
        var sc = new Scanner(System.in);
        var opcion = "";
        do {
            // Menú principal
            System.out.println("Bienvenido a la aplicación de WATERMELON");
            System.out.println("¿Qué tipo de usuario eres?");
            System.out.println("1. Cliente");
            System.out.println("2. Dirección");
            System.out.println("3. Técnico");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextLine();

            // Opciones del menú principal
            switch (opcion){
                case "1" ->
                    // Lógica para registrar un nuevo cliente
                        nuevoCliente();
                case "2" ->
                    // Lógica para direccion
                        direccion();
                case "3" ->
                    // Lógica para tecnico
                        tecnico();
                case "4" ->
                        System.out.println("Gracias por usar el sistema de atención al cliente de WATERMELON");
                default -> System.out.println("Opción inválida, por favor seleccione una opción válida");

            }

        }while (!opcion.equals("4"));
    }

    private static void tecnico() {
        var sc = new Scanner(System.in);
        String usuario= "tecnico";
        String contrasena= "1234";
        System.out.println("Login de Técnico");
        // Pedir usuario y contraseña
        System.out.println("Introduce tu nombre de usuario");
        String usuarioInt = sc.nextLine();
        System.out.println("Introduce tu contraseña");
        String contrasenaInt = sc.nextLine();
        // Comprobar usuario y contraseña
        if (usuarioInt.equals(usuario) && contrasenaInt.equals(contrasena)) {
            System.out.println("Bienvenido técnico de WATERMELON");
            // Pedir id de la llamada
            System.out.println("Dime el id de la llamada");
            String idLlamada = sc.nextLine();

            try {
                // Peticion Get a la API
                RestTemplate restTemplate = new RestTemplate();
                String url = "http://localhost:8080/info-cliente/" + idLlamada;
                // Realizar la petición GET
                String result = restTemplate.getForObject(url, String.class);
                // Mostrar el resultado
                System.out.println("Resultado de la petición GET: " + result);
            }
            catch (HttpClientErrorException e) {
                System.out.println("No se ha encontrado la llamada con id " + idLlamada);
            }



        } else {
            System.out.println("Usuario o contraseña incorrectos");
        }

    }

    private static void direccion() {
        var sc = new Scanner(System.in);
        String usuario= "direccion";
        String contrasena= "1234";
        System.out.println("Login de Dirección");
        // Pedir usuario y contraseña
        System.out.println("Introduce tu nombre de usuario");
        String usuarioInt = sc.nextLine();
        System.out.println("Introduce tu contraseña");
        String contrasenaInt = sc.nextLine();
        // Comprobar que el usuario y la contraseña son correctos
        if (usuarioInt.equals(usuario) && contrasenaInt.equals(contrasena)) {
            System.out.println("Bienvenido a la dirección de WATERMELON");

            try {
                // Peticion Get a la API
                RestTemplate restTemplate = new RestTemplate();
                String url = "http://localhost:8080/list-llamadas";
                // Realizar la petición GET
                String result = restTemplate.getForObject(url, String.class);
                // Mostrar el resultado
                System.out.println("Resultado de la petición GET: " + result);
                System.out.println("¿Quieres saber la información de una fecha en concreto? (S/N)");
                String opcion = sc.nextLine().toUpperCase();
                if (opcion.equals("S")) {
                    System.out.println("Introduce la fecha en formato yyyy-MM-dd");
                    String fecha = sc.nextLine();
                    url = "http://localhost:8080/list-llamadas/" + fecha;
                    // Realizar la petición GET
                    result = restTemplate.getForObject(url, String.class);
                    // Mostrar el resultado
                    System.out.println("Resultado de la petición GET: " + result);
                }
                else if (opcion.equals("N")) {
                    System.out.println("Gracias por usar el sistema de atención al cliente de WATERMELON");
                }
                else {
                    System.out.println("Opción inválida, por favor seleccione una opción válida");
                }
            } catch (HttpClientErrorException e) {
                System.out.println("No hay llamadas registradas");
            }
            catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto");
            }
            catch (Exception e) {
                System.out.println("Error desconocido");
            }

        } else {
            System.out.println("Usuario o contraseña incorrectos");
        }
    }

    private static void nuevoCliente(){
        var sc = new Scanner(System.in);
        System.out.println("Bievenido al sistema de atención al cliente de WATERMELON");

        // Pedir datos al usuario
        System.out.println("¿Cómo te llamas?");
        String nombre = sc.nextLine();
        System.out.println("¿Cuáles son tus apellidos?");
        String apellidos = sc.nextLine();
        System.out.println("¿Cuál es tu número de teléfono?");
        String telefono = sc.nextLine();
        System.out.println("¿Cuál es el modelo de tu teléfono?");
        String modelo = sc.nextLine();

        Client cliente = new Client(nombre, apellidos, telefono, modelo);

        // Convertir a string la fecha actual2
        LocalDate date = LocalDate.now();
        //cambio de formato de fecha
        date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String fecha = date.toString();

        // Solicitar motivo de la llamada
        System.out.println("¿Cuál es el motivo de tu llamada?");
        String motivo = sc.nextLine();
        System.out.println("¿Qué tipo de incidencia es?");
        System.out.println("¿Hardware o Software?");
        TipoIncidencia tipoIncidencia = TipoIncidencia.valueOf(sc.nextLine().toUpperCase());

        System.out.println("¿Tipo de reparación?");
        System.out.println("¿Física o Virtual?");
        TipoReparacion tipoReparacion = TipoReparacion.valueOf(sc.nextLine().toUpperCase());

        System.out.println("¿Hemos podido solucionar tu problema?");
        System.out.println("¿S o N?");
        String respuesta = sc.nextLine().toUpperCase();
        boolean solucionado;
        solucionado = respuesta.equals("S");

        try{
            // Peticion POST a la API
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/insert";
            // Establecer los datos del nuevo IncidenciaDto aquí
            IncidenciaDto incidenciaDto = new IncidenciaDto(cliente, fecha, motivo, tipoIncidencia, tipoReparacion, solucionado);
            // Hacer la petición POST
            ResponseEntity<String> result = restTemplate.postForEntity(url, incidenciaDto, String.class);
            // Mostrar el resultado
            System.out.println("Resultado de la petición POST: " + result.getBody());
        }
        catch (HttpClientErrorException e){
            System.out.println("Error al insertar la incidencia");
        }


    }

}
