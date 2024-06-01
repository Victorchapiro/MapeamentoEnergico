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
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageButton buttonUser;
    private Button buttonAssessment;
    private RecyclerView recyclerView;
    private ApplianceAdapter applianceAdapter;
    private List<Appliance> applianceList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.main_activity);
        buttonUser = findViewById(R.id.buttonUser);
        buttonAssessment = findViewById(R.id.buttonAssessment);
        recyclerView = findViewById(R.id.recyclerView);
        // Initialize the appliance list
        applianceList = new ArrayList<>();
        applianceList.add(new Appliance("Geladeira"));
        applianceList.add(new Appliance("Ar Condicionado"));
        applianceList.add(new Appliance("Ferro"));
        applianceList.add(new Appliance("Máquina de Lavar"));

        // Set the adapter and layout manager for the RecyclerView
        applianceAdapter = new ApplianceAdapter(applianceList);
        recyclerView.setAdapter(applianceAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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