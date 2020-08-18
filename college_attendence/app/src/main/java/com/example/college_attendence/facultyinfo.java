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

public class facultyinfo extends AppCompatActivity {

    Boolean edit=false;
    TextView t1,t2;
    EditText e1,e2,e3;
    ImageView iedit,idelete;
    Button submit;
    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultyinfo);

        setContentView(R.layout.activity_facultyinfo);
        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2);
        e1=(EditText)findViewById(R.id.e1);
        e2=(EditText)findViewById(R.id.e2);
        e3=(EditText)findViewById(R.id.e3);
        iedit=(ImageView)findViewById(R.id.i1);
        idelete=(ImageView)findViewById(R.id.i2);
        submit=(Button)findViewById(R.id.b1);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        e1.setVisibility(View.GONE);
        e2.setVisibility(View.GONE);
        e3.setVisibility(View.GONE);
        submit.setVisibility(View.GONE);

        final int index=getIntent().getIntExtra("index",0);

        e1.setText(Startapplication.faculties.get(index).getName());
        e2.setText(Startapplication.faculties.get(index).getEmail());
        e3.setText(Startapplication.faculties.get(index).getContact());

        t1.setText(Startapplication.faculties.get(index).getName().toUpperCase().charAt(0)+"");
        t2.setText(Startapplication.faculties.get(index).getName());




        iedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit=!edit;
                if(edit){
                    e1.setVisibility(View.VISIBLE);
                    e2.setVisibility(View.VISIBLE);
                    e3.setVisibility(View.VISIBLE);
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


                final AlertDialog.Builder alert=new AlertDialog.Builder(facultyinfo.this);
                alert.setMessage("Are you sure you want to delete the faculty?");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        showProgress(true);
                        Backendless.Persistence.of(faculty.class).remove(Startapplication.faculties.get(index),
                                new AsyncCallback<Long>() {
                                    @Override
                                    public void handleResponse(Long response) {
                                        Startapplication.students.remove(index);
                                        Toast.makeText(facultyinfo.this, "faculty deleted successfully", Toast.LENGTH_SHORT).show();
                                        setResult(RESULT_OK);
                                        facultyinfo.this.finish();

                                    }

                                    @Override
                                    public void handleFault(BackendlessFault fault) {

                                        Toast.makeText(facultyinfo.this, "error!!"+fault.getMessage(), Toast.LENGTH_SHORT).show();
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

                if(e1.getText().toString().isEmpty() || e2.getText().toString().isEmpty()  || e3.getText().toString().isEmpty()){
                    Toast.makeText(facultyinfo.this, "please fill all the details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Startapplication.faculties.get(index).setName(e1.getText().toString());
                    Startapplication.faculties.get(index).setEmail(e2.getText().toString());
                    Startapplication.faculties.get(index).setContact(e3.getText().toString());
                    showProgress(true);
                    Backendless.Persistence.save(Startapplication.faculties.get(index), new AsyncCallback<faculty>() {
                        @Override
                        public void handleResponse(faculty response) {
                            t1.setText(Startapplication.faculties.get(index).getName().toUpperCase().charAt(0)+"");
                            t2.setText(Startapplication.faculties.get(index).getName());
                            Toast.makeText(facultyinfo.this, "Faculty details updated", Toast.LENGTH_SHORT).show();
                            showProgress(false);
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {

                            Toast.makeText(facultyinfo.this, "error!!"+fault.getMessage(), Toast.LENGTH_SHORT).show();
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
