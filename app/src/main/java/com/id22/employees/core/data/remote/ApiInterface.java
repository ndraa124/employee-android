package com.id22.employees.core.data.remote;

import com.id22.employees.core.data.model.ResponseModel;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    String v1 = "api/v1";

    @GET(v1 + "/employee")
    Observable<ResponseModel> getAllEmployee();

    @FormUrlEncoded
    @POST(v1 + "/employee")
    Observable<ResponseModel> createEmployee(
            @Field("name") String name,
            @Field("gender") String gender,
            @Field("place_of_birth") String pob,
            @Field("date_of_birth") String dob,
            @Field("status") String status,
            @Field("address") String address
    );

    @FormUrlEncoded
    @PUT(v1 + "/employee/{id}")
    Observable<ResponseModel> updateEmployee(
            @Path("id") int id,
            @Field("name") String name,
            @Field("gender") String gender,
            @Field("place_of_birth") String pob,
            @Field("date_of_birth") String dob,
            @Field("status") String status,
            @Field("address") String address
    );

    @DELETE(v1 + "/employee/{id}")
    Observable<ResponseModel> deleteEmployee(
            @Path("id") int id
    );
}
