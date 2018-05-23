package ru.unturn.iksa.volountersevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class RegisterUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        final Button completeRegisterButton = (Button)findViewById(R.id.completeRegisterButton);
        EditText secondPassword = (EditText)findViewById(R.id.secondPassword);
        secondPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                completeRegisterButton.setText(R.string.registrationButton);
                completeRegisterButton.setEnabled(true);
                if(editable.length() != 0){
                    EditText checkPassword = (EditText)findViewById(R.id.firstPassword);
                    if(!editable.toString().equals(checkPassword.getText().toString())){
                        completeRegisterButton.setText(R.string.invalidPassword);
                        completeRegisterButton.setEnabled(false);
                    }
                }
            }
        });
        completeRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText phone = (EditText)findViewById(R.id.phone);
                EditText firstPassword = (EditText)findViewById(R.id.firstPassword);
                EditText registerEmail = (EditText)findViewById(R.id.registerEmail);
                EditText firstName = (EditText)findViewById(R.id.firstName);
                EditText secondName = (EditText)findViewById(R.id.secondName);
                try {
                    if(RequestsForServer.registerClient(firstName.getText().toString(), secondName.getText().toString(), registerEmail.getText().toString(),
                        SecurityClass.SHA1(firstPassword.getText().toString()), phone.getText().toString())){
                        Intent intent = new Intent(RegisterUserActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }else { //10322376
                        Toast.makeText(RegisterUserActivity.this, "Register failed, try later", Toast.LENGTH_LONG);
                    }

                }catch (NoSuchAlgorithmException ex){

                }catch (UnsupportedEncodingException ex){

                }
            }
        });
    }
}
