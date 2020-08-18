package com.example.college_attendence;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class edit_info extends AppCompatActivity {


    TextView t1,t2;
    EditText e1,e2,e3,e4;
    ImageView iedit,idelete;
    Button submit;
    Boolean edit=true;
    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);


        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);
        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2);
        e1=(EditText)findViewById(R.id.e1);
        e2=(EditText)findViewById(R.id.e2);
        e3=(EditText)findViewById(R.id.e3);
        e4=(EditText)findViewById(R.id.e4);
        iedit=(ImageView)findViewById(R.id.i1);
        idelete=(ImageView)findViewById(R.id.i2);
        submit=(Button)findViewById(R.id.b1);


        e1.setVisibility(View.GONE);
        e2.setVisibility(View.GONE);
        e3.setVisibility(View.GONE);

        submit.setVisibility(View.GONE);

        final int index=getIntent().getIntExtra("index",0);

        e1.setText(Startapplication.student1.get(index).getFname());
        e2.setText(Startapplication.student1.get(index).getLname());
        e3.setText(Startapplication.student1.get(index).getUsn());
        int present=Startapplication.student1.get(index).getPresent();
        e4.setText(String.valueOf(present));

        t1.setText(Startapplication.student1.get(index).getFname().toUpperCase().charAt(0)+"");
        t2.setText(Startapplication.student1.get(index).getFname()+Startapplication.student1.get(index).getLname());



        iedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit=!edit;
                if(edit){
                    e1.setVisibility(View.VISIBLE);
                    e2.setVisibility(View.VISIBLE);
                    e3.setVisibility(View.VISIBLE);
                    e4.setVisibility(View.VISIBLE);
                    submit.setVisibility(View.VISIBLE);
                }
                else
                {
                    e1.setVisibility(View.GONE);
                    e2.setVisibility(View.GONE);
                    e3.setVisibility(View.GONE);

                    submit.setVisibility(View.GONE);
                }

            }
        });



        idelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alert=new AlertDialog.Builder(edit_info.this);
                alert.setMessage("Are you sure you want to delete the student?");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        showProgress(true);
                        Backendless.Persistence.of(student1.class).remove(Startapplication.student1.get(index),
                                new AsyncCallback<Long>() {
                                    @Override
                                    public void handleResponse(Long response) {
                                        Startapplication.student1.remove(index);
                                        Toast.makeText(edit_info.this, "student deleted successfully", Toast.LENGTH_SHORT).show();
                                        setResult(RESULT_OK);
                                        edit_info.this.finish();

                                    }

                                    @Override
                                    public void handleFault(BackendlessFault fault) {

                                        Toast.makeText(edit_info.this, "error!!"+fault.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });

                alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alert.show();


            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(e1.getText().toString().isEmpty() || e2.getText().toString().isEmpty() || e3.getText().toString().isEmpty()){
                    Toast.makeText(edit_info.this, "please fill all the details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Startapplication.student1.get(index).setFname(e1.getText().toString());
                    Startapplication.student1.get(index).setLname(e2.getText().toString());
                    Startapplication.student1.get(index).setUsn(e3.getText().toString());
                   int a=Integer.parseInt(e4.getText().toString());
                    Startapplication.student1.get(index).setPresent(a);
                    showProgress(true);

                    Backendless.Persistence.save(Startapplication.student1.get(index), new AsyncCallback<student1>() {
                        @Override
                        public void handleResponse(student1 response) {
                            t1.setText(Startapplication.student1.get(index).getFname().charAt(0)+"");
                            t2.setText(Startapplication.student1.get(index).getFname()+Startapplication.student1.get(index).getLname());

                            Toast.makeText(edit_info.this, "Students details updated successfully!!", Toast.LENGTH_SHORT).show();
                            showProgress(false);
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {

                            Toast.makeText(edit_info.this, "error!!"+fault.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });






    }
    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });

            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}
