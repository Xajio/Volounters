package ru.unturn.iksa.volountersevent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sPref = getPreferences(MODE_PRIVATE);
        if(!sPref.getString("email_login", "").isEmpty()){
            Intent intent = new Intent(this, EventsActivity.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_login);
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText email = (EditText)findViewById(R.id.loginEmail);
                EditText password = (EditText)findViewById(R.id.loginPassword);
                try {
                    if (RequestsForServer.checkClient(email.getText().toString(), SecurityClass.SHA1(password.getText().toString()))) {
                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putString("email_login", email.getText().toString());
                        ed.commit();
                        Intent intent = new Intent(LoginActivity.this, EventsActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Login or password is not correct", Toast.LENGTH_LONG);
                    }
                }catch (NoSuchAlgorithmException ex){
                    Log.e("Error", ex.getMessage());
                }catch (UnsupportedEncodingException ex){
                    Log.e("Error", ex.getMessage());
                }
            }
        });
        Button registerButton = (Button)findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterUserActivity.class);
                startActivity(intent);
            }
        });
    }
}
