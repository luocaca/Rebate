package com.just.rebate.adapter.recycle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.just.rebate.R;
import com.just.rebate.data.detailed_DataServer;

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {
    private Context context;
    private List<detailed_DataServer> mDataServer;
    public DetailsAdapter(List<detailed_DataServer> mDataServer){
        this.mDataServer=mDataServer;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detailed, parent, false);
        return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mtv_text.setText(mDataServer.get(position).getText());


    }

    @Override
    public int getItemCount() {
        return mDataServer==null ? 0:mDataServer.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mtv_text;


        public ViewHolder(View itemView) {
            super(itemView);
            mtv_text = itemView.findViewById(R.id.Text);


        }
    }


}
