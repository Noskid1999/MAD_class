package com.example.todoapp.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.todoapp.R;
import com.example.todoapp.activity.MainActivity;
import com.example.todoapp.database.model.ETodo;
import com.example.todoapp.util.DateConverter;
import com.example.todoapp.viewModel.TodoViewModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoAddFragment extends Fragment {
    View view;

    private ETodo eTodo;
    private EditText etTitle;
    private EditText etDescription;
    private RadioGroup rgPriority;
    private CalendarView cvDate;
    private Button btnAdd;
    private Button btnCancel;
    private CheckBox cbIsComplete;

    String title, description;
    int priority;
    long date;

    int todoId;

    public static final int HIGH_PRIORITY = 1;
    public static final int MEDIUM_PRIORITY = 2;
    public static final int LOW_PRIORITY = 3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        eTodo = new ETodo();
//      Get the eTodoId from the intent if available (i.e. Update eTodo condition)
        todoId = getActivity().getIntent().getIntExtra("eTodoId", -1);

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_todo_add, container, false);

//        Get Texts
        etTitle = (EditText) view.findViewById(R.id.et_fta_title);
        etDescription = (EditText) view.findViewById(R.id.et_fta_description);

        rgPriority = (RadioGroup) view.findViewById(R.id.rg_fta_priority);

        cvDate = (CalendarView) view.findViewById(R.id.cv_fta_todoDate);

        cbIsComplete = (CheckBox) view.findViewById(R.id.cb_fta_isComplete);

        btnAdd = (Button) view.findViewById(R.id.btn_fta_addTodo);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTodo();
            }
        });

        btnCancel = (Button) view.findViewById(R.id.btn_fta_cancelTodo);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertCancel();
            }
        });
        if (todoId != -1) loadUpdateData();
        return view;
    }

    void saveTodo() {
        Date taskDate = new Date();

        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            date = cvDate.getDate();
            taskDate = format.parse(format.format(new Date(date)));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        title = etTitle.getText().toString();
        description = etDescription.getText().toString();


        switch (rgPriority.getCheckedRadioButtonId()) {
            case R.id.rb_fta_priority_high:
                priority = HIGH_PRIORITY;
                break;
            case R.id.rb_fta_priority_medium:
                priority = MEDIUM_PRIORITY;
                break;
            case R.id.rb_fta_priority_low:
                priority = LOW_PRIORITY;
                break;
        }

        eTodo.setTitle(title);
        eTodo.setDescription(description);
        eTodo.setTaskDate(taskDate);
        eTodo.setPriority(priority);
        eTodo.setStatus(cbIsComplete.isChecked() ? 1 : 0);


        TodoViewModel viewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        viewModel.insert(eTodo);

        Toast.makeText(getActivity(), "Todo Task Saved", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);

    }

    void loadUpdateData() {
        TodoViewModel viewModel = new ViewModelProvider(this).get(TodoViewModel.class);
//      Check if the id is present
        if (todoId == -1) return;
        btnAdd.setText("Update");

        eTodo = viewModel.get(todoId);

        etTitle.setText(eTodo.getTitle());
        etDescription.setText(eTodo.getDescription());
        cvDate.setDate(DateConverter.toTimeStamp(eTodo.getTaskDate()));
        switch (eTodo.getPriority()) {
            case HIGH_PRIORITY:
                rgPriority.check(R.id.rb_fta_priority_high);
                break;
            case MEDIUM_PRIORITY:
                rgPriority.check(R.id.rb_fta_priority_medium);
                break;
            case LOW_PRIORITY:
                rgPriority.check(R.id.rb_fta_priority_low);
                break;
        }
        cbIsComplete.setChecked(eTodo.getStatus() == 1 ? true : false);
    }

    void showAlertCancel() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setMessage(getString(R.string.alert_fta_cancel))
                .setTitle(getString(R.string.app_name))
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton(getString(R.string.btn_fta_alert_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(getString(R.string.btn_fta_alert_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        alertDialog.show();
    }


}