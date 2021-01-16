package com.example.labolatorium11;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE="message";
    public static final String  FOLDERNAME="MESSAGE";
    public static final String TAG="EDUIB";
    ArrayList<String> arrayList=new ArrayList<>();
    ListView listView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        Intent intent=getIntent();
        String message=intent.getStringExtra(EXTRA_MESSAGE);
        listView=(ListView) findViewById(R.id.listView);

        File myFile= new File(String.valueOf(getExternalFilesDir(FOLDERNAME)));



        String[] pathnames;
        File f = new File(String.valueOf(myFile));
        pathnames = f.list();

        for (String pathname : pathnames) {
            File myExternalFile = new File(getExternalFilesDir(FOLDERNAME), pathname);
            try (FileInputStream is = new FileInputStream(myExternalFile)) {
                byte[] bytes = new byte[2048];
                is.read(bytes);
                is.close();
                String msg = new String(bytes);
                arrayList.add(pathname +" " +msg );
                ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
                listView.setAdapter(arrayAdapter);

            } catch (FileNotFoundException e) {
                Log.e(TAG, e.toString());
            } catch (IOException e) {
                Log.e(TAG, e.toString());
            }

        }

    }
}