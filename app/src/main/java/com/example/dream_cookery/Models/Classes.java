package com.example.dream_cookery.Models;

public class Classes {

    private String cName, cDescription, cImage, cCategory, cPrice;
    public Classes(String cName, String cDescription, String cPrice, String cImage, String cCategory) {
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

    public String getcPrice() {
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

    public void setcPrice(String cPrice) {
        this.cPrice = cPrice;
    }

    public void setcImage(String cImage) {
        this.cImage = cImage;
    }

    public void setcCategory(String cCategory) {
        this.cCategory = cCategory;
    }
}
