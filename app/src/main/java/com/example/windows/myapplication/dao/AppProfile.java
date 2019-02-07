package com.example.windows.myapplication.dao;

public class AppProfile {

    private String email;
    private String pass;
    private String fname;
    private String lname;
    public AppProfile()
    {
        email = "";
        pass = "";
        fname = "";
        lname = "";
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
