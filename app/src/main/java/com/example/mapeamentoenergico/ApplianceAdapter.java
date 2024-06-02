package com.example.mapeamentoenergico;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ApplianceAdapter extends RecyclerView.Adapter<ApplianceAdapter.ApplianceViewHolder> {

    private List<Appliance> applianceList;

    public ApplianceAdapter(List<Appliance> applianceList) {
        this.applianceList = applianceList;
    }

    @NonNull
    @Override
    public ApplianceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appliance, parent, false);
        return new ApplianceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplianceViewHolder holder, int position) {
        Appliance appliance = applianceList.get(position);
        holder.checkBox.setText(appliance.getName());
        holder.checkBox.setChecked(appliance.isChecked());


        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            applianceList.get(holder.getAdapterPosition()).setChecked(isChecked);
        });

        holder.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                int quantity = s.toString().isEmpty() ? 0 : Integer.parseInt(s.toString());
                applianceList.get(holder.getAdapterPosition()).setQuantity(quantity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return applianceList.size();
    }

    public static class ApplianceViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        EditText editText;

        public ApplianceViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBoxAppliance);
            editText = itemView.findViewById(R.id.editTextQuantity);
        }
    }
}
