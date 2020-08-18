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

public class faculty_adapter extends ArrayAdapter<faculty> {
    private Context context;
    private List<faculty> Faculty;

    public faculty_adapter(Context context,List<faculty> list){
        super(context,R.layout.row_layout1,list);
        this.context=context;
        this.Faculty=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.row_layout1,parent,false);

        TextView tvchar=convertView.findViewById(R.id.t1);
        TextView tvname=convertView.findViewById(R.id.t2);
      // TextView tvemail=convertView.findViewById(R.id.t3);
      //  TextView tvdept=convertView.findViewById(R.id.t4);
    //  TextView tvphone=convertView.findViewById(R.id.t5);

        tvchar.setText(Faculty.get(position).getName().toUpperCase().charAt(0)+"");
        tvname.setText(Faculty.get(position).getName());
    //   tvemail.setText(Faculty.get(position).getEmail());
    //   tvdept.setText(Faculty.get(position).getDepartment());
    // tvphone.setText(Faculty.get(position).getContact());


        return convertView;


    }
}
