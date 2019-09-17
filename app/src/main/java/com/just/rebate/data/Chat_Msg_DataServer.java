package com.just.rebate.data;

public class Chat_Msg_DataServer {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT = 1;
    public int Image;
    public String Content;
    public int Type;

    public void setImage(int image) {
        Image = image;
    }

    public void setContent(String content) {
        Content = content;
    }

    public void setType(int type) {
        Type = type;
    }

    public Chat_Msg_DataServer(){

    }

    public int getImage() {
        return Image;
    }

    public String getContent() {
        return Content;
    }

    public int getType() {
        return Type;
    }

    @Override
    public String toString() {
        return "Chat_Msg_DataServer{" +
                "Image=" + Image +
                ", Content='" + Content + '\'' +
                ", Type=" + Type +
                '}';
    }
}
