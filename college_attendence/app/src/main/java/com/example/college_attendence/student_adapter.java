package com.example.college_attendence;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class student_adapter extends ArrayAdapter<student> {
    private Context context;
    private List<student> Student;

    public student_adapter(Context context,List<student> list){
        super(context,R.layout.row_layout,list);
        this.context=context;
        this.Student=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.row_layout,parent,false);

        TextView tvchar=convertView.findViewById(R.id.t1);
        TextView tvname=convertView.findViewById(R.id.t2);
        TextView tvdept=convertView.findViewById(R.id.t3);
        TextView tvphone=convertView.findViewById(R.id.t4);
        TextView tvyear=convertView.findViewById(R.id.t5);

        tvchar.setText(Student.get(position).getFname().toUpperCase().charAt(0)+"");
        tvname.setText(Student.get(position).getFname()+Student.get(position).getLname());
        tvdept.setText(Student.get(position).getDept());
        tvphone.setText(Student.get(position).getUsn());
        tvyear.setText(Student.get(position).getYear());


        return convertView;


    }
}
