package com.example.musicapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MusicPlayer extends AppCompatActivity {



    private TextView tv1;
    private TextView tv2;
    private Spinner sp1;
    private ListView lv1;
    private Button btn_bf;
    private Button btn_zt;
    private MediaPlayer mc1;
    private MediaPlayer mc2;
    private MediaPlayer mc3;


    private String[] string_arr={"义勇军进行曲（合唱）- 中国人民解放军军乐团","喜欢你 - 邓紫棋","Wish You Hell - Wendy"};
    private String[] string_picture={String.valueOf(R.drawable.mc1), String.valueOf(R.drawable.mc2), String.valueOf(R.drawable.mc3)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        sp1 = findViewById(R.id.sp1);
        lv1 = findViewById(R.id.lv1);
        btn_bf = findViewById(R.id.btn_bf);
        btn_zt = findViewById(R.id.btn_zt);

        sp1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (getResources().getStringArray(R.array.bg_arr)[position] == "bg1") {
                    view.setBackgroundResource(R.drawable.bg1);
                }
                if (getResources().getStringArray(R.array.bg_arr)[position] == "bg2") {
                    view.setBackgroundResource(R.drawable.bg3);
                }
                if (getResources().getStringArray(R.array.bg_arr)[position] == "bg3"){
                    view.setBackgroundResource(R.drawable.bg2);
                }
            }
        });

        lv1.setAdapter(new MyAdapter(getApplicationContext()));
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv2.setText(R.string.zzbf+string_arr[position]);
            }
        });

        mc1= MediaPlayer.create(getApplicationContext(),R.raw.mc1);
        mc2= MediaPlayer.create(getApplicationContext(),R.raw.mc2);
        mc3= MediaPlayer.create(getApplicationContext(),R.raw.mc3);

        btn_bf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv2.getText().equals(R.string.zzbf+"义勇军进行曲（合唱）- 中国人民解放军军乐团"))
                {mc1.start();}
                if (tv2.getText().equals(R.string.zzbf+"喜欢你 - 邓紫棋"))
                {mc2.start();}
                if (tv2.getText().equals(R.string.zzbf+"Wish You Hell - Wendy"))
                {mc3.start();}
                else {
                    Toast.makeText(MusicPlayer.this, R.string.bfsb, Toast.LENGTH_SHORT).show();
                }

            }
        });
        btn_zt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv2.getText().equals(R.string.zzbf+"义勇军进行曲（合唱）- 中国人民解放军军乐团"))
                {mc1.pause();}
                if (tv2.getText().equals(R.string.zzbf+"喜欢你 - 邓紫棋"))
                {mc2.pause();}
                if (tv2.getText().equals(R.string.zzbf+"Wish You Hell - Wendy"))
                {mc3.pause();}
                else {
                    Toast.makeText(MusicPlayer.this, R.string.bfsb, Toast.LENGTH_SHORT).show();
                }

            }
        });

        //登录成功后，用户名显示
        String username = getIntent().getStringExtra("username");
        tv1.setText(username + R.string.music2);

    }

    //列表变化
    private  class MyAdapter extends BaseAdapter {

        private Context context;
        MyAdapter(Context context){this.context=context;}
        @Override
        public int getCount() {
            return string_arr.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View v, ViewGroup parent) {

            if (position%2==0){
                v= LayoutInflater.from(context).inflate(R.layout.itemview1,null);
            }else {
                v=LayoutInflater.from(context).inflate(R.layout.itemview2,null);
            }
            TextView tvv1=v.findViewById(R.id.tvv1);
            ImageView ivv=v.findViewById(R.id.ivv);
            tvv1.setText(string_arr[position]);
            ivv.setImageResource(Integer.parseInt(string_picture[position]));

            return v;
        }
    }
}