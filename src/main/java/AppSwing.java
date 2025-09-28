import javax.swing.*;
import java.awt.*;
import java.io.File;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Swing-based desktop app to convert JSON files into CSV.
 */
public class AppSwing {
    private static File loadedFile; // remembers the loaded JSON file

    public static void main(String[] args) {
        JFrame frame = new JFrame("JSON → CSV App");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // === Botón: Cargar JSON ===
        JButton btnLoad = new JButton("Load JSON");
        btnLoad.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    // Try reading as object
                    try {
                        JSONObject obj = JsonReader.readJsonObject(file.getAbsolutePath());
                        textArea.setText(obj.toString(2));
                        loadedFile = file;
                    } catch (Exception exObj) {
                        // Try as array
                        JSONArray arr = JsonReader.readJsonArray(file.getAbsolutePath());
                        textArea.setText(arr.toString(2));
                        loadedFile = file;
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error reading JSON: " + ex.getMessage());
                }
            }
        });

        // === Botón: Exportar CSV ===
        JButton btnSave = new JButton("Export CSV");
        btnSave.addActionListener(e -> {
            if (loadedFile == null) {
                JOptionPane.showMessageDialog(frame, "Please load a JSON file first.");
                return;
            }
            try {
                String[][] data;

                // Try mapping as object
                try {
                    JSONObject obj = JsonReader.readJsonObject(loadedFile.getAbsolutePath());
                    data = JsonToCSVMapper.mapObject(obj);
                } catch (Exception exObj) {
                    // Else as array
                    JSONArray arr = JsonReader.readJsonArray(loadedFile.getAbsolutePath());
                    data = JsonToCSVMapper.mapArray(arr);
                }

                // Use default delimiter (comma)
                CSVWrite.writeCsv("output.csv", data, ',');
                JOptionPane.showMessageDialog(frame, "CSV exported as output.csv");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error exporting CSV: " + ex.getMessage());
            }
        });

        // Panel con botones
        JPanel panel = new JPanel();
        panel.add(btnLoad);
        panel.add(btnSave);
        frame.add(panel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}