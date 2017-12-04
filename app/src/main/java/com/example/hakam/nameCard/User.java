package com.example.hakam.nameCard;

/**
 * Created by hakam on 03/12/17.
 */

public class User {

    private String photo;
    private String name;
    private int age;

    public User() {}

    /**
     *
     * @param photo
     * @param name
     * @param age
     */
    public User(String photo, String name, int age) {
        this.photo = photo;
        this.name = name;
        this.age = age;
    }

    public String getPhoto() {
        return photo;
    }

    /**
     *
     * @param photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     *
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     *
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
