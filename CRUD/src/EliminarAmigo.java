import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.NumberFormatException;

class EliminarAmigo {

    public static void main(String datos[]) {

        try {
            String nombreEliminar = datos[0];

            String nombreNumeroCadena;
            String nombre;
            long numero;
            int indice;

            File archivo = new File("contactosAmigos.txt");

            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(archivo, "rw");
            boolean encontrado = false;

            while (raf.getFilePointer() < raf.length()) {
                nombreNumeroCadena = raf.readLine();
                String[] lineaDividida = nombreNumeroCadena.split("!");
                nombre = lineaDividida[0];
                numero = Long.parseLong(lineaDividida[1]);

                if (nombre.equals(nombreEliminar)) {
                    encontrado = true;
                    break;
                }
            }

            if (encontrado) {
                File archivoTemporal = new File("temp.txt");
                RandomAccessFile tmpraf = new RandomAccessFile(archivoTemporal, "rw");

                raf.seek(0);

                while (raf.getFilePointer() < raf.length()) {
                    nombreNumeroCadena = raf.readLine();
                    indice = nombreNumeroCadena.indexOf('!');
                    nombre = nombreNumeroCadena.substring(0, indice);

                    if (nombre.equals(nombreEliminar)) {
                        continue;
                    }

                    tmpraf.writeBytes(nombreNumeroCadena);
                    tmpraf.writeBytes(System.lineSeparator());
                }

                raf.seek(0);
                tmpraf.seek(0);

                while (tmpraf.getFilePointer() < tmpraf.length()) {
                    raf.writeBytes(tmpraf.readLine());
                    raf.writeBytes(System.lineSeparator());
                }

                raf.setLength(tmpraf.length());
                tmpraf.close();
                raf.close();
                archivoTemporal.delete();

                System.out.println("Amigo eliminado.");
            } else {
                raf.close();
                System.out.println("El nombre ingresado no existe.");
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
}