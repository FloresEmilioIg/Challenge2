import javax.swing.*;
import java.awt.*;
import java.io.File;

import org.json.JSONArray;
import org.json.JSONObject;

public class AppSwing {
    private static File loadedFile; // para recordar qué JSON se cargó

    public static void main(String[] args) {
        JFrame frame = new JFrame("JSON → CSV App");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Botón para cargar JSON
        JButton btnLoad = new JButton("Cargar JSON");
        btnLoad.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    // Intentar primero como JSONObject
                    try {
                        JSONObject obj = JsonReader.readJsonObject(file.getAbsolutePath());
                        textArea.setText(obj.toString(2));
                        loadedFile = file;
                    } catch (Exception exObj) {
                        // Si no es objeto, intentar como JSONArray
                        JSONArray arr = JsonReader.readJsonArray(file.getAbsolutePath());
                        textArea.setText(arr.toString(2));
                        loadedFile = file;
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error leyendo JSON: " + ex.getMessage());
                }
            }
        });

        // Botón para exportar CSV
        JButton btnSave = new JButton("Exportar CSV");
        btnSave.addActionListener(e -> {
            if (loadedFile == null) {
                JOptionPane.showMessageDialog(frame, "Primero carga un archivo JSON.");
                return;
            }
            try {
                String[][] data;

                // Intentar como objeto
                try {
                    JSONObject obj = JsonReader.readJsonObject(loadedFile.getAbsolutePath());
                    data = new String[][]{
                            {"Nombre", "Edad", "Ciudad"},
                            {
                                    obj.getString("nombre"),
                                    String.valueOf(obj.getInt("edad")),
                                    obj.getString("ciudad")
                            }
                    };
                } catch (Exception exObj) {
                    // Si no es objeto, intentar como array
                    JSONArray arr = JsonReader.readJsonArray(loadedFile.getAbsolutePath());
                    data = new String[arr.length() + 1][3];
                    data[0][0] = "Nombre";
                    data[0][1] = "Edad";
                    data[0][2] = "Ciudad";

                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject o = arr.getJSONObject(i);
                        data[i + 1][0] = o.getString("nombre");
                        data[i + 1][1] = String.valueOf(o.getInt("edad"));
                        data[i + 1][2] = o.getString("ciudad");
                    }
                }

                CSVWrite.writeCsv("output.csv", data);
                JOptionPane.showMessageDialog(frame, "CSV exportado como output.csv");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error exportando CSV: " + ex.getMessage());
            }
        });

        JPanel panel = new JPanel();
        panel.add(btnLoad);
        panel.add(btnSave);
        frame.add(panel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}