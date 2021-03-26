package com.example.todoapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.todoapp.R;
import com.example.todoapp.database.model.ETodo;
import com.example.todoapp.viewModel.TodoViewModel;

import java.util.ArrayList;
import java.util.HashMap;

//TODO: Get the list of todo tasks
public class TodoFragment extends ListFragment {
    RecyclerView rvTodoList;
    TodoViewModel viewModel;
    View rootView;
    public final Integer PRIORITY_HIGH = 1, PRIORITY_MEDIUM = 2, PRIORITY_LOW = 3;

    public HashMap<Integer, String> priorityStringMap;
    public HashMap<Integer, Integer> priorityColorMap;

    ETodo[] todoList;

    ArrayList<HashMap<String, String>> data = new ArrayList<>();
    SimpleAdapter adapter;

    public TodoFragment() {
        priorityStringMap.put(PRIORITY_HIGH, "High");
        priorityStringMap.put(PRIORITY_MEDIUM, "Medium");
        priorityStringMap.put(PRIORITY_LOW, "Low");


        priorityColorMap.put(PRIORITY_HIGH, R.color.danger);
        priorityColorMap.put(PRIORITY_MEDIUM, R.color.warning);
        priorityColorMap.put(PRIORITY_LOW, R.color.success);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.list_item_todo, container, false);
        rvTodoList = rootView.findViewById(R.id.rv_ftl_todoListContainer);
        viewModel = new ViewModelProvider(this).get(TodoViewModel.class);

        LinearLayoutManager llm = new LinearLayoutManager((getActivity()));
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvTodoList.setLayoutManager(llm);

        return rootView;

//        HashMap<String, String> map = new HashMap<>();
//
//        for (int i = 0; i < todoList.length; i++) {
//            map = new HashMap<>();
//            map.put("title", todoList[i].getTitle());
//            map.put("priority", priorityStringMap.get(todoList[i].getPriority()));
//
//            data.add(map);
//        }
//
//        String[] from = {"title", "priority"};
//        int[] to = {R.id.tv_ft_taskTitle, R.id.tv_ft_taskPriority};
//
//        adapter = new SimpleAdapter(getActivity(), data, R.layout.list_item_todo, from, to);
//        setListAdapter(adapter);
//
//
//        return super.onCreateView(inflater, container, savedInstanceState);
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