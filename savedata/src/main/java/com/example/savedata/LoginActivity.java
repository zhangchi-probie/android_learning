package com.example.savedata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Button button;
    private EditText username;
    private EditText password;
    private CheckBox rememberMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.user_name);
        password = findViewById(R.id.password);
        button = findViewById(R.id.login_button);
        rememberMe = findViewById(R.id.remember_me);
        preferences = getSharedPreferences("login data",0);
        Boolean isRemember = preferences.getBoolean("remember_password",false);
        if (isRemember){
            String uName = preferences.getString("username","");
            String pWord = preferences.getString("password","");
            username.setText(uName);
            password.setText(pWord);
            rememberMe.setChecked(true);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uName = username.getText().toString();
                String pWord = password.getText().toString();
                if (uName.equals("admin") && pWord.equals("admin")){
                    editor = preferences.edit();
                    if (rememberMe.isChecked()){
                        editor.putString("username",uName);
                        editor.putString("password",pWord);
                        editor.putBoolean("remember_password",true);
                    }else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,"username or password is wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}