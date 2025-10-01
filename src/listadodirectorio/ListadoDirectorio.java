package listadodirectorio;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListadoDirectorio {
    public static void main(String[] args) {
        // Inicializamos la variable ruta con un punto (directorio actual por defecto)
        String ruta = ".";

        // Comprobamos si se ha pasado un argumento al ejecutar el programa
        if (args.length > 0) {
            ruta = args[0];
        }

        // Creación del objeto File
        File fich = new File(ruta);

        if (!fich.exists()) {
            System.out.println("No existe el fichero o directorio (" + ruta + ").");
        } else {
            if (fich.isFile()) {
                System.out.println(ruta + " es un fichero.");
            } else {
                System.out.println(ruta + " es un directorio. Contenidos: ");
                File[] ficheros = fich.listFiles();

                if (ficheros != null) {
                    for (File f : ficheros) {
                        // Identificador si es directorio o fichero
                        String tipo = f.isDirectory() ? "D" : "F";

                        // Permisos estilo Linux
                        String permisos = (f.canRead() ? "r" : "-") +
                                (f.canWrite() ? "w" : "-") +
                                (f.canExecute() ? "x" : "-");

                        // Tamaño (solo para ficheros)
                        String tamanyo = f.isFile() ? f.length() + " bytes" : "-";

                        // Fecha de última modificación
                        String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                                .format(new Date(f.lastModified()));

                        // Mostramos toda la información
                        System.out.printf("%s %-20s [Permisos: %s] [Tamaño: %s] [Últ. modif: %s]%n",
                                tipo, f.getName(), permisos, tamanyo, fecha);
                    }
                }
            }
        }
    }
}
