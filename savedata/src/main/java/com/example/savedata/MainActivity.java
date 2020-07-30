package com.example.savedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private Button getButton;
    private Button jumpButton;
    private EditText editText;
    private EditText shardEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.save_file_text);
        String inputText = load();
        if (!TextUtils.isEmpty(inputText)){
            editText.setText(inputText);
            editText.setSelection(inputText.length());
            Toast.makeText(this,"Read Success",Toast.LENGTH_SHORT).show();
        }

        button = findViewById(R.id.save_shard_preferences);
        button.setOnClickListener(this);
        getButton = findViewById(R.id.get_shard_preferences);
        getButton.setOnClickListener(this);
        jumpButton = findViewById(R.id.jump_to_login);
        jumpButton.setOnClickListener(this);
    }

    public String load(){
        FileInputStream inputStream = null;
        BufferedReader  bufferedReader = null;
        StringBuilder content = new StringBuilder();
        try {
            inputStream =  openFileInput("dataFile");
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = bufferedReader.readLine() )!= null){
                content.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String inputText = editText.getText().toString();
        save(inputText);
    }

    public void save(String string){
        FileOutputStream outputStream = null ;
        BufferedWriter writer = null;
        try {
            outputStream = openFileOutput("dataFile", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write(string);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.save_shard_preferences:
                SharedPreferences.Editor editor = getSharedPreferences("shard data",MODE_PRIVATE).edit();
                Log.e("save","save_shard_preferences is clicked");
                editor.putString("content","this is test for shard preferences");
                editor.apply();
                break;
            case R.id.get_shard_preferences:
                SharedPreferences sharedPreferences = getSharedPreferences("shard data",0);
                String content = sharedPreferences.getString("content","");
                Log.e("get","get_shard_preferences is clicked");
                Log.e("shard data ","shard data is"+ content);
                break;
            case R.id.jump_to_login:
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            default:
                break;
        }
    }
}
