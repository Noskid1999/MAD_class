package com.example.todoapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.todoapp.R;
import com.example.todoapp.database.model.ETodo;

import java.util.ArrayList;
import java.util.HashMap;
//TODO: Get the list of todo tasks
public class TodoFragment extends ListFragment {
    public final Integer PRIORITY_HIGH = 1, PRIORITY_MEDIUM = 2, PRIORITY_LOW = 3;

    public HashMap<Integer, String> priorityStringMap;
    public HashMap<Integer, String> priorityColorMap;

    ETodo[] todoList;

    ArrayList<HashMap<String, String>> data = new ArrayList<>();
    SimpleAdapter adapter;

    public TodoFragment() {
        priorityStringMap.put(PRIORITY_HIGH, "High");
        priorityStringMap.put(PRIORITY_MEDIUM, "Medium");
        priorityStringMap.put(PRIORITY_LOW, "Low");


        priorityColorMap.put(PRIORITY_HIGH, "#DC3545");
        priorityColorMap.put(PRIORITY_MEDIUM, "#FFC107");
        priorityColorMap.put(PRIORITY_LOW, "#28A745");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        HashMap<String, String> map = new HashMap<>();

        for (int i = 0; i < todoList.length; i++) {
            map = new HashMap<>();
            map.put("title", todoList[i].getTitle());
            map.put("priority", priorityStringMap.get(todoList[i].getPriority()));

            data.add(map);
        }

        String[] from = {"title", "priority"};
        int[] to = {R.id.tv_ft_taskTitle, R.id.tv_ft_taskPriority};

        adapter = new SimpleAdapter(getActivity(), data, R.layout.fragment_todo, from, to);
        setListAdapter(adapter);


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {

        super.onStart();

        getListView().setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), data.get(position).get("title"), Toast.LENGTH_SHORT).show();
            }
        });
    }
}