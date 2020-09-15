package com.example.dream_cookery.Models;

public class History {
    private String ClassName, Price, Subtitle, TimeSlot;

    public History()
    {

    }

    public History(String className, String price, String subtitle, String timeSlot) {
        ClassName = className;
        Price = price;
        Subtitle = subtitle;
        TimeSlot = timeSlot;
    }

    public String getClassName() {
        return ClassName;
    }

    public String getPrice() {
        return Price;
    }

    public String getSubtitle() {
        return Subtitle;
    }

    public String getTimeSlot() {
        return TimeSlot;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public void setSubtitle(String subtitle) {
        Subtitle = subtitle;
    }

    public void setTimeSlot(String timeSlot) {
        TimeSlot = timeSlot;
    }
}
