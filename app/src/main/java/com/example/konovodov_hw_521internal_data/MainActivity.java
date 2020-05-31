package com.example.konovodov_hw_521internal_data;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText mLoginEdTxt;
    private EditText mPassEdTxt;
    private FileInputStream fileInputStream;
    private StringBuffer loginFromFile;
    private StringBuffer passFromFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoginEdTxt = findViewById(R.id.editLogin);
        mPassEdTxt = findViewById(R.id.editPassword);
        Button mEnterBtn = findViewById(R.id.enterBtn);
        Button mRegBtn = findViewById(R.id.regBtn);

        mEnterBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if ((mLoginEdTxt.getText().length() == 0) || (mPassEdTxt.getText().length() == 0)) {
                    Toast.makeText(MainActivity.this, "Введите логин и пароль для входа", Toast.LENGTH_SHORT).show();
                } else {
                    //работаем...

                    try {
                        fileInputStream = openFileInput("file_name");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    try {
                        loginFromFile = new StringBuffer(reader.readLine());
                        passFromFile = new StringBuffer(reader.readLine());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (loginFromFile.toString().equals(mLoginEdTxt.getText().toString())) {
                        if (passFromFile.toString().equals(mPassEdTxt.getText().toString())) {
                            Toast.makeText(MainActivity.this, "УСПЕХ!!!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Пароль введен не верно", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Логин введен не верно", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        mRegBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if ((mLoginEdTxt.getText().length() == 0) || (mPassEdTxt.getText().length() == 0)) {
                    Toast.makeText(MainActivity.this, "Введите логин и пароль для регистрации", Toast.LENGTH_SHORT).show();
                } else {
                    //работаем...

                    FileOutputStream fileOutputStream = null;
                    try {
                        fileOutputStream = openFileOutput("file_name", MODE_PRIVATE);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                    BufferedWriter bw = new BufferedWriter(outputStreamWriter);
                    try {
                        bw.write(mLoginEdTxt.getText().toString() + "\n");
                        bw.write(mPassEdTxt.getText().toString());
                        Toast.makeText(MainActivity.this, "Данные сохранены", Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        bw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}
