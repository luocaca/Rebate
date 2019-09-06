package com.just.rebate.data;


public class detailed_DataServer {
      public  String text;


      public detailed_DataServer(){

      }

        public detailed_DataServer(String text){
            this.text = text;

    }

    public String getText() {
        return text;
    }

    public void setText(String name) {
        this.text = name;
    }

    public String toString(){
            return "detailed_DataServer{"+"Text="+text+"\n"+
                    "}";
    }
}
