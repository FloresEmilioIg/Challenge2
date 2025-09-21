import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase para escribir datos en archivos CSV usando OpenCSV.
 */
public class CSVWrite {

    /**
     * Escribe datos en un archivo CSV.
     *
     * @param filePath Ruta del archivo CSV.
     * @param data     Matriz de Strings que representa filas y columnas.
     * @throws IOException si ocurre un error al escribir el archivo.
     */
    public static void writeCsv(String filePath, String[][] data) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            for (String[] row : data) {
                writer.writeNext(row);
            }
        } catch (IOException e) {
            throw new IOException("Error al escribir en el archivo CSV: " + filePath, e);
        }
    }
}

