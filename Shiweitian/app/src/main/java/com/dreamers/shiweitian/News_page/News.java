package com.dreamers.shiweitian.News_page;

/**
 * Created by stzha on 2017/2/19.
 */

public class News {
    private String id;
    private String source;  //推送内容（文字）
    private String title;  //推送内容（图片）
    private String content;      //时间
    private String time;      //时间
    private String photo;      //时间
    private String category;      //时间

    public String getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public String getPhoto() {
        return photo;
    }

    public String getCategory() {
        return category;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
