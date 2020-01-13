package com.just.rebate.adapter.recycle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.just.rebate.R;
import com.just.rebate.data.Bank_Card_DataServer;

import java.util.List;

/**
 * 银行卡
 */
public class BankCardAdapter extends RecyclerView.Adapter<BankCardAdapter.ViewHolder> {
    private Context context;
    private static final int NORMAL_VIEW = 0;
    private static final int FOOT_VIEW = 1;
    private List<Bank_Card_DataServer.DataBean> mDataServer;

    public BankCardAdapter(List<Bank_Card_DataServer.DataBean> mDataServers, Context mContext) {
        this.mDataServer = mDataServers;
        this.context = mContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bank_card1, parent, false);
        return new ViewHolder(view);


    }

//    public int getItemViewType(int position) {
//        if (position == getItemCount() - 1) {
//            return FOOT_VIEW;
//        }
//        return NORMAL_VIEW;
//    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(mDataServer.size()!=0){
            holder.mtv_text.setText(mDataServer.get(position).ReceivingBankName);
            holder.mtv_number.setText(mDataServer.get(position).ReceivingAccount);
            Glide.with(context).load(mDataServer.get(position)).into(holder.mIv_pic);
        }
    }

    @Override
    public int getItemCount() {
        return mDataServer.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout footview;
        private TextView mtv_text, mtv_number;
        private ImageView mIv_pic;


        ViewHolder(View itemView) {
            super(itemView);
            mtv_text = itemView.findViewById(R.id.bank);
            mtv_number = itemView.findViewById(R.id.card_number);
            mIv_pic = itemView.findViewById(R.id.pic);
        }
    }
}

