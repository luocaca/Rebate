package me.luocaca.rebate.adapter.recycle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.luocaca.rebate.R;

public class ArrivalDetailsAdapter extends RecyclerView.Adapter<ArrivalDetailsAdapter.ViewHolder> {
    private Context context;
    private List<String> list;

    public ArrivalDetailsAdapter(Context context,List<String> list){
        this.context = context;
        this.list =list;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_arrival_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mtv_name.setText("京东购物");
        holder.mtv_time.setText("2019-07-31");
        holder.mtv_rebate.setText("返利 2.62元");
        holder.mtv_detailed.setText("+2.62元");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mtv_name ,mtv_time,mtv_rebate,mtv_detailed;


        public ViewHolder(View itemView) {
            super(itemView);
            mtv_name = itemView.findViewById(R.id.name);
            mtv_time = itemView.findViewById(R.id.time);
            mtv_rebate = itemView.findViewById(R.id.rebate);
            mtv_detailed = itemView.findViewById(R.id.detailed);

        }
    }

 
}
