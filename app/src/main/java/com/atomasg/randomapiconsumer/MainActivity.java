package com.atomasg.randomapiconsumer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.atomasg.randomapiconsumer.databinding.ActivityMainBinding;
import com.atomasg.randomapiconsumer.ui.fragment.ContactListFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, new ContactListFragment(), ContactListFragment.class.getSimpleName())
                .commit();
    }



}