package model;

public class Disaster {
    private String type;
    private String title;
    private String location;
    private String date;

    public Disaster(String type, String title, String location, String date) {
        this.type = type;
        this.title = title;
        this.location = location;
        this.date = date;
    }

    public String getType() { return type; }
    public String getTitle() { return title; }
    public String getLocation() { return location; }
    public String getDate() { return date; }

    @Override
    public String toString() {
        return String.format("[%s] %s at %s on %s", type, title, location, date);
    }
}
