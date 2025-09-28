import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Class to transform JSON data into a CSV-compatible structure.
 */
public class JsonReader {

    /**
     * Reads a JSON file and returns it´s content as JSONObject.
     *
     * @param filePath Route of the JSON file.
     * @return JSONObject with the file data.
     * @throws IOException if an error happens when reading the file.
     */
    public static JSONObject readJsonObject(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            JSONTokener tokener = new JSONTokener(fis);
            return new JSONObject(tokener);
        } catch (FileNotFoundException e) {
            throw new IOException("Archivo no encontrado: " + filePath, e);
        } catch (Exception e) {
            throw new IOException("Error al parsear el archivo JSON.", e);
        }
    }

    /**
     * Reads a JSON file and returns it´s content as JSONArray.
     *
     * @param filePath Route of the JSON file.
     * @return JSONObject with the file data.
     * @throws IOException if an error happens when reading the file.
     */
    public static JSONArray readJsonArray(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            JSONTokener tokener = new JSONTokener(fis);
            return new JSONArray(tokener);
        } catch (FileNotFoundException e) {
            throw new IOException("Archivo no encontrado: " + filePath, e);
        } catch (Exception e) {
            throw new IOException("Error al parsear el archivo JSON.", e);
        }
    }
}
