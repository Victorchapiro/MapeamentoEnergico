package com.example.mapeamentoenergico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AssessmentActivty extends AppCompatActivity {


    private ImageButton buttonUser;
    private Toolbar toolbarHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.assessment_result_activity);
        buttonUser = findViewById(R.id.buttonUser);
        toolbarHome = findViewById(R.id.toolbarHome);
        setSupportActionBar(toolbarHome);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        toolbarHome.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Direcionar para a MainActivity
                Intent intent = new Intent(AssessmentActivty.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finaliza a atividade atual se necessário
            }
        });

        buttonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflar o layout user_management_activity
                Intent intent = new Intent(AssessmentActivty.this, UserManagementActivity.class);
                startActivity(intent);
                finish(); // Finalize a atividade atual se necessário

            }
        });
    }

}

