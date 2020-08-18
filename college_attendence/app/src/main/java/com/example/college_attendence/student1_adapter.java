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

public class student1_adapter extends ArrayAdapter<student1> {

    private Context context;
    private List<student1> Student;

    public student1_adapter(Context context,List<student1> list){
        super(context,R.layout.row_attendance,list);
        this.context=context;
        this.Student=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.row_attendance,parent,false);

        TextView tvchar=convertView.findViewById(R.id.t1);
        TextView tvname=convertView.findViewById(R.id.t2);
        TextView tvusn=convertView.findViewById(R.id.t3);

        tvchar.setText(Student.get(position).getFname().toUpperCase().charAt(0)+"");
        tvname.setText(Student.get(position).getFname()+Student.get(position).getLname());
        tvusn.setText(Student.get(position).getUsn());
        return convertView;



    }
}
