package com.id22.employees;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.id22.employees.core.data.model.EmployeeModel;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.RecyclerViewAdapter> {

    private final Context context;
    private final ArrayList<EmployeeModel> mItems;
    private final ItemClickListener itemClickListener;

    public MainAdapter(Context context, ArrayList<EmployeeModel> mItems, ItemClickListener itemClickListener) {
        this.context = context;
        this.mItems = mItems;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main, viewGroup, false);
        return new RecyclerViewAdapter(view, itemClickListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int i) {
        EmployeeModel item = mItems.get(i);

        holder.tvName.setText(item.getName());
        holder.tvGender.setText(item.getGender());
        holder.tvDob.setText(item.getPlaceOfBirth() + ", " + item.getDateOfBirth());
        holder.tvStatus.setText(item.getStatus());
        holder.tvAddress.setText(item.getAddress());

        holder.itemView.setOnClickListener(v -> itemClickListener.onItemClick(item));
        holder.btnDelete.setOnClickListener(v -> itemClickListener.onItemDelete(item.getId()));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

        AppCompatButton btnDelete;
        TextView tvName, tvGender, tvDob, tvStatus, tvAddress;
        ItemClickListener itemClickListener;

        RecyclerViewAdapter(View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            btnDelete = itemView.findViewById(R.id.btn_delete);
            tvName = itemView.findViewById(R.id.tv_name);
            tvGender = itemView.findViewById(R.id.tv_gender);
            tvDob = itemView.findViewById(R.id.tv_dob);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tvAddress = itemView.findViewById(R.id.tv_address);

            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
        }
    }

    public interface ItemClickListener {
        void onItemClick(EmployeeModel data);

        void onItemDelete(int id);
    }
}