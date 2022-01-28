package com.pritesh.mvvm_coroutines.ui.main.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.pritesh.mvvm_coroutines.R;
import com.pritesh.mvvm_coroutines.databinding.LayoutListBinding;
import com.pritesh.mvvm_coroutines.ui.main.models.AleList;

import java.util.List;

public class AleAdapter extends RecyclerView.Adapter<AleAdapter.ViewHolder> {

    private List<AleList> itemList;
    private LayoutInflater layoutInflater;
    Context context;
    LayoutListBinding binding;

    public AleAdapter(Context context, List<AleList> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.layout_list
                , viewGroup, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        try {
            viewHolder.binding.tvName.setText(itemList.get(position).getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LayoutListBinding binding;

        public ViewHolder(LayoutListBinding weekBinding) {
            super(weekBinding.getRoot());
            this.binding = weekBinding;
        }

        public LayoutListBinding getBinding() {
            return binding;
        }
    }
}
