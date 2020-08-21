package com.example.remainme.remaindme.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.remainme.remaindme.Activities.BaseActivity;
import com.example.remainme.remaindme.DataBaseHelper.DataBaseHelper;
import com.example.remainme.remaindme.Activities.MainActivity;
import com.example.remainme.remaindme.R;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


public class NewTaskFragment extends Fragment implements View.OnClickListener{

    private String mParam3="",mParam2="",mParam1="",sid="";
    private boolean isupdate=false;
    TextView time,date,task_name;
    Button create_task,update_task;
    EditText id;
    DataBaseHelper myDb;
    Switch reminder;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private OnFragmentInteractionListener mListener;

    public NewTaskFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString("title");
            mParam2 = getArguments().getString("schedule");
            mParam3 = getArguments().getString("datetime");
            isupdate= getArguments().getBoolean("isupdate");
            sid     = getArguments().getString("id");
            Log.e(">>>>>>mparam",mParam1+mParam2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewroot= inflater.inflate(R.layout.fragment_new_task, container, false);
        time = viewroot.findViewById(R.id.time);
        date = viewroot.findViewById(R.id.date);
        task_name =viewroot.findViewById(R.id.task_name);
        create_task = viewroot.findViewById(R.id.create_new_task);
        update_task = viewroot.findViewById(R.id.update_new_task);
        reminder    = viewroot.findViewById(R.id.remindme_switch);
        id= viewroot.findViewById(R.id.task_id);
        id.setVisibility(View.GONE);
        myDb = new DataBaseHelper(getContext(),"my_task");
        if (isupdate){
            create_task.setVisibility(View.GONE);
            update_task.setVisibility(View.VISIBLE);
            if (!mParam1.isEmpty())
                task_name.setText(mParam1);
            if (!mParam3.isEmpty()){
                String[] str =mParam3.split(" ");
                date.setText(str[0]);
                time.setText(str[1]);
            }
            if(mParam2.equals("Y"))
                reminder.setChecked(true);
            else
                reminder.setChecked(false);
            id.setText(sid);
        }else{
            create_task.setVisibility(View.VISIBLE);
            update_task.setVisibility(View.GONE);
        }

        time.setOnClickListener(this);
        date.setOnClickListener(this);
        create_task.setOnClickListener(this);
        update_task.setOnClickListener(this);
        return viewroot;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        if(view==time){
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            time.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        } // crash da same errror
        if(view==date){
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            String[] months ={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
                            date.setText(dayOfMonth + "/" + months[monthOfYear] + "/" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if(view==create_task){
            String string_create_task= task_name.getText().toString();
            String string_date_time  = date.getText().toString()+" "+time.getText().toString();
            String remindme="";
            if (reminder.isChecked())
                remindme="Y";
            else
                remindme="N";

            if(string_create_task.equals("") ||  string_date_time.equals("") || string_create_task.isEmpty() || string_date_time.isEmpty()) {
                Toast.makeText(getContext(), "Please select date/time/task", Toast.LENGTH_SHORT).show();
                return;
            }else{
                boolean isInserted = myDb.insertData(string_create_task,string_date_time,remindme);
                if(isInserted == true) {
                    task_name.setText("");
                    date.setText("");
                    time.setText("");
                    Intent refresh = new Intent(getContext(), BaseActivity.class);
                    startActivity(refresh);
                    getActivity().finish();
                    Toast.makeText(getContext(),"Data Inserted",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getContext(),"Data not Inserted",Toast.LENGTH_LONG).show();
            }

        }
        if(view==update_task){
            String string_create_task= task_name.getText().toString();
            String string_date_time  = date.getText().toString()+" "+time.getText().toString();
            String ids=id.getText().toString();
            String remindme="";
            if (reminder.isChecked())
                remindme="Y";
            else
                remindme="N";
            Log.e(">>>data_update",string_date_time+string_create_task+ids);
            if (ids.isEmpty()){
                Toast.makeText(getContext(),"This Schedule Never be Edited nor be deleted",Toast.LENGTH_SHORT).show();
            }else{
                boolean isUpdated = myDb.updateData(ids,string_create_task,remindme,string_date_time);
                if (isUpdated){
                    Intent refresh = new Intent(getContext(), BaseActivity.class);
                    startActivity(refresh);
                    getActivity().finish();
                    Toast.makeText(getContext(),"Data Update",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getContext(),"Data Not Update",Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
