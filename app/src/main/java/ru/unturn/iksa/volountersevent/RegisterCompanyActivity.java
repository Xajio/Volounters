package ru.unturn.iksa.volountersevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class RegisterCompanyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_company);
        final Button completeRegisterButton = (Button)findViewById(R.id.completeRegisterCompanyButton);
        EditText secondPassword = (EditText)findViewById(R.id.registerCompanySecondPassword);
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
                    EditText checkPassword = (EditText)findViewById(R.id.registerCompanyFirstPassword);
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
                EditText phone = (EditText)findViewById(R.id.registerCompanyPhone);
                EditText firstPassword = (EditText)findViewById(R.id.registerCompanyFirstPassword);
                EditText registerEmail = (EditText)findViewById(R.id.registerCompanyEmail);
                EditText firstName = (EditText)findViewById(R.id.registerCompanyFirstName);
                EditText secondName = (EditText)findViewById(R.id.registerCompanySecondName);
                EditText Inn = (EditText)findViewById(R.id.registerCompanyINN);
                try {
                    if(RequestsForServer.registerCompany(firstName.getText().toString(), secondName.getText().toString(), registerEmail.getText().toString(),
                            SecurityClass.SHA1(firstPassword.getText().toString()), phone.getText().toString(), Inn.getText().toString())){
                        Intent intent = new Intent(RegisterCompanyActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(RegisterCompanyActivity.this, "Register failed, try later", Toast.LENGTH_LONG);
                    }

                }catch (NoSuchAlgorithmException ex){

                }catch (UnsupportedEncodingException ex){

                }
            }
        });
    }
}
