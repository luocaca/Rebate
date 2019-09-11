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
    private List<Bank_Card_DataServer> mDataServer;

    public BankCardAdapter(List<Bank_Card_DataServer> mDataServer) {
        this.mDataServer = mDataServer;

    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == NORMAL_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bank_card, parent, false);
            return new ViewHolder(view, viewType);
        } else {
            View footview = LayoutInflater.from(context).inflate(R.layout.footview_layout,parent, false);
            return new ViewHolder(footview, viewType);
        }

    }

    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return FOOT_VIEW;
        }
        return NORMAL_VIEW;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mtv_text.setText(mDataServer.get(position).getText());
        holder.mtv_number.setText(mDataServer.get(position).getNumber());
        holder.mIv_pic.setImageResource(mDataServer.get(position).getPic());


    }

    @Override
    public int getItemCount() {
        return mDataServer == null ? 0 : mDataServer.size()+1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout footview;
        private TextView mtv_text, mtv_number;
        private ImageView mIv_pic;


        ViewHolder(View itemView,int viewType) {
            super(itemView);
            if (viewType == NORMAL_VIEW) {
                mtv_text = itemView.findViewById(R.id.Text);
                mtv_number = itemView.findViewById(R.id.card_number);
                mIv_pic = itemView.findViewById(R.id.pic);
                //如果是footView那么给footView赋值。
            } else if (viewType == FOOT_VIEW) {
                footview = (LinearLayout) itemView;
            }

        }
    }
}

