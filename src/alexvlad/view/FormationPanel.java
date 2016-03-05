package alexvlad.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
public class FormationPanel extends JPanel {

    private HashMap<Integer, PlayerPanel> buttonlistMap;
    private int id = 0;
    private Fantasy f;
    public FormationPanel(Fantasy f) {

        super();
        this.f = f;
        buttonlistMap = new HashMap<Integer, PlayerPanel>();

        GridLayout grid = new GridLayout(5, 1);
        setLayout(grid);

        setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
    }
    public void add(ArrayList<HashMap<String, String>> players) {
        add(addLine(players));
    }
    private JPanel addLine(ArrayList<HashMap<String, String>> players) {

        JPanel line = new JPanel();
        line.setLayout(new GridLayout(1, players.size()));

        for (int i = 0; i < players.size(); ++i) {
            PlayerPanel pn = new PlayerPanel(players.get(i), f);

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
    public Integer getPlayerIDForTextField(JTextField textField) {

        Integer toReturn = -1;

        for (Integer key : buttonlistMap.keySet()) {
            JTextField currentTextfield = buttonlistMap.get(key).getPlayerNameTextField();

            if (currentTextfield == textField) {
                toReturn = key;
                break;
            }
        }

        return toReturn;
    }
}
