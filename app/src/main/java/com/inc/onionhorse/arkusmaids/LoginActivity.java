package com.inc.onionhorse.arkusmaids;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSignin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignUp;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null){
            //actividad de perfil va aqui
            finish();//termina la actividad actual para brincar a Perfil
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }

        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        buttonSignin = (Button)findViewById(R.id.buttonSignin);
        textViewSignUp = (TextView)findViewById(R.id.textViewSignUp);

        progressDialog = new ProgressDialog(this);

        buttonSignin.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);

    }

    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            //Correo esta vacio
            Toast.makeText(this, "Please enter an email", Toast.LENGTH_SHORT).show();
            //pone alto a la ejecucion
            return;
        }

        if (TextUtils.isEmpty(password)){
            //contraseña esta vacia
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
            //pone alto a la ejecucion
            return;
        }
        //si la contraseña y usuario no estan vacias
        // despliega dialogo de progreso
        progressDialog.setMessage("Accesing account profile ...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            //da comienzo la actividad de perfil
                            finish();//termina la actividad actual para brincar a Perfil
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if (view == buttonSignin){
            userLogin();
        }
        if (view == textViewSignUp){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

    }
}
