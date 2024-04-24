package com.example.musicapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_1;
    private Button btn_2;
    private CheckBox cb1;
    private CheckBox cb2;
    private EditText et1;
    private EditText et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);


        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //从SharedPreferences中获取是否记住密码的参数
        final SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember = preference.getBoolean("cb2", false);
        //设置账号与密码到文本框，并勾选记住密码
        if (isRemember) {
            et1.setText(preference.getString("name", ""));
            et2.setText(preference.getString("password", ""));
            cb2.setChecked(true);
        }
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = et1.getText().toString();
                String psw = et2.getText().toString();
                if (username.equals("文") && psw.equals("123456")) {
                    SharedPreferences.Editor editor = preference.edit();
                    if (cb2.isChecked()) {
                        editor.putBoolean("cb2", true);
                        editor.putString("name", username);
                        editor.putString("password", psw);
                    } else {
                        editor.clear();
                    }
                    editor.apply();
                    Toast.makeText(MainActivity.this, R.string.cg, Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),MusicPlayer.class);
                    intent.putExtra("username",username);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, R.string.sb, Toast.LENGTH_SHORT).show();
                }
            }
        });
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    et2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    et2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

            }
        });

    }


}