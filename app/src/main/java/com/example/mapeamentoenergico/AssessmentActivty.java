package com.example.mapeamentoenergico;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AssessmentActivty extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ApplianceAssessmentAdapter applianceAdapter;
    private ImageButton buttonUser;
    private Toolbar toolbarHome;
    private List<Appliance> checkedItems;
    private List<ApplianceAssessment> assessmentBill;
    private double conta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.assessment_result_activity);

        buttonUser = findViewById(R.id.buttonUser);
        toolbarHome = findViewById(R.id.toolbarHome);
        recyclerView = findViewById(R.id.recyclerView);
        setSupportActionBar(toolbarHome);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        assessmentBill = new ArrayList<>();

        // Receber os itens selecionados
        checkedItems = getIntent().getParcelableArrayListExtra("checkedItems");
        conta = getIntent().getDoubleExtra("conta", 0);
        for (Appliance a : checkedItems) {
            assessmentBill.add(new ApplianceAssessment(a.getConsumption(), a.getName()));
        }

        // Configurar o Adapter para o RecyclerView
        applianceAdapter = new ApplianceAssessmentAdapter(assessmentBill);
        recyclerView.setAdapter(applianceAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Calcular o percentual para o primeiro appliance
        Appliance appliance = checkedItems.get(0);
        double percentual = percentualCalculator(checkedItems, appliance);
        Log.d("CalculadoraPercentual", "O percentual para " + appliance.getName() + " é: " + percentual + "%");

        // Configurar ação do botão de voltar
        toolbarHome.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssessmentActivty.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Configurar ação do botão de usuário
        buttonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssessmentActivty.this, UserManagementActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public static double percentualCalculator(List<Appliance> checkedItems, Appliance appliance) {
        double somatorioTotal = 0.0;
        for (Appliance a : checkedItems) {
            somatorioTotal += a.getEnergicSpent() * a.getQuantity();
            Log.d("valor", "somatório " + somatorioTotal);
        }

        double valorAppliance = appliance.getEnergicSpent() * appliance.getQuantity();
        double percentual = (valorAppliance / somatorioTotal) * 100.0;

        return percentual;
    }
}
