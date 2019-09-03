package com.just.rebate.adapter.recycle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.just.rebate.R;
import com.just.rebate.data.DataServer;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class ArrivalDetailsAdapter extends RecyclerView.Adapter<ArrivalDetailsAdapter.ViewHolder> {
    private Context context;
    private List<DataServer> mDataServer;
    public ArrivalDetailsAdapter(List<DataServer> mDataServer){
        this.mDataServer=mDataServer;

    }






    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_arrival_details, parent, false);
        return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mtv_name.setText(mDataServer.get(position).getName());
        holder.mtv_time.setText(mDataServer.get(position).getTime());
        holder.mtv_rebate.setText(mDataServer.get(position).getRebate());
        holder.mtv_detailed.setText(mDataServer.get(position).getDetailed());

    }

    @Override
    public int getItemCount() {
        return mDataServer==null ? 0:mDataServer.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mtv_name, mtv_time, mtv_rebate, mtv_detailed;


        public ViewHolder(View itemView) {
            super(itemView);
            mtv_name = itemView.findViewById(R.id.name);
            mtv_time = itemView.findViewById(R.id.time);
            mtv_rebate = itemView.findViewById(R.id.rebate);
            mtv_detailed = itemView.findViewById(R.id.detailed);

        }
    }


}
