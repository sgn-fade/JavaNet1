package tcpWork.Data;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User implements Serializable {
    private static final DateFormat dateFormatter =
            new SimpleDateFormat("dd.MM.yyyy");
    private String name;
    private String surName;
    private String sex;
    private Date birthday;

    public User(String name, String surName, String sex, String birthday) {
        this.name = name;
        this.surName = surName;
        this.sex = sex;
        try {
            this.birthday = dateFormatter.parse(birthday);
        } catch (ParseException e) {
            System.out.println("Error: " + e);
        }
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + dateFormatter.format(birthday) +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return dateFormatter.format(birthday);
    }

    public void setBirthday(String birthday) {
        try {
            this.birthday = dateFormatter.parse(birthday);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
