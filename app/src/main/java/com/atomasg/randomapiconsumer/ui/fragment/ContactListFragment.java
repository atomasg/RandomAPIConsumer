package com.atomasg.randomapiconsumer.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.atomasg.randomapiconsumer.data.remote.ContactService;
import com.atomasg.randomapiconsumer.databinding.FragmentContactListBinding;
import com.atomasg.randomapiconsumer.model.Contact;
import com.atomasg.randomapiconsumer.model.ContactsResponse;
import com.atomasg.randomapiconsumer.ui.adapter.ContactsAdapter;
import com.atomasg.randomapiconsumer.utilities.ApiUtils;
import com.atomasg.randomapiconsumer.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactListFragment extends Fragment {


    private FragmentContactListBinding binding;
    private ContactsAdapter mAdapter;
    private ContactService mService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentContactListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        binding.rvContactList.setAdapter();
        init();
    }

    private void init() {
        mService = ApiUtils.getContactService();
        mAdapter = new ContactsAdapter(getContext(), new ArrayList<Contact>(0), new ContactsAdapter.PostItemListener() {
            @Override
            public void onPostClick(String id) {
                Toast.makeText(getContext(), "Go to detail..." + id, Toast.LENGTH_SHORT).show();
            }
        });


        binding.rvContactList.setAdapter(mAdapter);
        binding.rvContactList.setHasFixedSize(true);

        loadContacts();
    }

    private void loadContacts() {
        mService.getContacts(Constants.DEFAULT_CONTACT_NUM).enqueue(new Callback<ContactsResponse>() {
            @Override
            public void onResponse(Call<ContactsResponse> call, Response<ContactsResponse> response) {
                if (response.isSuccessful()){
                    mAdapter.updateContacts(response.body().getResults());
                }else{
                    mAdapter.updateContacts(null);
                }
            }

            @Override
            public void onFailure(Call<ContactsResponse> call, Throwable t) {
                showErrorMessage();
                Log.d("MainActivity", "error loading from API");
            }
        });
    }

    private void showErrorMessage() {
        Toast.makeText(getContext(), "An error occurred, please try again later.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}