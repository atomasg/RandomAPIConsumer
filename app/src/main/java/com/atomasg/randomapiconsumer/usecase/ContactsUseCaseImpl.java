package com.atomasg.randomapiconsumer.usecase;

import android.util.Log;

import com.atomasg.randomapiconsumer.data.ContactsBean;
import com.atomasg.randomapiconsumer.data.remote.ContactService;
import com.atomasg.randomapiconsumer.model.Contact;
import com.atomasg.randomapiconsumer.model.ContactsResponse;
import com.atomasg.randomapiconsumer.ui.fragment.ContactListView;
import com.atomasg.randomapiconsumer.utilities.ApiUtils;
import com.atomasg.randomapiconsumer.utilities.Constants;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactsUseCaseImpl implements ContactsUseCase {

    private ContactService mService;
    private ContactListView view;
    private ContactsBean bean;

    public ContactsUseCaseImpl(ContactListView view) {
        this.view = view;
        this.mService = ApiUtils.getContactService();
        this.bean = ContactsBean.getInstance();
    }

    @Override
    public void getInitialContacts() {
        if (bean.getContacts() == null || bean.getContacts().isEmpty()) {
            mService.getContacts(Constants.DEFAULT_CONTACT_NUM).enqueue(new Callback<ContactsResponse>() {
                @Override
                public void onResponse(Call<ContactsResponse> call, Response<ContactsResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        //remove duplicates
                        Set<Contact> contactSet = new LinkedHashSet<>(response.body().getContacts());
                        List<Contact> aux = new ArrayList<>(contactSet);
                        bean.setContacts(aux);
                        view.updateContacts(bean.getContacts());
                    } else {
                        bean.setContacts(null);
                        view.updateContacts(null);
                    }
                }

                @Override
                public void onFailure(Call<ContactsResponse> call, Throwable t) {
                    view.showErrorMessage(t.getMessage());
                    Log.d("MainActivity", "error loading from API");
                }
            });
        } else {
            view.updateContacts(bean.getContacts());
        }
    }


    @Override
    public void getNextContacts() {
        mService.getContacts(Constants.DEFAULT_CONTACT_NUM).enqueue(new Callback<ContactsResponse>() {
            @Override
            public void onResponse(Call<ContactsResponse> call, Response<ContactsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    bean.getContacts().addAll(response.body().getContacts());
                    view.updateContacts(bean.getContacts());
                } else {
                    bean.setContacts(null);
                    view.updateContacts(null);
                }
            }

            @Override
            public void onFailure(Call<ContactsResponse> call, Throwable t) {
                view.showErrorMessage(t.getMessage());
                Log.d("MainActivity", "error loading from API");
            }
        });

    }

    @Override
    public Contact getDetail(String id) {
        for (Contact contact : bean.getContacts()) {
            if (contact.getLogin().getUuid().equals(id)) {
                return contact;
            }
        }

        return null;
    }
}
