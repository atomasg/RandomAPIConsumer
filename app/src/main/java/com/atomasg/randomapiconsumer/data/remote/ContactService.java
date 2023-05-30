package com.atomasg.randomapiconsumer.data.remote;

import com.atomasg.randomapiconsumer.model.ContactsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ContactService {

    @GET("api/")
    Call<ContactsResponse> getContacts(@Query("results") int num);
}
