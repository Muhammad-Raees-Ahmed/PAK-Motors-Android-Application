package com.example.shopping.ApplicationProgrammingInterface;

public class DATA {

  public  String bname,cname,odate;

    public DATA(String bname, String cname, String odate) {
        this.bname = bname;
        this.cname = cname;
        this.odate = odate;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getOdate() {
        return odate;
    }

    public void setOdate(String odate) {
        this.odate = odate;
    }

}
