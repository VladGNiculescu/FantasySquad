package alexvlad.model;

public class Player {
    private String imgPath;
    private int id;
    private String name;

    public Player(int id, String name) {
        this.name = name;
        this.id = id;
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
