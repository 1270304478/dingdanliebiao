package com.bwei.shidongliang20180119.bean;

/**
 * Created by Adminjs on 2018/1/4.
 */

public class EventBean {
    private String pid;
    private String image;
    private String name;
    private String price;

    public EventBean(String pid, String image, String name, String price) {
        this.pid = pid;
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "EventBean{" +
                "pid=" + pid +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
