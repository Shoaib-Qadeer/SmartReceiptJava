package com.shoaibqadeer.smartreceipt;


public class Customer {
    int id;
    String name;
    int age;
    String address;
    String city;
    String phone_num;
    String email_address;
    String password;


    Customer(){}


    Customer(int ied,String nm, int ag, String addr, String city, String phone, String email, String pass){
        this.id=ied;
        this.name=nm;
        this.age=ag;
        this.city=city;
        this.address=addr;
        this.phone_num=phone;
        this.email_address=email;
        this.password=pass;

    }
    Customer(Customer ab){
        this.id=ab.getId();
        this.name=ab.getName();
        this.age=ab.getAge();
        this.city=ab.getCity();
        this.address=ab.getAddress();
        this.phone_num=ab.getPhone_num();
        this.email_address=ab.getEmail_address();
        this.password=ab.getPassword();
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getEmail_address() {
        return email_address;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone_num() {
        return phone_num;
    }
}
