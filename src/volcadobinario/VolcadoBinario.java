// Volcado hexadecimal de un fichero con FileInputStream
package volcadobinario;
/*
Ahora un ejemplo con flujos binarios. El siguiente programa hace un volcado binario de un fichero indicado desde línea de comandos.
Los contenidos del fichero se leen en bloques de 32 bytes, y el contenido de cada bloque se escribe en una línea de texto.
Los bytes se escriben en hexadecimal (base 16) y, por tanto, cada byte se escribe utilizando dos caracteres.
El programa muestra como máximo los primeros 2 kilobytes (MAX_BYTES=2048).
Por supuesto, este programa se puede utilizar tanto con ficheros binarios como con ficheros de texto.
Hacer notar que esta clase permite hacer el volcado binario de un InputStream, y un FileInputStream es un caso particular.
Siempre que sea posible, debemos hacer que las clases que desarrollemos funcionen con streams en general, y no solo con ficheros en particular.
 */

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Clase que permite realizar un volcado binario (en formato hexadecimal) de un fichero.
 */
public class VolcadoBinario {
    // Define el número de bytes a leer y mostrar por fila en el volcado.
    static int TAM_FILA = 32;
    // Define el número máximo de bytes a volcar.
    static int MAX_BYTES = 2048;
    // Stream de entrada de datos.
    InputStream is = null;
    // Stream de salida (puede ser System.out o un fichero).
    PrintStream ps = null;

    /**
     * Constructor de la clase.
     * @param is El InputStream desde el que se leerán los datos.
     * @param ps El PrintStream donde se escribirá el volcado.
     */
    public VolcadoBinario(InputStream is, PrintStream ps) {
        this.is = is;
        this.ps = ps;
    }

    /**
     * Realiza el volcado de los bytes del fichero.
     * Lee el fichero en bloques, convierte los bytes a hexadecimal y los imprime en el PrintStream indicado.
     * @throws IOException Si ocurre un error de entrada/salida durante la lectura del fichero.
     */
    public void volcar() throws IOException {
        byte buffer[] = new byte[TAM_FILA];
        int bytesLeidos;
        int offset = 0;

        do {
            bytesLeidos = is.read(buffer);
            if (bytesLeidos == -1) break; // fin de fichero

            ps.format("[%5d] ", offset);
            for (int i = 0; i < bytesLeidos; i++) {
                ps.format("%02x ", buffer[i]);
            }
            offset += bytesLeidos;
            ps.println();

        } while (bytesLeidos == TAM_FILA && offset < MAX_BYTES);
    }

    /**
     * Método principal (main) para la ejecución del programa.
     * @param args Argumentos de la línea de comandos, se espera que el primer argumento sea la ruta del fichero.
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("No se ha indicado ningún fichero a volcar");
            return;
        }

        // Nombre del fichero de entrada (el que vamos a volcar)
        String nomFich = "src/volcadobinario/" + args[0];
        // Fichero donde se guardará el resultado del volcado
        String salida = "src/volcadobinario/Volcado.txt";

        try (FileInputStream fis = new FileInputStream(nomFich);
             PrintStream ps = new PrintStream(salida)) {

            VolcadoBinario vb = new VolcadoBinario(fis, ps);
            vb.volcar();

            System.out.println("✅ Volcado realizado correctamente en: " + salida);

        } catch (FileNotFoundException e) {
            System.err.println("ERROR: no existe el fichero " + nomFich);
        } catch (IOException e) {
            System.err.println("ERROR de E/S: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
