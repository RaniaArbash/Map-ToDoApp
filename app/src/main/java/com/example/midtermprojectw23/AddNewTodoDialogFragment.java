package com.example.midtermprojectw23;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import androidx.fragment.app.DialogFragment;

public class AddNewTodoDialogFragment extends DialogFragment {

    interface DialogFragmentCallBackInterface {
        void addingNewTaskListener(Task todo);
    }

//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        return new AlertDialog.Builder(requireContext())
//                .setMessage(getString(R.string.order_confirmation))
//                .create();
//    }

    public static String TAG = "myFragment";
    DialogFragmentCallBackInterface listener ;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_add_new_to_do, container, false);
        DatePicker datePicker = v.findViewById(R.id.datapicker);

        Button save = v.findViewById(R.id.save_id);
        Switch isurgent = v.findViewById(R.id.isargent_switch);
        EditText taskText = v.findViewById(R.id.task_id);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isa =  isurgent.isChecked()? true: false;
                String taskDate = datePicker.getDayOfMonth()+"/"+datePicker.getMonth()+"/"+datePicker.getYear();
                if (!taskText.getText().toString().isEmpty()) {
                    Task newTodo = new Task(taskText.getText().toString(), taskDate, isa);
                    // passing this todo to main Activity
                    listener.addingNewTaskListener(newTodo);
                    dismiss();
                }
                }
        });

        return v;


    }

    }
