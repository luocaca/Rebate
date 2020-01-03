package com.just.integralmanagement.entity;

public class TokenDataBean {

    /**
     * Type : 200
     * Content : 登录成功
     * Data : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1laWQiOiIxIiwidW5pcXVlX25hbWUiOiJhZG1pbiIsIm5iZiI6MTU3NTI3MDAxMiwiZXhwIjoxNTc1MzU2NDEyLCJpYXQiOjE1NzUyNzAwMTIsImlzcyI6Im9zaGFycCBpZGVudGl0eSIsImF1ZCI6Im9zaGFycCBhbmd1bGFyIGRlbW8ifQ.oNjMZo1jYYKf80QOyXs5LzToe3KTiGx9GcfxnEtZdvo
     */

    private int Type;
    private String Content;
    private String Data;

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }
}
