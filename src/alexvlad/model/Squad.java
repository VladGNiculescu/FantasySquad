package alexvlad.model;

import java.util.ArrayList;

public class Squad {

    private ArrayList<Player> players;

    private int id;

    public Squad() {

        players = new ArrayList<Player>();

        id = 0;
    }

    public void generate(int gk, int def, int mid, int at) {


        for (int i = 0; i < gk; i++) {
            players.add(new Goalkeeper(id));
            id++;
        }
        for (int i = 0; i < def; i++) {
            players.add(new Defender(id));
            id++;
        }
        for (int i = 0; i < mid; i++) {
            players.add(new Midfielder(id));
            id++;
        }
        for (int i = 0; i < at; i++) {
            players.add(new Striker(id));
            id++;
        }


    }


    public void updateFieldPlayers(int def, int mid, int at) {

        subAll();
        int countdef = 0;
        int countmid = 0;
        int countat = 0;
        players.get(0).setSub(false);
        for (int i = 0; i < players.size(); i++) {
            if (countdef < def && players.get(i) instanceof Defender) {
                players.get(i).setSub(false);
                countdef++;
            }
            if (countmid < mid && players.get(i) instanceof Midfielder) {
                players.get(i).setSub(false);
                countmid++;
            }
            if (countat < at && players.get(i) instanceof Striker) {
                players.get(i).setSub(false);
                countat++;
            }
        }


    }

    private void subAll() {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setSub(true);
        }
    }

    public ArrayList<Player> getPlayerList() {
        return players;
    }


}
