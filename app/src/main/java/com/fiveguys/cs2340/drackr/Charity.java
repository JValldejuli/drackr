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
    private final List<Donation> donations;

    @Override
    public String toString() {
        return name;
    }

    /**
     * Gets this charities donations.
     * @return The charities donations.
     */
    public List<Donation> getDonations() {
        return Collections.unmodifiableList(donations);
    }

    /**
     * Adds a donation to the charities donations.
     * @param donation The donation to add.
     */
    public void addDonation(Donation donation) {
        donations.add(donation);
    }

    /**
     * The designated constructor for Charity.
     * @param key ID of the charity
     * @param name Name of the charity
     * @param latitude Latitude of the charity
     * @param longitude Longitude of the charity
     * @param streetAddress Street address of the charity
     * @param city City of the charity
     * @param state State of the charity
     * @param zip Zip of the charity
     * @param type Type of the charity
     * @param phoneNumber Phone number of the charity
     * @param url URL of the charity
     * @param donations Donations to the charity
     */
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

    /**
     * Constructs a multiline, full description of the charity.
     * @return The full description.
     */
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

    /**
     * A short description for the map marker.
     * @return A short description for the map marker.
     */
    public String getMapMarkerDescription() {
        return getName()
                + "\n"
                + getPhoneNumber();
    }

    /**
     * Gets the coordinates of the Charity as a LatLng object.
     * @return The coordinates of the Charity as a LatLng object.
     */
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

    /**
     * Get charity key/ID.
     * @return Charity key/ID.
     */
    private String getKey() {
        return key;
    }

    /**
     * Get charity name.
     * @return Charity name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get charity latitude.
     * @return Charity latitude.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Get charity longitude.
     * @return Charity longitude.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Get charity street address.
     * @return Charity street address.
     */
    private String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Get charity city.
     * @return Charity city.
     */
    private String getCity() {
        return city;
    }

    /**
     * Get charity state.
     * @return Charity state.
     */
    private String getState() {
        return state;
    }

    /**
     * Get charity zip.
     * @return Charity zip.
     */
    private int getZip() {
        return zip;
    }

    /**
     * Get charity type.
     * @return Charity type.
     */
    private CharityType getType() {
        return type;
    }

    /**
     * Get charity phone number.
     * @return Charity phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Get charity url.
     * @return Charity url.
     */
    private CharSequence getUrl() {
        return url;
    }

}
