package com.just.rebate.adapter.recycle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.just.rebate.R;
import com.just.rebate.entity.OrderListData;
import com.rebate.commom.util.bitmap.GlideRoundTransform;

import java.text.DecimalFormat;
import java.util.List;

public class FragmentOrderAdapter extends RecyclerView.Adapter<FragmentOrderAdapter.ViewHolder> {
    private List<OrderListData.RowsBean> rowsBeans;
    private Context mContext;

    public FragmentOrderAdapter(List<OrderListData.RowsBean>rowsBean,Context context){
        this.rowsBeans=rowsBean;
        this.mContext=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orderall,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FragmentOrderAdapter.ViewHolder holder, int position) {
        holder.mTv_PlatformName.setText(rowsBeans.get(position).OrderPlatformName);
        holder.mTv_PaymentExpried.setText(rowsBeans.get(position).PaymentExpried);
        DecimalFormat df   = new DecimalFormat("######0.00");
        holder.mTv_Amount.setText(df.format(rowsBeans.get(position).Amount)+"");
        holder.mTv_order_name.setText(rowsBeans.get(position).OrderItems.get(position).ProductName);
        holder.mTv_SpecName.setText(rowsBeans.get(position).OrderItems.get(position).SpecName);
//        holder.mTv_rich_moeny
        RequestOptions myOptions = new RequestOptions()
//                        .transform(DrawableTransitionOptions.with(drawableCrossFadeFactory))
                .transform(new GlideRoundTransform(holder.itemView.getContext(), 6))
//                      .centerCrop()
                ;

        DrawableCrossFadeFactory drawableCrossFadeFactory = new DrawableCrossFadeFactory.Builder(100).setCrossFadeEnabled(true).build();
//                Glide.with(ZZSearch2UpActivity.this)
//                        .load("")
//                        .transition(DrawableTransitionOptions.with(drawableCrossFadeFactory))
//                ;
        Glide.with(holder.itemView.getContext()).load(rowsBeans.get(position).OrderItems.get(position).Image).transition(DrawableTransitionOptions.with(drawableCrossFadeFactory)).apply(myOptions).into(holder.mIv_order);
    }

    @Override
    public int getItemCount() {
        return rowsBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTv_PlatformName,mTv_PaymentExpried,mTv_rich_moeny,mTv_order_name,mTv_Amount,mTv_SpecName;
        private ImageView mIv_order;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTv_PlatformName=itemView.findViewById(R.id.OrderPlatformName);
            mTv_PaymentExpried=itemView.findViewById(R.id.PaymentExpried);
            mTv_rich_moeny=itemView.findViewById(R.id.rich_moeny);
            mTv_order_name=itemView.findViewById(R.id.order_name);
            mTv_Amount=itemView.findViewById(R.id.Amount);
            mTv_SpecName=itemView.findViewById(R.id.SpecName);
            mIv_order=itemView.findViewById(R.id.order_iv);
        }
    }
}
