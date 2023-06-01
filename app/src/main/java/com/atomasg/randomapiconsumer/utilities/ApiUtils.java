package com.atomasg.randomapiconsumer.utilities;

import com.atomasg.randomapiconsumer.data.remote.ContactService;
import com.atomasg.randomapiconsumer.data.remote.RetrofitClient;

public class ApiUtils {

    public static ContactService getContactService(){
        return RetrofitClient.getClient(Constants.BASE_URL).create(ContactService.class);
    }
}
