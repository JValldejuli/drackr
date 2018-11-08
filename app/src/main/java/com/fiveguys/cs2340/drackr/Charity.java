package com.fiveguys.cs2340.drackr;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Data structure to represent a charity.
 */
public class Charity implements Parcelable {

    private String key;
    private String name;
    private double latitude;
    private double longitude;
    private String streetAddress;
    private String city;
    private String state;
    private int zip;
    private CharityType type;
    private String phoneNumber;
    private String url;
    private List<Donation> donations;

    @Override
    public String toString() {
        return name;
    }

    public List<Donation> getDonations() {
        return Collections.unmodifiableList(donations);
    }

    public void addDonation(Donation donation) {
        donations.add(donation);
    }

    public Charity(String key, String name, double latitude, double longitude, String streetAddress,
                   String city, String state, int zip, CharityType type, String phoneNumber,
                   String url, List<Donation> donations) {
        this.key = key;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.type = type;
        this.phoneNumber = phoneNumber;
        this.url = url;
        this.donations = donations;
    }

    private Charity(Parcel in) {
        key = in.readString();
        name = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        streetAddress = in.readString();
        city = in.readString();
        state = in.readString();
        zip = in.readInt();
        phoneNumber = in.readString();
        url = in.readString();
        type = (CharityType) in.readSerializable();
        donations = new ArrayList<>();
        in.readTypedList(donations, Donation.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(name);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(streetAddress);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeInt(zip);
        dest.writeString(phoneNumber);
        dest.writeString(url);
        dest.writeSerializable(type);
        dest.writeTypedList(donations);
    }

    public CharSequence getFullDescription() {
        CharityType type = getType();
        String typeAsString = type.description();
        return getKey()
                + "\n"
                + getName()
                + "\n"
                + getLatitude()
                + "\n"
                + getLongitude()
                + "\n"
                + getStreetAddress()
                + "\n"
                + getCity()
                + "\n"
                + getState()
                + "\n"
                + getZip()
                + "\n"
                + typeAsString
                + "\n"
                + getPhoneNumber()
                + "\n"
                + getUrl().toString();
    }

    public String getMapMarkerDescription() {
        return getName()
                + "\n"
                + getPhoneNumber();
    }

    public LatLng getCoordinates() {
        return new LatLng(getLatitude(), getLongitude());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Charity> CREATOR = new Creator<Charity>() {
        @Override
        public Charity createFromParcel(Parcel in) {
            return new Charity(in);
        }

        @Override
        public Charity[] newArray(int size) {
            return new Charity[size];
        }
    };

    private String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    private String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    private String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    private CharityType getType() {
        return type;
    }

    public void setType(CharityType type) {
        this.type = type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private CharSequence getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
