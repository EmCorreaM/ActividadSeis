import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.NumberFormatException;

class MostrarAmigos {

    public static void main(String datos[]) {

        try {
            String nombreNumeroCadena;
            String nombre;
            long numero;

            File archivo = new File("contactosAmigos.txt");

            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(archivo, "rw");

            while (raf.getFilePointer() < raf.length()) {
                nombreNumeroCadena = raf.readLine();
                String[] lineaDividida = nombreNumeroCadena.split("!");
                nombre = lineaDividida[0];
                numero = Long.parseLong(lineaDividida[1]);

                System.out.println("Nombre: " + nombre + "\n" + "Número: " + numero + "\n");
            }

            raf.close();
        } catch (IOException ioe) {
            System.out.println(ioe);
        } catch (NumberFormatException nef) {
            System.out.println(nef);
        }
    }
}
