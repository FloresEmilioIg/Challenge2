import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Main class to run JSON → CSV conversion.
 *
 * Usage:
 *   java Main input.json output.csv ;
 *   (last argument is optional delimiter, default = ',')
 */
public class main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Main <input.json> <output.csv> [delimiter]");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];
        char delimiter = args.length >= 3 ? args[2].charAt(0) : ',';

        try {
            String[][] data;

            try {
                // Try as object
                JSONObject obj = JsonReader.readJsonObject(inputFile);
                data = JsonToCSVMapper.mapObject(obj);
            } catch (Exception e) {
                // Else try as array
                JSONArray arr = JsonReader.readJsonArray(inputFile);
                data = JsonToCSVMapper.mapArray(arr);
            }

            CSVWrite.writeCsv(outputFile, data, delimiter);
            System.out.println("CSV file successfully created: " + outputFile);

        } catch (Exception e) {
            System.out.println("Error during conversion: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
