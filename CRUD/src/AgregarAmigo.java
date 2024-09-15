import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.NumberFormatException;

class AgregarAmigo {

    public static void main(String datos[]) {

        try {
            String nuevoNombre = datos[0];
            long nuevoNumero = Long.parseLong(datos[1]);

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

                if (nombre.equals(nuevoNombre) || numero == nuevoNumero) {
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                nombreNumeroCadena = nuevoNombre + "!" + String.valueOf(nuevoNumero);
                raf.writeBytes(nombreNumeroCadena);
                raf.writeBytes(System.lineSeparator());
                System.out.println("Amigo agregado.");
                raf.close();
            } else {
                raf.close();
                System.out.println("El nombre ingresado ya existe.");
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        } catch (NumberFormatException nef) {
            System.out.println(nef);
        }
    }
}
