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

    /**
     * Constructor for the Squad class
     * Initialises the ArrayLists that keep record of the players
     */

    public Squad() {

        players = new ArrayList<Player>();
        goalkeepers = new ArrayList<Player>();
        defenders = new ArrayList<Player>();
        midfielders = new ArrayList<Player>();
        strikers = new ArrayList<Player>();

        subs = new ArrayList<Player>();

        id = 0;
    }

    /**
     * Generates and empty team based on the number of players of each role
     *
     * @param gk  number of Goalkeepers
     * @param def number of Defenders
     * @param mid number of Midfielders
     * @param at  number of Strikers
     */

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

    /**
     * Method to respond a formation change.
     * Will take or send players from the bench based on the formation
     *
     * @param def number of Defenders
     * @param mid number of Midfielders
     * @param at  number of Strikers
     */

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

        goalkeepers = new ArrayList<Player>();
        defenders = new ArrayList<Player>();
        midfielders = new ArrayList<Player>();
        strikers = new ArrayList<Player>();

        subs = new ArrayList<Player>();

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

    /**
     * Method that sends all the players to the sub bench
     * Sets the isSub value to true
     */

    private void subAll() {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setSub(true);
        }
    }

    /**
     * Getter for the ArrayList of Players
     *
     * @return ArrayList of all Players in the Squad
     */

    public ArrayList<Player> getPlayerList() {
        return players;
    }

    /**
     * Getter for the ArrayList of Goalkeepers
     *
     * @return ArrayList of Goalkeepers
     */

    public ArrayList<Player> getGoalkeepers() {
        return goalkeepers;
    }

    /**
     * Getter for the ArrayList of Defenders
     *
     * @return ArrayList of Defenders
     */

    public ArrayList<Player> getDefenders() {
        return defenders;
    }

    /**
     * Getter for the ArrayList of Midfielders
     *
     * @return ArrayList of Midfielders
     */

    public ArrayList<Player> getMidfielders() {
        return midfielders;
    }

    /**
     * Getter for the ArrayList of Strikers
     *
     * @return ArrayList of Strikers
     */

    public ArrayList<Player> getStrikers() {
        return strikers;
    }

    /**
     * Getter for the ArrayList of Subs (type Player)
     *
     * @return ArrayList of Subs
     */

    public ArrayList<Player> getSubs() {
        return subs;
    }
}
