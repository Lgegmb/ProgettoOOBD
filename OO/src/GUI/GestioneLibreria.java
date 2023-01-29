package GUI;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class GestioneLibreria extends JFrame{
    private JPanel panel1;
    private JTable table1;

    public GestioneLibreria()  {
        JFrame frame = new JFrame("GestioneLibreria");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);

        //add data to the table
        String[] columnNames = {"First Name",
                                "Last Name",
                                "Sport",
                                "# of Years",
                                "Vegetarian"};

        Object[][] data = {
            {"Kathy", "Smith",
             "Snowboarding", 5, false},
            {"John", "Doe",
             "Rowing", 3, true},
            {"Sue", "Black",
             "Knitting", 2, false},
            {"Jane", "White",
             "Speed reading", 20, true},
            {"Joe", "Brown",
             "Pool", 10, false}
        };

        table1 = new JTable(data, columnNames);
        JTableHeader header = table1.getTableHeader();
        header.setBackground(Color.yellow);

        table1.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table1.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table1);

        //Add the scroll pane to this panel.
        panel1.add(scrollPane);




    }

    public static void main(String[] args) {
        new GestioneLibreria();
    }
}
