package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.example.loginapp.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    private static final int DATA_LOADED_CODE = 0;
    private ActivitySecondBinding binding;
    private ProgressDialog loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadingDialog = createdDialog();
        binding.welcomeText.setText(binding.welcomeText.getText().toString()+ ", " + getIntent().getStringExtra("Login"));
        binding.downloadButton.setOnClickListener(v -> {
            startThread();
            showLoadingDialog();
        });
    }
    private Handler dataHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what == DATA_LOADED_CODE){
                int randNum = (int) msg.obj;
                String strRandNum = String.valueOf(randNum);
                setData(strRandNum);
                hideLoadingDialog();
            }
            else{
                Log.e("MASSAGE", String.valueOf(msg.what));
            }
        }
    };
    private void setData(String text){
        binding.textRand.setText(text);
    }

    private ProgressDialog createdDialog(){
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgress(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setTitle("Loading...");
        dialog.setMessage("Download data");
        return dialog;
    }
    private void showLoadingDialog(){
        loadingDialog.show();
    }
    private void hideLoadingDialog(){
        loadingDialog.hide();
    }
    private void startThread(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                int randNum = 0;
                for(int i = 0; i < 100000; i++){
                    randNum = (int) (Math.random() * 1000);
                    System.out.println(i);
                }
                Message msg = new Message();
                msg.what = DATA_LOADED_CODE;
                msg.obj = randNum;
                dataHandler.sendMessage(msg);
            }
        }.start();
    }
}