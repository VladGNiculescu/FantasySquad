package alexvlad.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FormationPanel extends JPanel {

    public FormationPanel(int def, int mid, int at) {

        super();
        setLayout(new GridLayout(5, 1, 20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setMinimumSize(new Dimension(300, 300));
        add(addLine(1, "Goalkeeper"));
        add(addLine(def, "Defender"));
        add(addLine(mid, "Midfielder"));
        add(addLine(at, "Striker"));
        add(addLine(4, "Sub"));
    }

    private JPanel addLine(int number, String name)
    {
        JPanel line = new JPanel();
        line.setLayout(new GridLayout(1, number, 20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        for(int i = 0; i < number; i++) {
            line.add(createPlayerPanel(name));
        }
        return line;
    }

    private JPanel createPlayerPanel(String name) {
        JPanel player = new JPanel();
        player.setLayout(new BorderLayout());
        JButton playerButton = new JButton("+");
        JTextField playerName = new JTextField(name);

        playerName.setHorizontalAlignment(JLabel.CENTER);

        player.add(playerButton,BorderLayout.CENTER);
        player.add(playerName,BorderLayout.SOUTH);



        return player;
    }

}
