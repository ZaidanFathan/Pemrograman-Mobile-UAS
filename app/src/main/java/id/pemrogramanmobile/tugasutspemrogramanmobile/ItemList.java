package id.pemrogramanmobile.tugasutspemrogramanmobile;

public class ItemList {
    private String id;
    private String title;
    private String description;

    public ItemList(String title, String description) {
        this.title = title;
        this.description =description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
