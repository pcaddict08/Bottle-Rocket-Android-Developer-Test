package com.einzig.stores.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

import java.util.List;

public class Store extends SugarRecord<Store> implements Parcelable {

    String address;
    String city;
    String name;
    float latitude;
    int zipcode;
    String storeLogoURL;
    String phone;
    float longitude;
    int storeID;
    String state;

    public String getAddress2() {
        return getCity() + ", " + getState() + " " + getZipcode();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getStoreLogoURL() {
        return storeLogoURL;
    }

    public void setStoreLogoURL(String storeLogoURL) {
        this.storeLogoURL = storeLogoURL;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.address);
        dest.writeString(this.city);
        dest.writeString(this.name);
        dest.writeFloat(this.latitude);
        dest.writeInt(this.zipcode);
        dest.writeString(this.storeLogoURL);
        dest.writeString(this.phone);
        dest.writeFloat(this.longitude);
        dest.writeInt(this.storeID);
        dest.writeString(this.state);
    }

    public Store() {
    }

    protected Store(Parcel in) {
        this.address = in.readString();
        this.city = in.readString();
        this.name = in.readString();
        this.latitude = in.readFloat();
        this.zipcode = in.readInt();
        this.storeLogoURL = in.readString();
        this.phone = in.readString();
        this.longitude = in.readFloat();
        this.storeID = in.readInt();
        this.state = in.readString();
    }

    public static final Parcelable.Creator<Store> CREATOR = new Parcelable.Creator<Store>() {
        @Override
        public Store createFromParcel(Parcel source) {
            return new Store(source);
        }

        @Override
        public Store[] newArray(int size) {
            return new Store[size];
        }
    };

    public static void saveList(List<Store> stores) {
        Store.deleteAll(Store.class);
        for (Store store : stores)
            store.save();
    }

    public String getLatLngStr() {
        try {
            return getLatitude() + ", " + getLongitude();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "N/A";
    }

    public String getShareString() {
        return getName() + "\n" +
                "Store #" + getStoreID() + "\n" +
                getPhone() + "\n" +
                getAddress() + "\n" +
                getAddress2() + "\n" +
                getLatLngStr();
    }
}
