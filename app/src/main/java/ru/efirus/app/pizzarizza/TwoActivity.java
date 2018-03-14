package ru.efirus.app.pizzarizza;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class TwoActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int RC_SIGN_IN = 123;
    Button btnSignin;
    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        btnSignin = (Button) findViewById(R.id.button2);
        btnSignin.setOnClickListener(this);
        btnExit = (Button) findViewById(R.id.button3);
        btnExit.setOnClickListener(this);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            // already signed in
            btnSignin.setEnabled(false);
            btnExit.setEnabled(true);
        } else {
            // not signed in
            btnSignin.setEnabled(true);
            btnExit.setEnabled(false);
        }
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.button2:
                // Choose authentication providers
                List<AuthUI.IdpConfig> providers = Arrays.asList(
                        new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build());
                // Create and launch sign-in intent
                startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setAvailableProviders(providers)
                                .build(),
                        RC_SIGN_IN);
                break;
            case R.id.button3:
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                // ...
                                btnSignin.setEnabled(true);
                                btnExit.setEnabled(false);
                            }
                        });

                break;
            default:
                break;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                btnSignin.setEnabled(false);
                btnExit.setEnabled(true);
                Toast.makeText(this, "Успешная авторизация))", Toast.LENGTH_SHORT).show();
                // ...
            } else {
                // Sign in failed, check response for error code
                // ...
                Toast.makeText(this, "ОШИБКА авторицации", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
