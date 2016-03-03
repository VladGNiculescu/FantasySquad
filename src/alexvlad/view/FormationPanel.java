package alexvlad.view;
import alexvlad.model.Player;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
public class FormationPanel extends JPanel {
    private final int totalGoalkeepers = 2;
    private final int totalDef = 5;
    private final int totalMidfielders = 5;
    private final int totalStrikers = 3;
    private HashMap<Integer, JButton> buttonlistMap;
    private int id = 0;
    public FormationPanel(int def, int mid, int at) {
        super();
        buttonlistMap = new HashMap<Integer, JButton>();
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
            PlayerPanel p = new PlayerPanel("Goalkeeper");
            line.add(p);
            buttonlistMap.put(id, p.getButton());
            id++;
        }
        for (int i = 0; i < def; ++i) {
            PlayerPanel p = new PlayerPanel("Defender");
            line.add(p);
            buttonlistMap.put(id, p.getButton());
            id++;
        }
        for (int i = 0; i < mid; ++i) {
            PlayerPanel p = new PlayerPanel("Midfielder");
            line.add(p);
            buttonlistMap.put(id, p.getButton());
            id++;
        }
        for (int i = 0; i < at; ++i) {
            PlayerPanel p = new PlayerPanel("Striker");
            line.add(p);
            buttonlistMap.put(id, p.getButton());
            id++;
        }
        return line;
    }
    private JPanel addLine(int number, String name) {
        JPanel line = new JPanel();
        line.setLayout(new GridLayout(1, number, 20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        for (int i = 0; i < number; i++) {
            PlayerPanel pn = new PlayerPanel(name);
            line.add(pn);
            buttonlistMap.put(id, pn.getButton());
            id++;
        }
        return line;
    }
    public HashMap getPlayerPanellist() {
        return (HashMap) buttonlistMap;
    }
}
