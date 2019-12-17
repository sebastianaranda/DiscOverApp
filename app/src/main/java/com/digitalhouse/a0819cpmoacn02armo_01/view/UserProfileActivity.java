package com.digitalhouse.a0819cpmoacn02armo_01.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.digitalhouse.a0819cpmoacn02armo_01.R;
import com.digitalhouse.a0819cpmoacn02armo_01.controller.GetUserCallback;
import com.digitalhouse.a0819cpmoacn02armo_01.controller.UserRequest;
import com.digitalhouse.a0819cpmoacn02armo_01.model.User;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfileActivity extends AppCompatActivity implements GetUserCallback.IGetUserResponse{

    private static final String COLLECTION_USERS = "Users";
    private static final int REQUEST_CODE_CHOOSE_IMAGE = 1;
    public static final String PROFILE_IMAGE = "userProfileImage";

    private Toolbar toolbar;
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private CircleImageView imgUserPicture;
    private ImageView btnEditUserImage;
    private EditText txtUserName;
    private EditText txtUserEmail;
    private Button btnEditUserInfo;
    private Button btnSaveUserInfo;
    private User user;
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private FirebaseStorage storage;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        toolbar = findViewById(R.id.toolbar_user_profile_activity);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        progressBar = findViewById(R.id.progressbar_upload_image);
        imgUserPicture = findViewById(R.id.image_view_user_profile_image);
        txtUserName = findViewById(R.id.user_profile_activity_edit_text_user_name);
        txtUserEmail = findViewById(R.id.user_profile_activity_edit_text_user_email);
        btnEditUserInfo = findViewById(R.id.user_profile_activity_button_edit_info_user);
        btnSaveUserInfo = findViewById(R.id.user_profile_activity_button_save_info_user);
        btnEditUserImage = findViewById(R.id.activity_user_profile_edit_image_icon);
        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button_fb);

        //encuento instancias de mis servicios
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        storage = FirebaseStorage.getInstance();

        getCurrentUser();

        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button_fb);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(UserProfileActivity.this, getString(R.string.txt_login_activity_welcome), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(UserProfileActivity.this, getString(R.string.txt_login_activity_login_canceled), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(UserProfileActivity.this, getString(R.string.txt_login_activity_login_error), Toast.LENGTH_SHORT).show();
            }
        });

        btnEditUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtUserName.setFocusableInTouchMode(true);
                txtUserEmail.setFocusableInTouchMode(true);
                btnEditUserInfo.setVisibility(View.GONE);
                btnSaveUserInfo.setVisibility(View.VISIBLE);
                btnEditUserImage.setVisibility(View.VISIBLE);
            }
        });

        btnSaveUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = txtUserName.getText().toString();
                String userEmail = txtUserEmail.getText().toString();
                if (!(userName.isEmpty())){
                    user.setName(userName);
                }
                if (!(userEmail.isEmpty())){
                    user.setEmail(userEmail);
                }
                saveNewData(user);
                uploadUrlImageToFirestore();
                btnSaveUserInfo.setVisibility(View.GONE);
                btnEditUserImage.setVisibility(View.GONE);
                btnEditUserInfo.setVisibility(View.VISIBLE);
                txtUserName.setFocusable(false);
                txtUserName.setText(null);
                txtUserName.setHint(user.getName());
                txtUserEmail.setFocusable(false);
                txtUserEmail.setText(null);
                txtUserEmail.setHint(user.getEmail());
            }
        });

        btnEditUserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
                btnSaveUserInfo.setVisibility(View.GONE);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
                Toast.makeText(UserProfileActivity.this, R.string.txt_user_profile_activity_logout, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserProfileActivity.this,MainActivity.class));
            }
        });
        getCurrentUser();
    }

    @Override
    protected void onResume() {
        super.onResume();
        UserRequest.makeUserRequest(new GetUserCallback(UserProfileActivity.this).getCallback());
    }

    @Override
    public void onCompleted(User user) {
        Glide.with(UserProfileActivity.this)
                .load(user.getUserProfileImage())
                .placeholder(R.drawable.img_artist_placeholder)
                .into(imgUserPicture);

        txtUserName.setText(user.getName());
        if (user.getEmail() == null) {
            txtUserEmail.setText(getString(R.string.txt_user_profile_activity_no_email_message));
            txtUserEmail.setTextColor(Color.RED);
        } else {
            txtUserEmail.setText(user.getEmail());
        }
    }

    private void getCurrentUser(){
        firestore.collection(COLLECTION_USERS)
                .document(currentUser.getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        user = documentSnapshot.toObject(User.class);
                        if (user != null) {
                            txtUserName.setHint(user.getName());
                            txtUserEmail.setHint(user.getEmail());
                            if (user.getUserProfileImage() != null) {
                                Glide.with(UserProfileActivity.this)
                                    .load(user.getUserProfileImage())
                                    .into(imgUserPicture);
                            }
                        }
                    }
                });
        loginButton.setVisibility(View.INVISIBLE);
    }

    private void saveNewData(User newUser){
        firestore.collection(COLLECTION_USERS)
                .document(currentUser.getUid())
                .set(newUser);
        currentUser.updateEmail(newUser.getEmail());
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,getString(R.string.txt_user_profile_activity_upload_image)), REQUEST_CODE_CHOOSE_IMAGE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_CHOOSE_IMAGE){
            Uri uri = data.getData();
            imgUserPicture.setImageURI(uri);
            uploadImageToStorage();
        }
    }

    private void uploadImageToStorage(){
        imgUserPicture.setDrawingCacheEnabled(true);
        imgUserPicture.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imgUserPicture.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        StorageReference reference = storage.getReference(PROFILE_IMAGE).child(currentUser.getUid());
        UploadTask uploadTask = reference.putBytes(data);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(UserProfileActivity.this, R.string.txt_user_profile_activity_upload_image_successful, Toast.LENGTH_SHORT).show();
                btnSaveUserInfo.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });
        uploadTask.addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                btnSaveUserInfo.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                Toast.makeText(UserProfileActivity.this, R.string.txt_user_profile_activity_upload_image_canceled, Toast.LENGTH_SHORT).show();
            }
        });
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UserProfileActivity.this, R.string.txt_user_profile_activity_upload_image_failure, Toast.LENGTH_SHORT).show();
                btnSaveUserInfo.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void uploadUrlImageToFirestore(){
        StorageReference reference = storage.getReference(PROFILE_IMAGE).child(currentUser.getUid());
        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                updateUserImage(uri.toString());
            }
        });
    }

    private void updateUserImage(String urlImage) {
        firestore.collection(COLLECTION_USERS)
                .document(currentUser.getUid())
                .update(PROFILE_IMAGE,urlImage);
    }

}