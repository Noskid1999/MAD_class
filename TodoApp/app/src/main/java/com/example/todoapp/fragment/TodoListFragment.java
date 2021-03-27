package com.example.todoapp.fragment;

import android.graphics.Canvas;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.todoapp.R;
import com.example.todoapp.adapter.TodoAdapter;
import com.example.todoapp.controller.SwipeController;
import com.example.todoapp.controller.SwipeControllerActions;
import com.example.todoapp.database.model.ETodo;
import com.example.todoapp.viewModel.TodoViewModel;

import java.util.List;


public class TodoListFragment extends Fragment {
    RecyclerView rvTodoList;
    TodoViewModel viewModel;
    View rootView;
    TodoAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_todo_list, container, false);
        rvTodoList = rootView.findViewById(R.id.rv_ftl_todoListContainer);
        viewModel = new ViewModelProvider(this).get(TodoViewModel.class);

        SwipeController swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onLeftClicked(int position) {
                super.onLeftClicked(position);
                ETodo eTodo = viewModel.getAllTodos().getValue().get(position);
                eTodo.setStatus(1);

                viewModel.update(eTodo);

            }

            @Override
            public void onRightClicked(int position) {
                super.onRightClicked(position);
                Toast.makeText(rootView.getContext(), "Button Clicked Right: " + position, Toast.LENGTH_SHORT).show();
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeController);
        itemTouchHelper.attachToRecyclerView(rvTodoList);

        rvTodoList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager((getActivity()));
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvTodoList.setLayoutManager(llm);

//      Set to do list change observer
        viewModel.getAllTodos().observe(getViewLifecycleOwner(), new Observer<List<ETodo>>() {
            @Override
            public void onChanged(List<ETodo> todoList) {
                adapter = new TodoAdapter(todoList);
                rvTodoList.setAdapter(adapter);
            }
        });
        return rootView;

    }

}