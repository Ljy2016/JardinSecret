package com.azadljy.jardinsecret.page.meterreadingtest;

public class UserBookInfo {
    private int id;
    private int lastmeterdata=0;
    private String accounttype;
    private String customeraddress;
    private String customername;
    private  float avgwaternum;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserBookInfo(int id, int lastmeterdata, String accounttype, String customeraddress, String customername, float avgwaternum) {
        this.id = id;
        this.lastmeterdata = lastmeterdata;
        this.accounttype = accounttype;
        this.customeraddress = customeraddress;
        this.customername = customername;
        this.avgwaternum = avgwaternum;
    }

    public int getLastmeterdata() {
        return lastmeterdata;
    }

    public void setLastmeterdata(int lastmeterdata) {
        this.lastmeterdata = lastmeterdata;
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    public String getCustomeraddress() {
        return customeraddress;
    }

    public void setCustomeraddress(String customeraddress) {
        this.customeraddress = customeraddress;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public float getAvgwaternum() {
        return avgwaternum;
    }

    public void setAvgwaternum(float avgwaternum) {
        this.avgwaternum = avgwaternum;
    }

    @Override
    public String toString() {
        return "UserBookInfo{" +
                "id=" + id +
                ", lastmeterdata=" + lastmeterdata +
                ", accounttype='" + accounttype + '\'' +
                ", customeraddress='" + customeraddress + '\'' +
                ", customername='" + customername + '\'' +
                ", avgwaternum=" + avgwaternum +
                '}';
    }
}
