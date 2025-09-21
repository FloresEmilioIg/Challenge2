import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Clase para leer archivos JSON usando la librería org.json.
 */
public class JsonReader {

    /**
     * Lee un archivo JSON y devuelve su contenido como JSONObject.
     *
     * @param filePath Ruta del archivo JSON.
     * @return JSONObject con los datos del archivo.
     * @throws IOException si ocurre un error al leer el archivo.
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
     * Lee un archivo JSON y devuelve su contenido como JSONArray.
     *
     * @param filePath Ruta del archivo JSON.
     * @return JSONArray con los datos del archivo.
     * @throws IOException si ocurre un error al leer el archivo.
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
