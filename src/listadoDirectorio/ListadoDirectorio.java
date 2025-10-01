package listadoDirectorio;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListadoDirectorio {
    public static void main(String[] args) {
        // inicializa la variable ruta con un punto (.), que representa el directorio actual
        String ruta = ".";
        if (args.length > 0) ruta = args[0];

        // Creación del objeto File
        File fich = new File(ruta);

        if (!-+
fich.exists()) { // Verificación de la existencia
            System.out.println("No existe el fichero o directorio (" + ruta + ").");
        } else {
            if (fich.isFile()) { // Comprobamos si es un fichero
                mostrarInfo(fich);
            } else {
                System.out.println(ruta + " es un directorio. Contenidos: ");
                // Creamos un array con los objetos File
                File[] ficheros = fich.listFiles();
                if (ficheros != null) {
                    for (File f : ficheros) {
                        mostrarInfo(f);
                    }
                }
            }
        }
    }

    // Metodo auxiliar para mostrar la información de cada fichero/directorio
    private static void mostrarInfo(File f) {
        // Prefijo para diferenciar entre fichero o directorio
        String tipo = f.isDirectory() ? "/" : f.isFile() ? "-" : "?";

        // Permisos en formato rwx
        String permisos = (f.canRead() ? "r" : "-") +
                (f.canWrite() ? "w" : "-") +
                (f.canExecute() ? "x" : "-");

        // Tamaño (solo si es fichero)
        String tam = f.isFile() ? (f.length() + " bytes") : "-";

        // Fecha de última modificación
        String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                .format(new Date(f.lastModified()));

        // Imprimir la info
        System.out.printf("(%s %s) %-20s Tamaño: %-10s Última mod: %s%n",
                tipo, permisos, f.getName(), tam, fecha);
    }
}

