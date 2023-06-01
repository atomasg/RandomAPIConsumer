package com.atomasg.randomapiconsumer.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.atomasg.randomapiconsumer.R;
import com.atomasg.randomapiconsumer.data.remote.ContactService;
import com.atomasg.randomapiconsumer.databinding.FragmentContactListBinding;
import com.atomasg.randomapiconsumer.model.Contact;
import com.atomasg.randomapiconsumer.model.ContactsResponse;
import com.atomasg.randomapiconsumer.ui.adapter.ContactsAdapter;
import com.atomasg.randomapiconsumer.usecase.ContactsUseCaseImpl;
import com.atomasg.randomapiconsumer.utilities.ApiUtils;
import com.atomasg.randomapiconsumer.utilities.Constants;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactListFragment extends Fragment implements ContactListView {


    private FragmentContactListBinding binding;
    private ContactsAdapter mAdapter;
    private ContactsUseCaseImpl contactUseCase;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentContactListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        contactUseCase = new ContactsUseCaseImpl(this);
        mAdapter = new ContactsAdapter(new ArrayList<Contact>(0), id -> {
            Bundle bundle = new Bundle();
            bundle.putString(ContactDetailFragment.ARG_ID, id);
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_garden_fragment_to_plant_detail_fragment, bundle);
        });

        binding.rvContactList.setAdapter(mAdapter);
        binding.rvContactList.setHasFixedSize(true);

        binding.filter.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (checkedIds.isEmpty()) {
                mAdapter.getFilter().filter("");
            } else{
                Integer id = checkedIds.get(0);
                Chip chip = group.findViewById(id);
                mAdapter.getFilter().filter(chip.getText());
            }
        });

        contactUseCase.getInitialContacts();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void updateContacts(List<Contact> contactList) {
        mAdapter.updateContacts(contactList);

    }

    @Override
    public void showErrorMessage(String msg) {
        Toast.makeText(getContext(), R.string.text_error, Toast.LENGTH_SHORT).show();
    }
}