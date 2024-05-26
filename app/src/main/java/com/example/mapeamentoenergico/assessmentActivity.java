package com.example.mapeamentoenergico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class assessmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assessment_result_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Encontrar o ImageButton pelo ID
        ImageButton btnUserManagement = findViewById(R.id.buttonUser);
        btnUserManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar para a tela de gerenciamento de usuário
                Intent intent = new Intent(assessmentActivity.this, UserManagementActivity.class);
                startActivity(intent);
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar de volta para a tela principal (MainActivity)
                Intent intent = new Intent(assessmentActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
