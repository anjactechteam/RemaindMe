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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.remainme.remaindme.Activities.BaseActivity;
import com.example.remainme.remaindme.DataBaseHelper.DataBaseHelper;
import com.example.remainme.remaindme.Activities.MainActivity;
import com.example.remainme.remaindme.R;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewTaskFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewTaskFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView time,date,task_name;
    Button create_task;
    DataBaseHelper myDb;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private OnFragmentInteractionListener mListener;

    public NewTaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory
     * method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewTaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewTaskFragment newInstance(String param1, String param2) {
        NewTaskFragment fragment = new NewTaskFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
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
        myDb = new DataBaseHelper(getContext(),"my_task");
        time.setOnClickListener(this);
        date.setOnClickListener(this);
        create_task.setOnClickListener(this);
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
            if(string_create_task.equals("") ||  string_date_time.equals("") || string_create_task.isEmpty() || string_date_time.isEmpty()) {
                Toast.makeText(getContext(), "Please select date/time/task", Toast.LENGTH_SHORT).show();
                return;
            }else{
                boolean isInserted = myDb.insertData(string_create_task,string_date_time,"Y");
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
