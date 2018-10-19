package com.fiveguys.cs2340.drackr;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Donation implements Parcelable {

    Date date;
    String zipCode;
    String description;
    double amount;
    DonationType type;

    public Donation(Date date, String zipCode, String description, double amount, DonationType type) {
        this.date = date;
        this.zipCode = zipCode;
        this.description = description;
        this.amount = amount;
        this.type = type;
    }

    protected Donation(Parcel in) {
        date = (Date) in.readSerializable();
        zipCode = in.readString();
        description = in.readString();
        amount = in.readDouble();
        type = (DonationType) in.readSerializable();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(date);
        dest.writeString(zipCode);
        dest.writeString(description);
        dest.writeDouble(amount);
        dest.writeSerializable(type);
    }

    @Override
    public String toString() {
        return zipCode + " " + description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Donation> CREATOR = new Creator<Donation>() {
        @Override
        public Donation createFromParcel(Parcel in) {
            return new Donation(in);
        }

        @Override
        public Donation[] newArray(int size) {
            return new Donation[size];
        }
    };

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public DonationType getType() {
        return type;
    }

    public void setType(DonationType type) {
        this.type = type;
    }

    public String getShortDescription() {
        return description.substring(0, Math.min(description.length(), 40));
    }

}
