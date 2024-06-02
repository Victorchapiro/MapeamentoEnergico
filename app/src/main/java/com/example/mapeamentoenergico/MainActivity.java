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
        applianceList.add(new Appliance("Geladeira", 56.88, 240.00, 13651.20));
        applianceList.add(new Appliance("Chuveiro elétrico", 88.00, 30.00, 2640.00));
        applianceList.add(new Appliance("Fogão elétrico (cooktop)", 68.55, 30.00, 2056.50));
        applianceList.add(new Appliance("Lavadora de roupas", 30.86, 10.00, 308.60));
        applianceList.add(new Appliance("Televisão", 48.24, 120.00, 5788.80));
        applianceList.add(new Appliance("Ar-condicionado", 259.20, 180.00, 46656.00));
        applianceList.add(new Appliance("Micro-ondas", 13.98, 20.00, 279.60));
        applianceList.add(new Appliance("Computador", 15.12, 80.00, 1209.60));
        applianceList.add(new Appliance("Secadora de roupas", 14.92, 8.00, 119.36));
        applianceList.add(new Appliance("Aspirador de pó", 5.28, 2.00, 10.56));
        applianceList.add(new Appliance("Ferro de passar roupa", 5.18, 2.00, 10.36));
        applianceList.add(new Appliance("Cafeteira elétrica", 2.88, 1.00, 2.88));
        applianceList.add(new Appliance("Chaleira elétrica", 2.52, 1.00, 2.52));
        applianceList.add(new Appliance("Torradeira", 1.32, 0.50, 0.66));
        applianceList.add(new Appliance("Liquidificador", 1.08, 0.50, 0.54));
        applianceList.add(new Appliance("Batedeira", 0.84, 0.50, 0.42));
        applianceList.add(new Appliance("Fritadeira elétrica", 1.76, 1.00, 1.76));
        applianceList.add(new Appliance("Máquina de lavar louças", 1.44, 2.00, 2.88));
        applianceList.add(new Appliance("Aparelho de som", 10.56, 30.00, 316.80));
        applianceList.add(new Appliance("Videogame", 6.48, 20.00, 129.60));
        applianceList.add(new Appliance("Carregador de celular", 0.54, 60.00, 32.40));
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