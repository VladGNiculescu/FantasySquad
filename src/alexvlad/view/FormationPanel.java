package alexvlad.view;

import javax.swing.*;
import java.awt.*;

public class FormationPanel extends JPanel {

    public FormationPanel(int def, int mid, int at) {

        JPanel pl = new JPanel();
        pl.add(addLine(def,"Defender"));
        pl.add(addLine(mid,"Midfielder"));
        pl.add(addLine(at,"Striker"));


        
    }
    private JPanel addLine(int number, String name)
    {
       JPanel line = new JPanel();
        line.setLayout(new GridLayout(1,number));
        for(int i=0;i<number;i++)
        {
            line.add(createPlayerPanel(name));
        }
        return line;
    }

    private JPanel createPlayerPanel(String name)
    {
        JPanel player = new JPanel();
        player.setLayout(new BorderLayout());
        JButton playerButton = new JButton();
        JTextField playerName = new JTextField();
        playerName.setText(name);

        player.add(playerButton,BorderLayout.CENTER);
        player.add(playerName,BorderLayout.SOUTH);



        return player;
    }

}
