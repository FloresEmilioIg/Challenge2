import org.json.JSONArray;
import org.json.JSONObject;

public class main {
    public static void main(String[] args) {
        // Leer un JSON Object (si existe)
        try {
            JSONObject jsonObject = JsonReader.readJsonObject("data.json");
            System.out.println("Contenido JSON Object: " + jsonObject.toString(2));
        } catch (Exception e) {
            System.out.println("No se encontró data.json, continuando...");
        }

        // Leer un JSON Array (si existe)
        try {
            JSONArray jsonArray = JsonReader.readJsonArray("dataArray.json");
            System.out.println("Contenido JSON Array: " + jsonArray.toString(2));
        } catch (Exception e) {
            System.out.println("No se encontró dataArray.json, continuando...");
        }

        // Escribir datos a CSV (puedes adaptarlo a lo que leas del JSON)
        try {
            String[][] data = {
                    {"Nombre", "Edad", "Ciudad"},
                    {"Juan", "25", "CDMX"},
                    {"Ana", "30", "Guadalajara"},
                    {"Luis", "28", "Monterrey"}
            };
            CSVWrite.writeCsv("output.csv", data);
            System.out.println("Archivo CSV creado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al generar el archivo CSV.");
            e.printStackTrace();
        }
    }
}

