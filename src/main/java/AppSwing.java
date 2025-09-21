import javax.swing.*;
import java.awt.*;
import java.io.File;

public class AppSwing {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JSON → CSV App");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JButton btnLoad = new JButton("Cargar JSON");
        btnLoad.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    String content = JsonReader.readJsonObject(file.getAbsolutePath()).toString(2);
                    textArea.setText(content);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error leyendo JSON: " + ex.getMessage());
                }
            }
        });

        JButton btnSave = new JButton("Exportar CSV");
        btnSave.addActionListener(e -> {
            try {
                String[][] data = {
                        {"Nombre", "Edad", "Ciudad"},
                        {"Juan", "25", "CDMX"},
                        {"Ana", "30", "Guadalajara"},
                        {"Luis", "28", "Monterrey"}
                };
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
