package com.example.mapeamentoenergico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText editTextUsernameRegister;
    private EditText editTextPasswordRegister;
    private EditText editTextNameRegister;
    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        editTextUsernameRegister = findViewById(R.id.editTextUsernameRegister);
        editTextPasswordRegister = findViewById(R.id.editTextPasswordRegister);
        editTextNameRegister = findViewById(R.id.editTextNameregister);
        buttonRegister = findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsernameRegister.getText().toString();
                String password = editTextPasswordRegister.getText().toString();
                String name = editTextNameRegister.getText().toString();

                if (register(username)) {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Cadastre um nome de usuário",
                            Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private boolean register (String username){
        return username != null && !username.isEmpty();
    }
}




