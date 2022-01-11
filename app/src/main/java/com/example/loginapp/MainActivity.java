package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.loginapp.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private static final String CORRECT_LOGIN = "Fedor";
    private static final String CORRECT_PASSWORD = "123";
    private ActivityMainBinding binding;
    private String login;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.entranceButton.setOnClickListener(v -> {
            getInputData();
            checkDate();
        });
    }
    private void getInputData(){
        login = binding.inputLogin.getText().toString();
        password = binding.inputPassword.getText().toString();
        System.out.println(login);
        System.out.println(password);
    }
    private void showSnackbar(String errorMessage) {
        Snackbar snackbar = Snackbar.make(this, binding.getRoot(), errorMessage, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("CANCEL", v -> clearInputs());
        snackbar.show();
    }
    private void checkDate(){
        if (!login.equals(CORRECT_LOGIN)){
            showSnackbar("Не правильный логин!");
        }
        else if (!password.equals(CORRECT_PASSWORD)){
            showSnackbar("Не правильный пароль!");
        }
        else{
            startSecondActivity();
        }
    }
    private void clearInputs(){
        binding.inputLogin.setText("");
        binding.inputPassword.setText("");
    }
    private void startSecondActivity(){
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("Login", login);
        startActivity(intent);
    }
}
// хух