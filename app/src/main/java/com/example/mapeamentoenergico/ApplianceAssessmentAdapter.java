package com.example.mapeamentoenergico;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ApplianceAssessmentAdapter extends RecyclerView.Adapter<ApplianceAssessmentAdapter.ApplianceViewHolder> {
    private List<ApplianceAssessment> applianceList;

    public ApplianceAssessmentAdapter(List<ApplianceAssessment> applianceList) {
        this.applianceList = applianceList;
    }

    @NonNull
    @Override
    public ApplianceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assessment_items, parent, false);
        return new ApplianceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplianceViewHolder holder, int position) {
        ApplianceAssessment appliance = applianceList.get(position);
        holder.bind(appliance);
    }

    @Override
    public int getItemCount() {
        return applianceList.size();
    }

    public static class ApplianceViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewValue;

        public ApplianceViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewValue = itemView.findViewById(R.id.textViewValue);
        }

        public void bind(ApplianceAssessment appliance) {
            textViewName.setText(appliance.getName());
            textViewValue.setText(NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(appliance.getValueBill()));
        }
    }
}
