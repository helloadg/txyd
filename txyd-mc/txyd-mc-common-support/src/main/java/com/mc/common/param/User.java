package com.mc.common.param;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private static final long serialVersionUID = 1595664669361469651L;
    private long id;
    private String name;
    private String email;
    private String phone;
    private List<Integer> ownCities = new ArrayList<Integer>();
    private List<Integer> ownWarehouses = new ArrayList<Integer>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Integer> getOwnCities() {
        return ownCities;
    }

    public void addOwnCity(int city) {
        ownCities.add(city);
    }

    public List<Integer> getOwnWarehouses() {
        return ownWarehouses;
    }

    public void addOwnWarehouse(int warehouse) {
        ownWarehouses.add(warehouse);
    }

    public void setOwnWarehouses(List<Integer> ownWarehouses) {
        this.ownWarehouses = ownWarehouses;
    }

}
