package alexvlad.model;

public class Player {

    /**
     * Main class for Player
     * Has an imagePath, ID, a name and boolean isSub
     */

    private String imgPath;
    private int id;
    private String name;
    private boolean isSub = false;

    /**
     * Constructor for the player class
     *
     * @param id   ID of the player
     * @param name The player name
     */

    public Player(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public void setSub(boolean sub) {
        isSub = sub;
    }

    public boolean isSub() {
        return isSub;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
