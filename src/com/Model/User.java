package com.Model;

import com.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int id;
    private String name;
    private String uname;
    private String pass;
    private String email;
    private String type;
    private String city;

    public User(int id, String name, String uname, String pass, String email, String type, String city) {
        this.id = id;
        this.name = name;
        this.uname = uname;
        this.pass = pass;
        this.email = email;
        this.type = type;
        this.city = city;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    //login ekranında email ve pass i db de sorgular
    public static User getFetch(String email, String pass){
        User obj = null;
        String query = "SELECT * FROM user WHERE email = ? AND pass = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, email);
            pr.setString(2, pass);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                switch (rs.getString("type")){
                    case "company":
                        obj = new Company();
                        break;
                    case "customer":
                        obj = new Customer();
                        break;
                    default:
                        obj = new Customer();
                }
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPass(rs.getString("pass"));
                obj.setEmail(rs.getString("email"));
                obj.setType(rs.getString("type"));
                obj.setCity(rs.getString("city"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }


}

