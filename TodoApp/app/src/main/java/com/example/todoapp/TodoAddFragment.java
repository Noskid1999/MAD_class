package com.example.todoapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.todoapp.model.ETodo;

public class TodoAddFragment extends Fragment {

    private ETodo etodo;
    private EditText etTitle;
    private EditText etDescription;
    private RadioGroup rgPriority;
    private CalendarView cvDate;
    private Button btnAdd;

    String title, description;
    int priority;
    long date;

    public TodoAddFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
//    public static TodoAddFragment newInstance(String param1, String param2) {
//        TodoAddFragment fragment = new TodoAddFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        etodo = new ETodo();

//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todo_add, container, false);

//        Get Texts
        etTitle = (EditText) view.findViewById(R.id.et_fta_title);
        etDescription = (EditText) view.findViewById(R.id.et_fta_description);

        rgPriority = (RadioGroup) view.findViewById(R.id.rg_fta_priority);

        cvDate = (CalendarView) view.findViewById(R.id.cv_fta_todoDate);

        btnAdd = (Button) view.findViewById(R.id.btn_fta_addTodo);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = etTitle.getText().toString();
                description = etDescription.getText().toString();

                RadioButton rbPriority = (RadioButton) view.findViewById(rgPriority.getCheckedRadioButtonId());

                switch (rbPriority.getText().toString()) {
                    case "High":
                        priority = 1;
                    case "Medium":
                        priority = 2;
                    case "Low":
                        priority = 3;
                }

                date = cvDate.getDate();


            }
        });
        return view;
    }


}