package com.nerugdev.literaturaApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.nerugdev.literaturaApp.controller.AutorController;
import com.nerugdev.literaturaApp.controller.LibroController;
import java.util.Scanner;

@Component
public class Menu implements CommandLineRunner {

    @Autowired
    private AutorController autorController;

    @Autowired
    private LibroController libroController;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el título del libro: ");
                    String titulo = scanner.nextLine();
                    libroController.buscarLibroPorTitulo(titulo);
                    break;
                case 2:
                    libroController.listarLibrosRegistrados();
                    break;
                case 3:
                    autorController.listarAutoresRegistrados();
                    break;
                case 4:
                    System.out.print("Ingrese el año: ");
                    int ano = scanner.nextInt();
                    autorController.listarAutoresVivosEnAno(ano);
                    break;
                case 5:
                    System.out.println("Seleccione un idioma:");
                    System.out.println("es - español");
                    System.out.println("en - inglés");
                    System.out.println("fr - francés");
                    System.out.println("pt - portugués");
                    String idioma = scanner.next();
                    libroController.listarLibrosPorIdioma(idioma);
                    break;
                case 0:
                    System.out.println("Saliendo de la aplicación...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 0);
        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println("*********************** menu literaturaApp ********************");
        System.out.println("1 - buscar libro por titulo");
        System.out.println("2 - listar libros registrados");
        System.out.println("3 - listar autores registrados");
        System.out.println("4 - listar autores vivos en un determinado año");
        System.out.println("5 - listar libro por idioma");
        System.out.println("0 - salir");
        System.out.println("*****************************************************************");
        System.out.print("Seleccione una opción: ");
    }
}