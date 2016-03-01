package alexvlad.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FormationPanel extends JPanel {

    private final int totalGoalkeepers = 2;
    private final int totalDef = 5;
    private final int totalMidfielders = 5;
    private final int totalStrikers = 3;

    public FormationPanel(int def, int mid, int at) {

        super();

        setLayout(new GridLayout(5, 1, 20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setMinimumSize(new Dimension(300, 300));

        add(addLine(1, "Goalkeeper"));
        add(addLine(def, "Defender"));
        add(addLine(mid, "Midfielder"));
        add(addLine(at, "Striker"));

        add(addSubLine(totalGoalkeepers - 1, totalDef - def, totalMidfielders - mid, totalStrikers - at));
    }

    private JPanel addSubLine(int goalkeepers, int def, int mid, int at) {
        JPanel line = new JPanel();
        line.setLayout(new GridLayout(1, 4, 20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (int i = 0; i < goalkeepers; ++i) {
            line.add(createPlayerPanel("Goalkeeper"));
        }

        for (int i = 0; i < def; ++i) {
            line.add(createPlayerPanel("Defender"));
        }

        for (int i = 0; i < mid; ++i) {
            line.add(createPlayerPanel("Midfielder"));
        }

        for (int i = 0; i < at; ++i) {
            line.add(createPlayerPanel("Striker"));
        }

        return line;
    }

    private JPanel addLine(int number, String name) {
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
        JLabel playerName = new JLabel(name);

        playerName.setHorizontalAlignment(JLabel.CENTER);

        player.add(playerButton,BorderLayout.CENTER);
        player.add(playerName,BorderLayout.SOUTH);



        return player;
    }

}
