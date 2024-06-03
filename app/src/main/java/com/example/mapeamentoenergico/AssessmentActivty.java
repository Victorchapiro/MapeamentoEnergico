package com.example.mapeamentoenergico;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AssessmentActivty extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ApplianceAssessmentAdapter applianceAdapter;
    private ImageButton buttonUser;
    private Toolbar toolbarHome;
    private List<Appliance> checkedItems;
    private List<ApplianceAssessment> assessmentBill;
    private double conta;
    private TextView textView;
    String[] dicas = {
            "Desligue aparelhos não utilizados: Desligue luzes, ventiladores, computadores e outros aparelhos quando não estiverem em uso.",
            "Use lâmpadas LED: Elas consomem menos energia e duram mais.",
            "Aproveite a luz natural: Mantenha as janelas abertas durante o dia para reduzir o uso de lâmpadas.",
            "Regule o termostato: Ajuste o termostato para uma temperatura eficiente. No verão, mantenha-o mais alto; no inverno, mais baixo.",
            "Desconecte carregadores: Desconecte carregadores de celular e outros dispositivos quando não estiverem em uso, pois continuam consumindo energia.",
            "Utilize eletrodomésticos eficientes: Opte por aparelhos com selo de eficiência energética (Procel ou Energy Star).",
            "Cozinhe de forma eficiente: Use tampas nas panelas e cozinhe em fogo baixo para economizar gás.",
            "Faça manutenção regular: Limpe ou troque filtros de ar condicionado e aquecedores regularmente para garantir eficiência.",
            "Evite stand-by: Desligue aparelhos da tomada em vez de deixá-los em modo stand-by.",
            "Lave roupas com água fria: Sempre que possível, use água fria para lavar roupas e acumule uma quantidade maior antes de lavar.",
            "Seque roupas ao ar livre: Sempre que possível, evite usar a secadora e seque as roupas ao ar livre.",
            "Utilize timers: Use temporizadores em aparelhos como aquecedores de água e luzes externas."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.assessment_result_activity);

        buttonUser = findViewById(R.id.buttonUser);
        toolbarHome = findViewById(R.id.toolbarHome);
        recyclerView = findViewById(R.id.recyclerView);
        textView = findViewById(R.id.textView5);
        Random random = new Random();
        int indiceAleatorio = random.nextInt(dicas.length);
        String dicaAleatoria = dicas[indiceAleatorio];
        textView.setText(dicaAleatoria);
        setSupportActionBar(toolbarHome);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        assessmentBill = new ArrayList<>();

        // Receber os itens selecionados
        checkedItems = getIntent().getParcelableArrayListExtra("checkedItems");
        conta = getIntent().getDoubleExtra("conta", 0);

        // Calcular o percentual e atualizar a lista assessmentBill
        assessmentBill = percentualCalculator(checkedItems, conta);

        // Configurar o Adapter para o RecyclerView
        applianceAdapter = new ApplianceAssessmentAdapter(assessmentBill);
        recyclerView.setAdapter(applianceAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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

    public List<ApplianceAssessment> percentualCalculator(List<Appliance> checkedItems, double conta) {
        List<ApplianceAssessment> assessmentBill = new ArrayList<>();
        double somatorioTotal = 0.0;
        for (Appliance a : checkedItems) {
            somatorioTotal += a.getEnergicSpent() * a.getQuantity();
            Log.d("valor", "somatório " + somatorioTotal);
        }

        for (Appliance a : checkedItems) {
            double valueEachAppliance = (a.getEnergicSpent() * a.getQuantity());
            double percentual = ((valueEachAppliance / somatorioTotal) * conta);
            assessmentBill.add(new ApplianceAssessment(percentual, a.getName()));
        }

        return assessmentBill;
    }
}
