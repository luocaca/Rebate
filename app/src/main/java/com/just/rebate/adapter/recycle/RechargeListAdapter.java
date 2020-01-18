package com.just.rebate.adapter.recycle;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.just.rebate.R;
import com.just.rebate.entity.RechargeListData;

import org.intellij.lang.annotations.JdkConstants;

import java.util.List;

public class RechargeListAdapter extends RecyclerView.Adapter<RechargeListAdapter.ViewHolder> {
    private List<RechargeListData.DataBean> rechargeListData;


    public RechargeListAdapter(List<RechargeListData.DataBean> data) {
        this.rechargeListData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recharge_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTv_OrderNumber.setText(rechargeListData.get(position).getOrderNo());

        String Times = rechargeListData.get(position).getCreatedTime();
        String timeers = Times.replaceAll("T", " ");
        String[] Time = timeers.split("\\.", 2);
        holder.mTv_CreatedTime.setText(Time[0]);

        if (rechargeListData.get(position).getOrderType() == 1) {
            holder.mTv_OrderType.setText("充值");
            holder.mTv_OrderMoeny.setText(rechargeListData.get(position).getIntegralNum() + "");
            holder.mTv_OrderMoeny.setTextColor(Color.rgb(37, 155, 36));
        } else if (rechargeListData.get(position).getOrderType() == 2) {
            holder.mTv_OrderType.setText("消费");
            holder.mTv_OrderMoeny.setText(rechargeListData.get(position).getIntegralNum() + "");
            holder.mTv_OrderMoeny.setTextColor(Color.RED);
        } else if (rechargeListData.get(position).getOrderType() == 3) {
            holder.mTv_OrderType.setText("提现");
            holder.mTv_OrderMoeny.setText(rechargeListData.get(position).getIntegralNum() + "");
            holder.mTv_OrderMoeny.setTextColor(Color.RED);
        }
        if (rechargeListData.get(position).getIsState() == 1) {
            holder.mTv_examineType.setText("已审核");
        } else if (rechargeListData.get(position).getIsState() == 0) {
            holder.mTv_examineType.setText("未审核");
        }
        holder.mTv_PaymentAccount.setText(rechargeListData.get(position).getReceivingAccount());
        holder.mTv_RecevieAccount.setText(rechargeListData.get(position).getRReceivingAccount());

    }

    @Override
    public int getItemCount() {
        if (rechargeListData.size() != 0) {
            return rechargeListData.size();
        } else {
            return 0;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTv_OrderType, mTv_OrderMoeny, mTv_examineType, mTv_PaymentAccount, mTv_RecevieAccount, mTv_OrderNumber, mTv_CreatedTime;


        public ViewHolder(View itemView) {
            super(itemView);
            mTv_OrderType = itemView.findViewById(R.id.OrderType);
            mTv_OrderMoeny = itemView.findViewById(R.id.OrderMoeny);
            mTv_examineType = itemView.findViewById(R.id.examineType);
            mTv_PaymentAccount = itemView.findViewById(R.id.PaymentAccount);
            mTv_RecevieAccount = itemView.findViewById(R.id.RecevieAccount);
            mTv_OrderNumber = itemView.findViewById(R.id.OrderNumber);
            mTv_CreatedTime = itemView.findViewById(R.id.CreatedTime);
        }
    }

}
