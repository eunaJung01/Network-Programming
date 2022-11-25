package Lecture.week11;

import javax.swing.*;
import java.awt.*;

public class Gui2SwingBasic extends JFrame {

    public Gui2SwingBasic() {
        super("Hello World");
        getContentPane().setLayout(new FlowLayout());
        JLabel label = new JLabel("Welcome to Swing");
        getContentPane().add(label);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(200, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        Gui2SwingBasic app = new Gui2SwingBasic();
    }

}
