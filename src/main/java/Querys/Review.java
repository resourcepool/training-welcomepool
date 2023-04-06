package main.java.Querys;

import java.sql.Date;
import java.sql.Timestamp;

public class Review {
    private int id;
    private String name;
    private String description;
    private Timestamp date;
    private String promotion;
    private int classId;

    public Review() {}

    public Review(String name, String description, Timestamp date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getPromotion(){return promotion;}

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }
}
