package main.java.Querys;

public class Promotion {

    public Promotion(){}
    public Promotion(String name) {
        this.name = name;
    }

    private int id;

    private String name;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

