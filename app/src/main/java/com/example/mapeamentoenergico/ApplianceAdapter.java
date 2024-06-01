package com.example.mapeamentoenergico;

// ApplianceAdapter.java
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
        holder.nameTextView.setText(appliance.getName());
    }

    @Override
    public int getItemCount() {
        return applianceList.size();
    }

    public static class ApplianceViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;

        public ApplianceViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.checkBoxAppliance);
        }
    }
}
