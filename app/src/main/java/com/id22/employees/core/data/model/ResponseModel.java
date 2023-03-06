package com.id22.employees.core.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseModel implements Parcelable {
    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private ArrayList<EmployeeModel> data;

    public ResponseModel(boolean success, String message, ArrayList<EmployeeModel> data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    protected ResponseModel(Parcel in) {
        success = in.readByte() != 0;
        message = in.readString();
        data = in.createTypedArrayList(EmployeeModel.CREATOR);
    }

    public static final Creator<ResponseModel> CREATOR = new Creator<ResponseModel>() {
        @Override
        public ResponseModel createFromParcel(Parcel in) {
            return new ResponseModel(in);
        }

        @Override
        public ResponseModel[] newArray(int size) {
            return new ResponseModel[size];
        }
    };

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<EmployeeModel> getData() {
        return data;
    }

    public void setData(ArrayList<EmployeeModel> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeByte((byte) (success ? 1 : 0));
        dest.writeString(message);
        dest.writeTypedList(data);
    }
}
