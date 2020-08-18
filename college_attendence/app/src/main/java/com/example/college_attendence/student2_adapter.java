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

public class student2_adapter extends ArrayAdapter<student1> {
    private Context context;
    private List<student1> studentlist;

    public student2_adapter(Context context, List<student1> list) {
        super(context, R.layout.row_edit, list);
        this.context = context;
        this.studentlist = list;


    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row_edit, parent, false);

        TextView tvchar = convertView.findViewById(R.id.t1);
        TextView tvname = convertView.findViewById(R.id.t2);
        TextView tvusn = convertView.findViewById(R.id.t3);

        tvchar.setText(studentlist.get(position).getFname().toUpperCase().charAt(0) + "");
        tvname.setText(studentlist.get(position).getFname() + studentlist.get(position).getLname());
        tvusn.setText(studentlist.get(position).getUsn());
        return convertView;

    }
}