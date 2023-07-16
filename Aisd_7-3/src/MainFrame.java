import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.LinkedBlockingQueue;

public class MainFrame extends JFrame{
    private JPanel panelMain;
    private JPanel panel1;
    private JPanel panel2;
    private JButton buttonInputAndOtvet;
    private JTable matrix;
    private JScrollPane scroll1;
    private JTextArea otvet;


    public MainFrame() throws IOException {
        this.setTitle("Основная программа");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();

        JFileChooser fileChooserOpen;

        fileChooserOpen = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);


        JFileChooser finalFileChooserOpen = fileChooserOpen;
        buttonInputAndOtvet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (finalFileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int[][] arr = ArrayUtils.readIntArray2FromFile(finalFileChooserOpen.getSelectedFile().getPath());
                        JTableUtils.writeArrayToJTable(matrix, arr);
                        boolean isConnected = MainLogic.checkConnectivity(arr);
                        if (isConnected) {
                            otvet.setText("Граф является связанным.");
                        } else {
                            otvet.setText("Граф не является связанным.");
                        }
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
    }
}
