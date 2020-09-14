package com.example.dream_cookery.Models;

public class Classes {

    private String cName, cDescription, cImage, cCategory, cPrice, cID, cInfoDescription, cInsName;

    public Classes()
    {

    }

    public Classes(String cName, String cDescription, String cImage, String cCategory, String cPrice, String cID, String cInfoDescription, String cInsName) {
        this.cName = cName;
        this.cDescription = cDescription;
        this.cImage = cImage;
        this.cCategory = cCategory;
        this.cPrice = cPrice;
        this.cID = cID;
        this.cInfoDescription = cInfoDescription;
        this.cInsName = cInsName;
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

    public String getcID() {
        return cID;
    }

    public String getcInfoDescription() {
        return cInfoDescription;
    }

    public String getcInsName() {
        return cInsName;
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

    public void setcID(String cID) {
        this.cID = cID;
    }

    public void setcInfoDescription(String cInfoDescription) {
        this.cInfoDescription = cInfoDescription;
    }

    public void setcInsName(String cInsName) {
        this.cInsName = cInsName;
    }
}