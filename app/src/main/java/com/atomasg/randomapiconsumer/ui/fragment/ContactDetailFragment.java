package com.atomasg.randomapiconsumer.ui.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.atomasg.randomapiconsumer.MainActivity;
import com.atomasg.randomapiconsumer.databinding.FragmentContactDetailBinding;
import com.atomasg.randomapiconsumer.model.Contact;
import com.atomasg.randomapiconsumer.usecase.ContactsUseCase;
import com.atomasg.randomapiconsumer.usecase.ContactsUseCaseImpl;
import com.atomasg.randomapiconsumer.usecase.DownloadAndSaveImageUseCase;
import com.atomasg.randomapiconsumer.usecase.DownloadAndSaveImageUseCaseImpl;
import com.squareup.picasso.Picasso;

import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactDetailFragment extends Fragment {

    public static final String ARG_ID = "ARG_ID";
    private final ActivityResultLauncher<String[]> activityResultLauncher;

    private String id;
    private ContactsUseCase contactUseCase;
    private FragmentContactDetailBinding binding;
    private Contact contact;
    private DownloadAndSaveImageUseCase downloadAndSaveImageUseCase;

    public ContactDetailFragment() {
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
            @Override
            public void onActivityResult(Map<String, Boolean> result) {
                Log.e("activityResultLauncher", "" + result.toString());
                Boolean areAllGranted = true;
                for (Boolean b : result.values()) {
                    areAllGranted = areAllGranted && b;
                }

                if (areAllGranted) {
                    if (result.containsKey(Manifest.permission.CALL_PHONE)) {
                        ((MainActivity) getActivity()).callPhone(contact.getPhone());
                    }
                    if (result.containsKey(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        downloadImage(contact.getPicture().getLarge());
                    }
                }
            }
        });
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getString(ARG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentContactDetailBinding.inflate(inflater, container, false);
        if (getArguments() != null) {
            getArguments().getString("contactId");
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contactUseCase = new ContactsUseCaseImpl(null);
        loadData();
    }

    private void loadData() {
        this.contact = contactUseCase.getDetail(id);
        binding.tvGenre.setText(contact.getGender());
        binding.tvName.setText(contact.getName().toString());
        binding.tvUsername.setText(contact.getLogin().getUsername());
        binding.tvEmail.setText(contact.getEmail());
        binding.tvEmail.setOnClickListener(v -> ((MainActivity) getActivity()).sendEmail(contact.getEmail()));
        binding.tvPhone.setText(contact.getPhone());
        binding.tvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    ((MainActivity) getActivity()).callPhone(contact.getPhone());
                } else {
                    String[] appPerms;
                    appPerms = new String[]{Manifest.permission.CALL_PHONE};
                    activityResultLauncher.launch(appPerms);
                }
            }
        });
        binding.tvAddress.setText(contact.getLocation().toString());
        Picasso.get().load(contact.getPicture().getLarge()).into(binding.imageView);
        binding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    downloadImage(contact.getPicture().getLarge());
                } else {
                    String[] appPerms;
                    appPerms = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    activityResultLauncher.launch(appPerms);
                }
            }
        });

    }

    private void downloadImage(String urlImg) {
        downloadAndSaveImageUseCase = new DownloadAndSaveImageUseCaseImpl(getContext());
        downloadAndSaveImageUseCase.downloadImage(urlImg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        contact = null;
    }
}