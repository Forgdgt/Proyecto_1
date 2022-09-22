package com.example.votacion;

public class UserModel {

    boolean isSelected;
    String userName;
    String casilla;
    int imagen;

    //now create constructor and getter setter method using shortcut like command+n for mac & Alt+Insert for window.


    public UserModel(boolean isSelected, String userName, String casilla,int imagen) {
        this.isSelected = isSelected;
        this.userName = userName;
        this.casilla =casilla;
        this.imagen =imagen;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getCasilla() {
        return casilla;
    }

    public void setCasilla(String casilla) {
        this.casilla = casilla;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

