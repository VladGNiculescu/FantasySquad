package alexvlad.model;

public class Player {
    private String imgPath;
    private int id;
    private String name;

    public Player(int id, String imgPath, String name) {
        this.name = name;
        this.id = id;
        this.imgPath = imgPath;

    }

    public String getImgPath() {
        return imgPath;
    }

   public void setName(String name) {
       this.name = name;
   }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
