package com.example.de.bloodbank;
public class DataModule {
    String address;
    String id;
    String name;
    String phone;
    String bloodgroup;
    String doner;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getDoner() {
        return doner;
    }

    public void setDoner(String doner) {
        this.doner = doner;
    }

    public String getBloodgroup_address() {
        return bloodgroup_address;
    }

    public void setBloodgroup_address(String bloodgroup_address) {
        this.bloodgroup_address = bloodgroup_address;
    }

    String bloodgroup_address;

}
