package com.just.rebate.data;


public class Bank_Card_DataServer {
      public  String text;
      public  String number;
      public  int pic;


    public int backgroundcolor;

      public Bank_Card_DataServer(){

      }

    public int getBackgroundcolor() {
        return backgroundcolor;
    }

    public void setBackgroundcolor(int backgroundcolor) {
        this.backgroundcolor = backgroundcolor;
    }

    public String getText() {
        return text;
    }

    public void setText(String name) {
        this.text = text;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String toString(){
            return "Bank_Card_DataServer{"+"text="+text+"\n"+
                    "number="+number+"\n"+
                    "pic="+pic+"\n"+
                    "}";
    }
}
