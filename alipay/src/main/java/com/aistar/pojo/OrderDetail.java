package com.aistar.pojo;

public class OrderDetail {
    private Orders order;
    private Detail detail;
    private Pro pro;

    public OrderDetail(){}

    public OrderDetail(Orders order, Detail detail, Pro pro) {
        this.order = order;
        this.detail = detail;
        this.pro = pro;
    }

    public Orders getOrders() {
        return order;
    }

    public void setOrders(Orders order) {
        this.order = order;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public Pro getPro() {
        return pro;
    }

    public void setPro(Pro pro) {
        this.pro = pro;
    }
}
