package com.just.rebate.adapter.recycle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.just.rebate.R;
import com.just.rebate.data.Bank_Card_DataServer;
import com.just.rebate.data.detailed_DataServer;

import java.util.List;

public class BankCardAdapter extends RecyclerView.Adapter<BankCardAdapter.ViewHolder> {
    private Context context;
    private List<Bank_Card_DataServer> mDataServer;
    public BankCardAdapter(List<Bank_Card_DataServer> mDataServer){
        this.mDataServer=mDataServer;

    }






    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bank_card, parent, false);
        return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mtv_text.setText(mDataServer.get(position).getText());
        holder.mtv_number.setText(mDataServer.get(position).getNumber());
        holder.mIv_pic.setImageResource(mDataServer.get(position).getPic());



    }

    @Override
    public int getItemCount() {
        return mDataServer==null ? 0:mDataServer.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mtv_text,mtv_number;
        private ImageView mIv_pic;



        public ViewHolder(View itemView) {
            super(itemView);
            mtv_text = itemView.findViewById(R.id.Text);
            mtv_number=itemView.findViewById(R.id.card_number);
            mIv_pic=itemView.findViewById(R.id.pic);


        }
    }


}