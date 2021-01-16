package com.example.labolatorium11;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {

    public static final String  FOLDERNAME="MESSAGE";
    public static final String TAG="EDUIB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onSendMessage(View view) {


        LocalDate today = LocalDate.now();
        EditText editText = (EditText) findViewById(R.id.editMs);
        String message = editText.getText().toString();
        Intent intent = new Intent(this, ReadActivity.class);
        intent.putExtra(ReadActivity.EXTRA_MESSAGE, message);
        startActivity(intent);

        File myExternalFile= new File(getExternalFilesDir(FOLDERNAME),today.toString()+".txt");
        try(FileOutputStream os=new FileOutputStream(myExternalFile)){
            os.write(message.getBytes());
            os.close();
            Toast.makeText(this,"Save OK",Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            Log.e(TAG,e.toString());
        } catch (IOException e) {
            Log.e(TAG,e.toString());
        }

    }
    }
