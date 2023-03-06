package com.id22.employees.core.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class EmployeeModel implements Parcelable {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("gender")
    private String gender;

    @SerializedName("place_of_birth")
    private String placeOfBirth;

    @SerializedName("date_of_birth")
    private String dateOfBirth;

    @SerializedName("status")
    private String status;

    @SerializedName("address")
    private String address;

    public EmployeeModel(int id, String name, String gender, String placeOfBirth, String dateOfBirth, String status, String address) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.placeOfBirth = placeOfBirth;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
        this.address = address;
    }

    protected EmployeeModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        gender = in.readString();
        placeOfBirth = in.readString();
        dateOfBirth = in.readString();
        status = in.readString();
        address = in.readString();
    }

    public static final Creator<EmployeeModel> CREATOR = new Creator<EmployeeModel>() {
        @Override
        public EmployeeModel createFromParcel(Parcel in) {
            return new EmployeeModel(in);
        }

        @Override
        public EmployeeModel[] newArray(int size) {
            return new EmployeeModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(gender);
        dest.writeString(placeOfBirth);
        dest.writeString(dateOfBirth);
        dest.writeString(status);
        dest.writeString(address);
    }
}
