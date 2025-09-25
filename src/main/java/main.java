import org.json.JSONArray;
import org.json.JSONObject;

public class main {
    public static void main(String[] args) {
        boolean exported = false;

        // Intentar con data.json (JSONObject)
        try {
            JSONObject jsonObject = JsonReader.readJsonObject("data.json");
            System.out.println("Contenido JSON Object: " + jsonObject.toString(2));

            // Convertir JSONObject a matriz CSV
            String[][] data = {
                    {"Nombre", "Edad", "Ciudad"},
                    {
                            jsonObject.getString("nombre"),
                            String.valueOf(jsonObject.getInt("edad")),
                            jsonObject.getString("ciudad")
                    }
            };

            CSVWrite.writeCsv("output.csv", data);
            System.out.println("Archivo CSV creado con éxito desde data.json");
            exported = true;

        } catch (Exception e) {
            System.out.println("No se encontró data.json o no es válido, continuando...");
        }

        // Intentar con dataArray.json (JSONArray)
        try {
            JSONArray jsonArray = JsonReader.readJsonArray("dataArray.json");
            System.out.println("Contenido JSON Array: " + jsonArray.toString(2));

            // Crear matriz con encabezados + filas
            String[][] data = new String[jsonArray.length() + 1][3];
            data[0][0] = "Nombre";
            data[0][1] = "Edad";
            data[0][2] = "Ciudad";

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                data[i + 1][0] = obj.getString("nombre");
                data[i + 1][1] = String.valueOf(obj.getInt("edad"));
                data[i + 1][2] = obj.getString("ciudad");
            }

            CSVWrite.writeCsv("output.csv", data);
            System.out.println("Archivo CSV creado con éxito desde dataArray.json");
            exported = true;

        } catch (Exception e) {
            System.out.println("No se encontró dataArray.json o no es válido, continuando...");
        }

        // Si no se pudo exportar nada
        if (!exported) {
            System.out.println("No se pudo generar el archivo CSV. Verifica tus archivos JSON.");
        }
    }
}