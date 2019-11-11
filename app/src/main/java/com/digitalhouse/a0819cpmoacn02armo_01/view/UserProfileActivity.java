package com.digitalhouse.a0819cpmoacn02armo_01.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.controller.GetUserCallback;
import com.digitalhouse.a0819cpmoacn02armo_01.controller.UserRequest;
import com.digitalhouse.a0819cpmoacn02armo_01.model.User;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import java.util.Arrays;

public class UserProfileActivity extends AppCompatActivity implements GetUserCallback.IGetUserResponse{

    private Toolbar toolbar;
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private ImageView imgUserPicture;
    private TextView txtUserName;
    private TextView txtUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        toolbar = findViewById(R.id.toolbar_user_profile_activity);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        imgUserPicture = findViewById(R.id.imageView_user_profile_image);
        txtUserName = findViewById(R.id.user_profile_activity_textView_userName);
        txtUserEmail = findViewById(R.id.user_profile_activity_textView_userEmail);
        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button_fb);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //TODO: borrar esta linea cuando se defina el comportamiento
                Toast.makeText(UserProfileActivity.this, "Login Exitoso", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                //TODO: borrar esta linea cuando se defina el comportamiento
                Toast.makeText(UserProfileActivity.this, "Login cancelado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                //TODO: borrar esta linea cuando se defina el comportamiento
                Toast.makeText(UserProfileActivity.this, "Login con error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        UserRequest.makeUserRequest(new GetUserCallback(UserProfileActivity.this).getCallback());
    }

    @Override
    public void onCompleted(User user) {
        Glide.with(UserProfileActivity.this)
                .load(user.getPicture())
                .placeholder(R.drawable.img_artist_placeholder)
                .into(imgUserPicture);

        txtUserName.setText(user.getName());
        //mId.setText(user.getId());
        if (user.getEmail() == null) {
            txtUserEmail.setText("El email no fue configurado");
            txtUserEmail.setTextColor(Color.RED);
        } else {
            txtUserEmail.setText(user.getEmail());
        }
        //mPermissions.setText(user.getPermissions());
    }

}
