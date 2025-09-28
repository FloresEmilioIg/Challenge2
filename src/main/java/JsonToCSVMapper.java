import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Utility class to transform JSON data into a CSV-compatible structure.
 */
public class JsonToCSVMapper {
    /**
     * Converts a JSONObject into a CSV 2D array.
     *
     * @param jsonObject JSON object containing the data.
     * @return 2D String array for CSV export.
     */
    public static String[][] mapObject(JSONObject jsonObject) {
        String[][] data = {
                {"Nombre", "Edad", "Ciudad"},
                {
                        jsonObject.optString("nombre", ""),
                        String.valueOf(jsonObject.optInt("edad", 0)),
                        jsonObject.optString("ciudad", "")
                }
        };
        return data;
    }

    /**
     * Converts a JSONArray into a CSV 2D array.
     *
     * @param jsonArray JSON array containing multiple objects.
     * @return 2D String array for CSV export.
     */
    public static String[][] mapArray(JSONArray jsonArray) {
        String[][] data = new String[jsonArray.length() + 1][3];
        data[0][0] = "Nombre";
        data[0][1] = "Edad";
        data[0][2] = "Ciudad";

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            data[i + 1][0] = obj.optString("nombre", "");
            data[i + 1][1] = String.valueOf(obj.optInt("edad", 0));
            data[i + 1][2] = obj.optString("ciudad", "");
        }
        return data;
    }
}
