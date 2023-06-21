package com.example.medcenter;

public class CardPacient {
    private String name;
    private String firstName;
    private String secondName;
    private String age;
    private String pol;
    private byte[] foto;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }



    public CardPacient(String name,String firstName, String secondName, String age, String pol){
        this.name=name;
        this.firstName=firstName;
        this.secondName=secondName;
        this.age=age;
        this.pol=pol;
    }
    public CardPacient(String name,String firstName, String secondName, String age, String pol,byte[] foto){
        this.name=name;
        this.firstName=firstName;
        this.secondName=secondName;
        this.age=age;
        this.pol=pol;
        this.foto=foto;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
