package com.example.android.materialdesigncodelab;

/**
 * Created by Shiva on 31-03-2018.
 */

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignAct extends AppCompatActivity {

    EditText email,password;
    Button register;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        mAuth = FirebaseAuth.getInstance();
        email= (EditText)findViewById(R.id.email);
        password= (EditText)findViewById(R.id.password);
        register= (Button) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String useremail= email.getText().toString();
                String userpass= password.getText().toString();

                mAuth.signInWithEmailAndPassword(useremail, userpass)
                        .addOnCompleteListener(SignAct.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d("success message", "signInWithEmail:onComplete:" + task.isSuccessful());

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Log.w("success message", "signInWithEmail:failed", task.getException());
                                    Toast.makeText(SignAct.this, "Authentication failed",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(SignAct.this, "SignIn Successful",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignAct.this, MainActivity.class);
                                    intent.putExtra("message", useremail);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                            }
                        });

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
    }

}
