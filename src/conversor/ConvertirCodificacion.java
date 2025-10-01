package conversor;

import java.io.*;

public class ConvertirCodificacion {
    public static void main(String[] args) {
        // Fichero de entrada en UTF-8
        String ficheroEntrada = "src/conversor/TextoUTF8.txt";
        // Ficheros de salida
        String ficheroISO = "src/conversor/Texto_ISO8859_1.txt";
        String ficheroUTF16 = "src/conversor/Texto_UTF16.txt";

        try (
                // Lector en UTF-8
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(new FileInputStream(ficheroEntrada), "UTF-8"))
        ) {
            // Escritura en ISO-8859-1
            try (BufferedWriter bwISO = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(ficheroISO), "ISO-8859-1"));
                 BufferedWriter bwUTF16 = new BufferedWriter(
                         new OutputStreamWriter(new FileOutputStream(ficheroUTF16), "UTF-16"))
            ) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    bwISO.write(linea);
                    bwISO.newLine();

                    bwUTF16.write(linea);
                    bwUTF16.newLine();
                }
            }

            System.out.println("Conversión realizada con éxito:");
            System.out.println(" -> " + ficheroISO + " (ISO-8859-1)");
            System.out.println(" -> " + ficheroUTF16 + " (UTF-16)");

        } catch (IOException e) {
            System.err.println("Error al convertir el fichero: " + e.getMessage());
        }
    }
}

