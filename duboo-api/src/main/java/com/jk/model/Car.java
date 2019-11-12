package com.jk.model;

import java.io.Serializable;

public class Car implements Serializable {


    private static final long serialVersionUID = 8506191501202450805L;

    private Integer cid;
    private String cname;
    private Integer counts;
    private String cartime;
    private Integer typeid;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public String getCartime() {
        return cartime;
    }

    public void setCartime(String cartime) {
        this.cartime = cartime;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    @Override
    public String toString() {
        return "Car{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", counts=" + counts +
                ", cartime='" + cartime + '\'' +
                ", typeid=" + typeid +
                '}';
    }
}
