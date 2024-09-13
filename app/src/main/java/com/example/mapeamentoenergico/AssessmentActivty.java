package com.example.mapeamentoenergico;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AssessmentActivty extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ApplianceAssessmentAdapter applianceAdapter;
    private ImageButton buttonUser;
    private Toolbar toolbarHome;
    private List<Appliance> checkedItems;
    private List<ApplianceAssessment> assessmentBill;
    private double conta;
    private TextView textView;
    private String token;  // O token JWT será armazenado aqui
    private static final String BASE_URL = "http://192.168.1.14:5000"; // URL da sua API (no caso de localhost em um emulador Android)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assessment_result_activity);

        buttonUser = findViewById(R.id.buttonUser);
        toolbarHome = findViewById(R.id.toolbarHome);
        recyclerView = findViewById(R.id.recyclerView);
        textView = findViewById(R.id.textView5);

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

        // Realizar login e obter token JWT
        loginUser("usuario1", "senha123");  // Você pode substituir pelo usuário e senha corretos
        buttonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflar o layout user_management_activity
                Intent intent = new Intent(AssessmentActivty.this, UserManagementActivity.class);
                startActivity(intent);
                finish(); // Finalize a atividade atual se necessário
            }
        });
        toolbarHome.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Direcionar para a MainActivity
                Intent intent = new Intent(AssessmentActivty.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finaliza a atividade atual se necessário
            }
        });



    }

    private void loginUser(String username, String password) {
        Retrofit retrofit = RetrofitClient.getClient(BASE_URL);
        ApiService apiService = retrofit.create(ApiService.class);

        LoginRequest loginRequest = new LoginRequest(username, password);

        Call<LoginResponse> call = apiService.login(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    token = response.body().getAccessToken();  // Armazenar o token
                    getEnergyTipFromApi();  // Após login bem-sucedido, obter a dica de energia
                } else {
                    Log.e("Login Error", "Erro ao autenticar");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("Login Error", "Falha na chamada da API de login", t);
            }
        });
    }

    private void getEnergyTipFromApi() {
        Retrofit retrofit = RetrofitClient.getClient(BASE_URL);
        ApiService apiService = retrofit.create(ApiService.class);

        Call<EnergyTipResponse> call = apiService.getEnergyTip("Bearer " + token);
        call.enqueue(new Callback<EnergyTipResponse>() {
            @Override
            public void onResponse(Call<EnergyTipResponse> call, Response<EnergyTipResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String dicaDaApi = response.body().getDica();
                    Log.d("API Success", "Dica recebida: " + dicaDaApi);
                    textView.setText(dicaDaApi);  // Exibir a dica retornada da API
                } else {
                    Log.e("API Error", "Erro ao obter a dica de energia");
                }
            }

            @Override
            public void onFailure(Call<EnergyTipResponse> call, Throwable t) {
                Log.e("API Error", "Falha na chamada da API", t);
            }
        });
    }

    public List<ApplianceAssessment> percentualCalculator(List<Appliance> checkedItems, double conta) {
        List<ApplianceAssessment> assessmentBill = new ArrayList<>();
        double somatorioTotal = 0.0;
        for (Appliance a : checkedItems) {
            somatorioTotal += a.getEnergicSpent() * a.getQuantity();
        }

        for (Appliance a : checkedItems) {
            double valueEachAppliance = (a.getEnergicSpent() * a.getQuantity());
            double percentual = ((valueEachAppliance / somatorioTotal) * conta);
            assessmentBill.add(new ApplianceAssessment(percentual, a.getName()));
        }

        return assessmentBill;
    }
}
