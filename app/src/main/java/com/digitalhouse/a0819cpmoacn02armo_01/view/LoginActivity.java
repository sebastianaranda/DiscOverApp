package com.digitalhouse.a0819cpmoacn02armo_01.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Arrays;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private MaterialButton materialButton;
    private TextInputLayout txtInputLayoutEmail;
    private TextInputEditText txtInputEditTextEmail;
    private TextInputLayout txtInputLayoutPassword;
    private TextInputEditText txtInputEditTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = findViewById(R.id.toolbar_login_activity);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        materialButton = findViewById(R.id.login_activity_material_button);
        txtInputLayoutEmail = findViewById(R.id.login_activity_text_input_layout_email);
        txtInputEditTextEmail = findViewById(R.id.login_activity_text_input_edit_text_email);
        txtInputLayoutPassword = findViewById(R.id.login_activity_text_input_layout_password);
        txtInputEditTextPassword = findViewById(R.id.login_activity_text_input_edit_text_password);

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkForm()){
                    //TODO: sacar toast despues de definir la logica del boton
                    Toast.makeText(LoginActivity.this, "Felicitaciones, creaste tu cuenta", Toast.LENGTH_SHORT).show();
                }
            }
        });


        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button_fb);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                startActivity(new Intent(LoginActivity.this,UserProfileActivity.class));
            }

            @Override
            public void onCancel() {
                //TODO: borrar esta linea cuando se defina el comportamiento
                Toast.makeText(LoginActivity.this, "Login cancelado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                //TODO: borrar esta linea cuando se defina el comportamiento
                Toast.makeText(LoginActivity.this, "Login con error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    //TODO: modificar los siguientes metodos despues de definir la logica de la creacion de la cuenta
    public Boolean checkEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    public Boolean checkForm(){
        Boolean verification = true;
        String email = txtInputEditTextEmail.getText().toString();
        String password = txtInputEditTextPassword.getText().toString();

        if (email.isEmpty()){
            txtInputLayoutEmail.setError(getString(R.string.txt_login_layout_error_empty_email));
            txtInputEditTextEmail.setHint(getString(R.string.txt_login_edit_text_error_empty_email));
            verification = false;
        }else {
            if (checkEmail(email)==false){
                txtInputLayoutEmail.setError(getString(R.string.txt_login_layout_error_wrong_email));
                verification = false;
            }else {
                txtInputLayoutEmail.setError(null);
            }
        }

        if (password.isEmpty()){
            txtInputLayoutPassword.setError(getString(R.string.txt_login_layout_error_empty_password));
            txtInputEditTextPassword.setHint(getString(R.string.txt_login_edit_text_error_empty_password));
            verification=false;
        }else {
            if (password.length()<8){
                txtInputLayoutPassword.setError(getString(R.string.txt_login_layout_error_wrong_password));
                verification = false;
            }else {
                txtInputLayoutPassword.setError(null);
            }
        }
        return verification;
    }

}
