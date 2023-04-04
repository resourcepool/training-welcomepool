package Querys;

import java.sql.Date;

public class Member {
    private int id;
    private String name;
    private String email;
    private Date birthdate;
    private int classId;

    public Member() {}

    public Member(String name, String email, Date birthdate, int classId) {
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
        this.classId = classId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}
