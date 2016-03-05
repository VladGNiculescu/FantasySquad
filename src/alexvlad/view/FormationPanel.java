package alexvlad.view;

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
        setMinimumSize(new Dimension(500, 500));
    }

    public void remove(ArrayList<HashMap<String, String>> players) {
        remove(addLine(players));
    }

    public void add(ArrayList<HashMap<String, String>> players) {
        add(addLine(players));
    }

    private JPanel addLine(ArrayList<HashMap<String, String>> players) {

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

        for (Integer key : buttonlistMap.keySet()) {
            JButton currentButton = buttonlistMap.get(key).getPlayerButton();

            if (currentButton == button) {
                toReturn = key;
                break;
            }
        }

        return toReturn;
    }
}
