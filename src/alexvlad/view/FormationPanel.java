package alexvlad.view;

import alexvlad.model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FormationPanel extends JPanel {

    private HashMap<Integer, PlayerPanel> buttonlistMap;
    private int id = 0;

    public FormationPanel() {

        super();
        buttonlistMap = new HashMap<Integer, PlayerPanel>();

        setLayout(new GridLayout(5, 1, 20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setMinimumSize(new Dimension(300, 300));
    }
/*
    private JPanel addSubLine(int goalkeepers, int def, int mid, int at) {

        JPanel line = new JPanel();
        line.setLayout(new GridLayout(1, 4, 20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (int i = 0; i < goalkeepers; ++i) {
            PlayerPanel p = new PlayerPanel("Goalkeeper");
            line.add(p);
            buttonlistMap.put(id, p);
            id++;
        }

        for (int i = 0; i < def; ++i) {
            PlayerPanel p = new PlayerPanel("Defender");
            line.add(p);
            buttonlistMap.put(id, p);
            id++;
        }

        for (int i = 0; i < mid; ++i) {
            PlayerPanel p = new PlayerPanel("Midfielder");
            line.add(p);
            buttonlistMap.put(id, p);
            id++;
        }

        for (int i = 0; i < at; ++i) {
            PlayerPanel p = new PlayerPanel("Striker");
            line.add(p);
            buttonlistMap.put(id, p);
            id++;
        }

        return line;
    }

    private JPanel addLine(int number, String name) {

        JPanel line = new JPanel();
        line.setLayout(new GridLayout(1, number, 20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (int i = 0; i < number; ++i) {
            PlayerPanel pn = new PlayerPanel(name);
            line.add(pn);
            buttonlistMap.put(id, pn);
            id++;
        }

        return line;
    }
*/
    public JPanel addLine(ArrayList<Player> players) {

        System.out.println("Players: " + players);

        JPanel line = new JPanel();
        line.setLayout(new GridLayout(1, players.size(), 20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (int i = 0; i < players.size(); ++i) {
            PlayerPanel pn = new PlayerPanel(players.get(i));
            line.add(pn);
            buttonlistMap.put(id, pn);
            id++;
        }

        return line;
    }

    public HashMap<Integer, PlayerPanel> getPlayerButtonsMap() {

        return buttonlistMap;
    }

    public Integer getPlayerIDForButton(JButton button) {

        Integer toReturn = -1;

        for (Integer key: buttonlistMap.keySet()) {
            JButton currentButton = buttonlistMap.get(key).getButton();

            if (currentButton == button) {
                toReturn = key;
                break;
            }
        }

        return toReturn;
    }
}
