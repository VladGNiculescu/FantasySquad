package alexvlad.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;

public class PlayerPanel extends JPanel {

    private JButton playerButton;
    private JTextField playerNameTextField;
    private HashMap<String, String> player;
    private JPanel textfieldPanel;
    private JPanel buttonPanel;
    private Fantasy f;

    public PlayerPanel(HashMap<String, String> player, Fantasy f) {
        super();

        this.player = player;
        this.f = f;
        createWidget();
    }

    public void createWidget() {

        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBorder(BorderFactory.createEmptyBorder());

        playerButton = new JButton("+");

        playerNameTextField = new JTextField(player.get("name"));
        playerNameTextField.setHorizontalAlignment(JTextField.CENTER);
        JPanel p = new JPanel();
        BorderLayout bl = new BorderLayout();
        bl.setVgap(5);
        p.setLayout(bl);

        p.add(playerButton, BorderLayout.CENTER);

        p.add(playerNameTextField, BorderLayout.SOUTH);

        playerButton.setPreferredSize(new Dimension(70, 30));


        add(p);

        if (player.get("imagePath") != null) {
            setImage();
        }
    }

    public JButton getPlayerButton() {
        return playerButton;
    }

    public JTextField getPlayerNameTextField() {
        return playerNameTextField;
    }

    public HashMap<String, String> getPlayer() {
        return player;
    }

    public void setImage() {
        try {
            ImageIcon ico = new ImageIcon(player.get("imagePath"));
            playerButton.setText("");
            playerButton.setIcon(ico);
            playerButton.setPreferredSize(new Dimension(ico.getIconWidth(), ico.getIconHeight()));
            f.resize();
        } catch (Exception e) {
        }
    }

    public void setNameLabel() {
        playerNameTextField.setText(player.get("name"));
    }
}
