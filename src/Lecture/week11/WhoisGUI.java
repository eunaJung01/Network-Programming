package Lecture.week11;

import javax.swing.*;
import java.awt.*;

public class WhoisGUI extends JFrame {

    private JTextField searchString = new JTextField(30);
    private JTextArea names = new JTextArea(15, 80);
    private JButton findButton = new JButton("Find");
    private ButtonGroup searchIn = new ButtonGroup();
    private ButtonGroup searchFor = new ButtonGroup();
    private JCheckBox exactMatch = new JCheckBox("Exact Match", true);
    private JTextField chosenServer = new JTextField();
    private Whois server;

    public WhoisGUI(Whois whois) {
        super("Whois");
        this.server = whois;
        Container pane = this.getContentPane();

        Font f = new Font("Monospaced", Font.PLAIN, 12);
        names.setFont(f);
        names.setEditable(false);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 1, 10, 10));
        JScrollPane jsp = new JScrollPane(names);
        centerPanel.add(jsp);
        pane.add("Center", centerPanel);

        // 따라치기 포기요
    }

}
