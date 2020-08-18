package com.example.college_attendence;

import android.app.Application;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

import java.util.List;

public class Startapplication extends Application {

    public static final String APPLICATION_ID = "B7CEC0D7-7BB0-00D2-FFCD-A3E4F0740900";
    public static final String API_KEY = "113A6C6B-0EA2-475C-B4B2-E1C4643361AA";
    public static final String SERVER_URL = "https://api.backendless.com";
public  static List<student> students;
public static  List<faculty> faculties;
public static  BackendlessUser user;
public static List<student1> student1;

    @Override
    public void onCreate() {
        super.onCreate();

        Backendless.setUrl(SERVER_URL );
        Backendless.initApp( getApplicationContext(),
                APPLICATION_ID,
                API_KEY );

    }
}
