package com.fiveguys.cs2340.drackr;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Data structure to represent a donation.
 */
public class Donation implements Parcelable {

    private Date date;
    private String zipCode;
    private String description;
    private double amount;
    private DonationType type;

    /**
     * Designated constructor for Donation.
     * @param date The date of the donation.
     * @param zipCode The zip code of the donation.
     * @param description The description of the donation.
     * @param amount The donation amount.
     * @param type
     */
    public Donation(Date date, String zipCode, String description,
                    double amount, DonationType type) {
        this.date = date;
        this.zipCode = zipCode;
        this.description = description;
        this.amount = amount;
        this.type = type;
    }

    private Donation(Parcel in) {
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

    public CharSequence getFullDescription() {
        return getDate()
                + "\n"
                + getZipCode()
                + "\n"
                + getDescription()
                + "\n"
                + getShortDescription()
                + "\n"
                + "$" + getAmount()
                + "\n"
                + getType();
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

    public String getZipCode() {
        return zipCode;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public DonationType getType() {
        return type;
    }

    private static final int SHORT_DESCRIPTION_LENGTH = 40;
    public String getShortDescription() {
        return description.substring(0, Math.min(description.length(), SHORT_DESCRIPTION_LENGTH));
    }

}
