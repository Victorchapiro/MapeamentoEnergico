package com.example.mapeamentoenergico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ImageButton buttonUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.main_activity);
        buttonUser = findViewById(R.id.buttonUser);

        buttonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflar o layout user_management_activity
                Intent intent = new Intent(MainActivity.this, UserManagementActivity.class);
                startActivity(intent);
                finish(); // Finalize a atividade atual se necessário
            }
        });
    }
}