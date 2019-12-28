package com.digitalhouse.a0819cpmoacn02armo_01.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.model.User;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Arrays;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private static final String COLLECTION_USERS = "Users";

    private Toolbar toolbar;
    private CallbackManager callbackManager;
    private LoginButton btnLoginFacebook;
    private MaterialButton btnLogin;
    private MaterialButton btnRegister;
    private TextInputLayout txtInputLayoutEmail;
    private TextInputEditText txtInputEditTextEmail;
    private TextInputLayout txtInputLayoutPassword;
    private TextInputEditText txtInputEditTextPassword;
    private FirebaseAuth auth;
    private FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        toolbar = findViewById(R.id.toolbar_login_activity);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnLogin = findViewById(R.id.login_activity_material_button_login);
        btnRegister = findViewById(R.id.login_activity_material_button_register);
        txtInputLayoutEmail = findViewById(R.id.login_activity_text_input_layout_email);
        txtInputEditTextEmail = findViewById(R.id.login_activity_text_input_edit_text_email);
        txtInputLayoutPassword = findViewById(R.id.login_activity_text_input_layout_password);
        txtInputEditTextPassword = findViewById(R.id.login_activity_text_input_edit_text_password);

        callbackManager = CallbackManager.Factory.create();
        btnLoginFacebook = findViewById(R.id.login_button_fb);
        btnLoginFacebook.setReadPermissions(Arrays.asList("email", "public_profile"));
        btnLoginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, R.string.txt_login_activity_login_canceled, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(LoginActivity.this, R.string.txt_login_activity_login_error, Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtInputEditTextEmail.getText().toString();
                String password = txtInputEditTextPassword.getText().toString();
                if (checkForm()){
                    createFirebaseUser(email,password);
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtInputEditTextEmail.getText().toString();
                String password = txtInputEditTextPassword.getText().toString();
                if (checkForm()){
                    LoginUserFirebase(email,password);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null){
            onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

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
        } else {
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
        } else {
            if (password.length()<8){
                txtInputLayoutPassword.setError(getString(R.string.txt_login_layout_error_wrong_password));
                verification = false;
            }else {
                txtInputLayoutPassword.setError(null);
            }
        }
        return verification;
    }

    private void createFirebaseUser(String email, String password){
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            saveUserLoggedInFirestore();
                            updateUI(user);
                            Bundle bundle = new Bundle();
                            bundle.putString(FirebaseAnalytics.Param.METHOD, "sign_up");
                            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SIGN_UP, bundle);
                        } else {
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void saveUserLoggedInFirestore() {
        FirebaseUser currentUser = auth.getCurrentUser();
        User newUser = new User(currentUser.getDisplayName(),currentUser.getEmail());
        if (currentUser.getPhotoUrl() != null){
            newUser.setUserProfileImage(currentUser.getPhotoUrl().toString());
        }
        FirebaseFirestore.getInstance()
                .collection(COLLECTION_USERS)
                .document(currentUser.getUid())
                .set(newUser);
    }

    private void LoginUserFirebase(String email, String password){
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            saveUserLoggedInFirestore();
                            updateUI(user);
                        } else {
                            Toast.makeText(LoginActivity.this, R.string.txt_login_activity_handle_fb_token_error, Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

}