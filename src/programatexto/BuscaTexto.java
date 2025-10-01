package programatexto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BuscaTexto {
    public static void main(String[] args) {
        // Ruta relativa desde la raíz del proyecto
        String nombreFichero = "src/programatexto/Texto.txt";

        // Texto a buscar
        if (args.length < 1) {
            System.out.println("Uso: java programatexto.BuscaTexto <texto>");
            return;
        }

        String textoBuscar = args[0];

        try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
            String linea;
            int numLinea = 1;

            while ((linea = br.readLine()) != null) {
                int index = linea.indexOf(textoBuscar);
                while (index >= 0) {
                    System.out.printf("Encontrado en línea %d, columna %d%n", numLinea, index + 1);
                    index = linea.indexOf(textoBuscar, index + 1);
                }
                numLinea++;
            }

        } catch (IOException e) {
            System.err.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}

