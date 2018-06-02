package com.luis_santiago.persistenciadedatos;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    private Button saveButton;
    private Button buttonRead;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveButton = findViewById(R.id.save_button);
        buttonRead = findViewById(R.id.read_button);
        textView = findViewById(R.id.text);

        saveButton.setOnClickListener(View->{
            String name = "myFile";
            String content = "Esto es el prime content , contiene caracteres";
            FileOutputStream outputStream;
            try {
                outputStream = openFileOutput(name, Context.MODE_PRIVATE);
                outputStream.write(content.getBytes());
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        buttonRead.setOnClickListener(View->{
            try {
                FileInputStream fis = getApplicationContext().openFileInput("myFile");
                InputStreamReader inputStreamReader = new InputStreamReader(fis , "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line).append("\n");
                }

                textView.setText(stringBuilder.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


//        button_save = findViewById(R.id.button_save);
//        edit_number = findViewById(R.id.edit_number);
//
//        SharedPreferences sharedPreferences = getSharedPreferences("test" , Context.MODE_PRIVATE);
//        String name = sharedPreferences.getString("hi" , "NOT FOUND");
//        edit_number.setText(name);
//
//
//        button_save.setOnClickListener(View->{
//            Toast.makeText(MainActivity.this , "Hello" , Toast.LENGTH_SHORT).show();
//
//            SharedPreferences shared = getSharedPreferences("test" , Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = shared.edit();
//            editor.putString("hi" , edit_number.getText().toString());
//            editor.apply();
//        });
    }
}
