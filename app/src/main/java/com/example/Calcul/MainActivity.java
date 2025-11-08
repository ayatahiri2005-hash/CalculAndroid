package com.example.Calcul;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editLogin = findViewById(R.id.editLogin); // ton EditText login
        EditText editPassword = findViewById(R.id.editPassword); // ton EditText mot de passe
        Button buttonValidate = findViewById(R.id.buttonValidate);

        buttonValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = editLogin.getText().toString();
                String password = editPassword.getText().toString();

                // Vérifie les informations
                if(login.equals("admin") && password.equals("1234")) {
                    // si correct, ouvre la deuxième activité
                    Intent intent = new Intent(MainActivity.this, CalculActivity.class);
                    startActivity(intent);
                } else {
                    // sinon, affiche un message
                    Toast.makeText(MainActivity.this, "Login ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
