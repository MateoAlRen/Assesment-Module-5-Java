package view;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class CSVtoDBUploader extends JFrame {

    private JButton btnLoadUsers;
    private JLabel lblStatus;
    private JProgressBar progressBar;

    private static final String URL = "jdbc:mysql://localhost:3306/LibraryNova";
    private static final String USER = "root";
    private static final String PASSWORD = "Qwe.123*";

    public CSVtoDBUploader() {
        setTitle("Upload CSV to Database");
        setSize(500, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel topPanel = new JPanel();
        btnLoadUsers = new JButton("Select Users CSV");
        lblStatus = new JLabel("Waiting for file...");
        topPanel.add(btnLoadUsers);
        topPanel.add(lblStatus);
        add(topPanel, BorderLayout.NORTH);

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        add(progressBar, BorderLayout.SOUTH);

        btnLoadUsers.addActionListener(e -> loadUsersCSV());
    }

    private void loadUsersCSV() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) return;
        File file = fileChooser.getSelectedFile();
        lblStatus.setText("File: " + file.getName());

        if (!file.getName().toLowerCase().endsWith(".csv")) {
            JOptionPane.showMessageDialog(this, "Please select a valid .csv file");
            return;
        }

        btnLoadUsers.setEnabled(false);
        progressBar.setIndeterminate(true);

        SwingWorker<Void, Integer> worker = new SwingWorker<>() {

            @Override
            protected Void doInBackground() throws Exception {
                try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                     BufferedReader br = new BufferedReader(new FileReader(file))) {

                    conn.setAutoCommit(false);

                    String header = br.readLine();
                    if (header == null) throw new IOException("The file is empty.");

                    String line;
                    int count = 0;

                    PreparedStatement ps = conn.prepareStatement(
                            "INSERT INTO library_user (user_name, user_email, user_password, staff_member) VALUES (?, ?, ?, ?)"
                    );

                    while ((line = br.readLine()) != null) {
                        List<String> values = parseCSVLine(line);
                        if (values.size() < 4) continue;

                        ps.setString(1, values.get(0));
                        ps.setString(2, values.get(1));
                        ps.setString(3, values.get(2));
                        ps.setBoolean(4, Boolean.parseBoolean(values.get(3)));
                        ps.addBatch();

                        count++;
                        if (count % 500 == 0) {
                            ps.executeBatch();
                            publish(count);
                        }
                    }

                    ps.executeBatch();
                    conn.commit();
                }
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
                int last = chunks.get(chunks.size() - 1);
                lblStatus.setText("Inserted " + last + " rows...");
            }

            @Override
            protected void done() {
                progressBar.setIndeterminate(false);
                btnLoadUsers.setEnabled(true);
                lblStatus.setText("Upload complete ✅");
                JOptionPane.showMessageDialog(CSVtoDBUploader.this, "User data successfully inserted.");
            }
        };

        worker.execute();
    }

    private List<String> parseCSVLine(String line) {
        List<String> result = new ArrayList<>();
        if (line == null) return result;

        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    current.append('"');
                    i++;
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (c == ',' && !inQuotes) {
                result.add(current.toString());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }
        result.add(current.toString());
        return result;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CSVtoDBUploader().setVisible(true));
    }
}
