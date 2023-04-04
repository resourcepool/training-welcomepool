package main.java;

public class Member{
    private String name;
    private String email;
    private String promotion;

    public Member(String n, String e, String p) {
        name= n;
        email=e;
        promotion=p;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPromotion() {
        return promotion;
    }
}
