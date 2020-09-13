package com.example.dream_cookery.Models;

public class Classes {

    private String cName, cDescription, cImage, cCategory;
    double cPrice;
    public Classes(String cName, String cDescription, double cPrice, String cImage, String cCategory) {
        this.cName = cName;
        this.cDescription = cDescription;
        this.cPrice = cPrice;
        this.cImage = cImage;
        this.cCategory = cCategory;
    }

    public String getcName() {
        return cName;
    }

    public String getcDescription() {
        return cDescription;
    }

    public double getcPrice() {
        return cPrice;
    }

    public String getcImage() {
        return cImage;
    }

    public String getcCategory() {
        return cCategory;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public void setcDescription(String cDescription) {
        this.cDescription = cDescription;
    }

    public void setcPrice(double cPrice) {
        this.cPrice = cPrice;
    }

    public void setcImage(String cImage) {
        this.cImage = cImage;
    }

    public void setcCategory(String cCategory) {
        this.cCategory = cCategory;
    }
}
