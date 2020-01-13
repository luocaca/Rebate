package com.just.rebate.adapter.recycle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.just.rebate.R;
import com.just.rebate.data.Bank_Card_DataServer;

import java.util.List;

public class AlertDigLog_Card_ChooseAdapter extends RecyclerView.Adapter<AlertDigLog_Card_ChooseAdapter.ViewHolder> {

    private List<Bank_Card_DataServer.DataBean> card_dataServers;
    private Context mContext;
    private OnItemClickListener itemClickListener;


    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public AlertDigLog_Card_ChooseAdapter(List<Bank_Card_DataServer.DataBean> bank_card_dataServers, Context context) {
        this.card_dataServers = bank_card_dataServers;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_bank_card_choosse, parent, false);
        return new ViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AlertDigLog_Card_ChooseAdapter.ViewHolder holder, int position) {
        if (card_dataServers.size() != 0) {
            holder.mTv_Card_Data.setText(card_dataServers.get(position).ReceivingAccount + "  （" + card_dataServers.get(position).ReceivingBankName + ")");
        }
    }


    @Override
    public int getItemCount() {
        if (card_dataServers.size() != 0) {
            return card_dataServers.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTv_Card_Data;
        private OnItemClickListener onItemClickListener;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            onItemClickListener = listener;
            itemView.setOnClickListener(this);
            mTv_Card_Data = itemView.findViewById(R.id.Card_Data);

        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }
}
