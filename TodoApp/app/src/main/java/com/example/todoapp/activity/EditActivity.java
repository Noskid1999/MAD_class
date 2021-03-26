package com.example.todoapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.todoapp.R;
import com.example.todoapp.fragment.TodoAddFragment;

public class EditActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        fragmentManager = getSupportFragmentManager();
        fragment = new TodoAddFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.edit_activity_container, fragment)
                .commit();
    }
}