package alexvlad.model;

import java.util.ArrayList;

public class Squad {

    private ArrayList<Player> players;
    private ArrayList<Player> goalkeepers;
    private ArrayList<Player> defenders;
    private ArrayList<Player> midfielders;
    private ArrayList<Player> strikers;
    private ArrayList<Player> subs;

    private int id;

    public Squad() {

        players = new ArrayList<Player>();
        goalkeepers = new ArrayList<Player>();
        defenders = new ArrayList<Player>();
        midfielders = new ArrayList<Player>();
        strikers = new ArrayList<Player>();

        subs = new ArrayList<Player>();

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


        ArrayList<Player> goalkeepers = new ArrayList<Player>();
        ArrayList<Player> defenders = new ArrayList<Player>();
        ArrayList<Player> midfielders = new ArrayList<Player>();
        ArrayList<Player> strikers = new ArrayList<Player>();

        ArrayList<Player> subs = new ArrayList<Player>();

        goalkeepers.add(players.get(0));
        subs.add(players.get(1));


        for (int i = 0; i < players.size(); ++i) {
            if (players.get(i) instanceof Defender && !players.get(i).isSub()) {
                defenders.add(players.get(i));
            } else if (players.get(i) instanceof Defender && players.get(i).isSub()) {
                subs.add(players.get(i));
            }

            if (players.get(i) instanceof Midfielder && !players.get(i).isSub()) {
                midfielders.add(players.get(i));
            } else if (players.get(i) instanceof Midfielder && players.get(i).isSub()) {
                subs.add(players.get(i));
            }

            if (players.get(i) instanceof Striker && !players.get(i).isSub()) {
                strikers.add(players.get(i));
            } else if (players.get(i) instanceof Striker && players.get(i).isSub()) {
                subs.add(players.get(i));
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

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> getGoalkeepers() {
        return goalkeepers;
    }

    public void setGoalkeepers(ArrayList<Player> goalkeepers) {
        this.goalkeepers = goalkeepers;
    }

    public ArrayList<Player> getDefenders() {
        return defenders;
    }

    public void setDefenders(ArrayList<Player> defenders) {
        this.defenders = defenders;
    }

    public ArrayList<Player> getMidfielders() {
        return midfielders;
    }

    public void setMidfielders(ArrayList<Player> midfielders) {
        this.midfielders = midfielders;
    }

    public ArrayList<Player> getStrikers() {
        return strikers;
    }

    public void setStrikers(ArrayList<Player> strikers) {
        this.strikers = strikers;
    }

    public ArrayList<Player> getSubs() {
        return subs;
    }

    public void setSubs(ArrayList<Player> subs) {
        this.subs = subs;
    }
}
