package alexvlad.model;

import java.io.File;
public class Player {

    private String imgPath;
    private int id;
    private String name;
    private boolean isSub = false;
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
