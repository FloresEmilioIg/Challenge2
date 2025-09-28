import com.opencsv.CSVWriter;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Utility class for writing data to CSV files using OpenCSV.
 */
public class CSVWrite {

    /**
     * Writes data to a CSV file.
     *
     * @param filePath  Path of the CSV file.
     * @param data      2D String array representing rows and columns.
     * @param delimiter Character used as column delimiter (e.g., ',' or ';').
     * @throws IOException If an error occurs while writing the file.
     */
    public static void writeCsv(String filePath, String[][] data, char delimiter) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath), delimiter,
                CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END)) {
            for (String[] row : data) {
                writer.writeNext(row);
            }
        } catch (IOException e) {
            throw new IOException("Error writing CSV file: " + filePath, e);
        }
    }
}

