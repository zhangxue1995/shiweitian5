package com.dreamers.shiweitian;

/**
 * Created by stzha on 2017/2/18.
 */

public class User_register {
    private String id;
    private String password;  //推送内容（文字）
    private String name;  //推送内容（图片）
    private String photo;      //时间

    public String getId() {
        return id;
    }


    public String getPassword() {

        return password;
    }

    public String getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
