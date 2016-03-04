package alexvlad.view;

import alexvlad.model.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Vlad-minihp on 02/03/2016.
 */
public class PlayerPanel extends JPanel {

    private JButton playerButton;
    private JLabel playerNameLabel;
    private Player player;

    public PlayerPanel(Player player) {
        super();

        this.player = player;
        createWidget();
    }

    public void createWidget() {

        setLayout(new BorderLayout());

        playerButton = new JButton("+");


        playerNameLabel = new JLabel(player.getName());
        playerNameLabel.setHorizontalAlignment(JLabel.CENTER);
        JPanel buttonPanel = new JPanel();




        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));


        playerButton.setPreferredSize(new Dimension(50,50));

        buttonPanel.add(playerButton);

    //    buttonPanel.setAlignmentX(playerButton.CENTER_ALIGNMENT);
        add(buttonPanel,BorderLayout.CENTER);



        add(playerNameLabel, BorderLayout.SOUTH);

     //   setMaximumSize(new Dimension(10, 10));

        if(player.getImgPath()!=null)
        {
            setImage();
        }
    }

    public JButton getButton() {
        return playerButton;
    }
    public Player getPlayer() {return player;}

    public void setImage()
    {
        try {
            System.out.println(player.getImgPath());
            ImageIcon ico = new ImageIcon(player.getImgPath());
            playerButton.setText("");
            playerButton.setIcon(ico);
            playerButton.setPreferredSize(new Dimension(ico.getIconWidth(),ico.getIconHeight()));
         //   playerButton.setSize(ico.getIconHeight(),ico.getIconWidth());

        } catch (Exception e) {
        }
    }

    public void setNameLabel()
    {
        playerNameLabel.setText(player.getName());
    }

}
