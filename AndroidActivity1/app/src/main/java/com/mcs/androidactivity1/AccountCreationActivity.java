package com.mcs.androidactivity1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountCreationActivity extends AppCompatActivity implements View.OnClickListener,
        View.OnFocusChangeListener, View.OnKeyListener{

    static final String EMAIL_KEY = "EMAIL_KEY";
    static final String PASSWORD_KEY = "PASSWORD_KEY";


    EditText email;
    EditText passWord;
    EditText checkPass;
    ImageButton toProfile;
    ImageButton backBtn;
    ImageView emailImage;
    ImageView passwordImage;
    ImageView repeatImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation);


        toProfile = findViewById(R.id.btn_img_create_account);
        toProfile.setOnClickListener(this);
        passWord = findViewById(R.id.passwordInput);
        checkPass = findViewById(R.id.checkPasswordInput);
        backBtn = findViewById(R.id.btn_back_create_account);
        email = findViewById(R.id.emailAddressInput);
        emailImage = findViewById(R.id.emailAddressImage);
        passwordImage = findViewById(R.id.createPasswordImage);
        repeatImage = findViewById(R.id.repeatPasswordImage);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                changeTxtbox();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        passWord.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // empty
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkPasswords();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // empty
            }
        });
        checkPass.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // empty
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkPasswords();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // empty
            }
        });
    }

    public boolean check_password(String password)
    {
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasNum = false;
        char c;
        if(password != null && !password.isEmpty() && password.length() > 7 )
        {
            for(int i = 0; i < password.length(); i++)
            {
                c = password.charAt(i);
                if(Character.isUpperCase(c))
                {
                    hasUpper = true;
                }
                else if(Character.isLowerCase(c))
                {
                    hasLower = true;
                }
                else if(Character.isDigit(c))
                {
                    hasNum = true;
                }

                if(hasLower && hasUpper && hasNum)
                {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean compare_passwords(String password, String password2){
        if(password.equals(password2)) return true;
        else return false;
    }
    private boolean checkEmail() {
        final String email_str = email.getText().toString();
        return !email_str.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email_str).matches();
    }
    private void changeTxtbox() {
        if (checkEmail())
            setImage(emailImage, R.drawable.tick2x);
        else
            setImage(emailImage, R.drawable.cross2x);
    }
    private void setImage(final ImageView v, final int d)
    {
        Drawable img = getResources().getDrawable(d, getApplicationContext().getTheme());
        v.setImageDrawable(img);
        v.setMinimumWidth(img.getMinimumWidth());
    }
    @Override
    public void onClick(View v) {
        if(compare_passwords(passWord.getText().toString(),checkPass.getText().toString()) )
        {
            Intent intent = new Intent();
            intent.setClass(this, ProfileActivity.class);
            intent.putExtra("PASSWORD",passWord.getText().toString());
            startActivity(intent);
        }
        else
        {
            Toast.makeText(
                    this,
                    "Fix issues email and password issues",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void backButton(View view) {
        finish();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }
    private void checkPasswords() {
        repeatImage.setImageResource(android.R.color.transparent);
        final String p = passWord.getText().toString();
        if (check_password(p))
        {
            setImage(passwordImage, R.drawable.tick2x);
            if (p.equals(checkPass.getText().toString())) {
                setImage(repeatImage, R.drawable.tick2x);
            }
            else {
                setImage(repeatImage, R.drawable.cross2x);
            }
        }
        else {
            setImage(passwordImage, R.drawable.cross2x);
            setImage(repeatImage, R.drawable.cross2x);
        }
    }
}