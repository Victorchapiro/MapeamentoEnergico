package com.example.mapeamentoenergico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton buttonUser;
    private Button buttonAssessment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.main_activity);
        buttonUser = findViewById(R.id.buttonUser);
        buttonAssessment = findViewById(R.id.buttonAssessment);

        buttonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflar o layout user_management_activity
                Intent intent = new Intent(MainActivity.this, UserManagementActivity.class);
                startActivity(intent);
                finish(); // Finalize a atividade atual se necessário
            }
        });

        buttonAssessment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AssessmentActivty.class);
                startActivity(intent);
                finish(); // Finalize a atividade atual se necessário
            }
        });
    }
}