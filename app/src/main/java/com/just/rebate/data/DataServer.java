package com.just.rebate.data;


public class DataServer {
      public  String name;
      public  String time;
      public  String rebate;
      public  String detailed;

      public DataServer(){

      }

        public DataServer(String name, String time, String rebate,String detailed){
            this.name = name;
            this.time = time;
            this.rebate = rebate;
            this.detailed= detailed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRebate() {
        return rebate;
    }

    public void setRebate(String rebate) {
        this.rebate = rebate;
    }

    public String getDetailed() {
        return detailed;
    }

    public void setDetailed(String detailed) {
        this.detailed = detailed;
    }
    public String toString(){
            return "DataServer{"+"Name="+name+"\n"+
                    "Time="+time+"\n"+
                    "Rebate="+rebate+"\n"+
                    "Detailed="+detailed+"\n+" +
                    "}";
    }
}
