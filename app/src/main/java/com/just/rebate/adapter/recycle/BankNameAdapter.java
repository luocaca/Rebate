package com.just.rebate.adapter.recycle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.just.rebate.R;
import com.just.rebate.entity.AddCardBean;

import java.util.List;

public class BankNameAdapter extends RecyclerView.Adapter<BankNameAdapter.ViewHolder>{
    private List<AddCardBean> addCardBeans;
    private Context mContext;
    private OnItemClickListener itemClickListener;


    public interface OnItemClickListener{
        public void onItemClick(View view, int position);
    }

    public BankNameAdapter(Context context, List<AddCardBean> cardBeans) {
        super();
        mContext = context;
        this.addCardBeans = cardBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_diglog_bank_name, parent, false);
        return new ViewHolder(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTv_BankName.setText(addCardBeans.get(position).BankName);
    }




    @Override
    public int getItemCount() {
        return addCardBeans.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private OnItemClickListener onItemClickListener;
        private TextView mTv_BankName;

        public ViewHolder(View view,OnItemClickListener listener) {
            super(view);
            onItemClickListener = listener;
            view.setOnClickListener(this);
            // mImageView = (ImageView) view.findViewById(R.id.avatar);
            mTv_BankName = view.findViewById(R.id.diglog_item_BankName);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(view, getAdapterPosition());
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.itemClickListener=listener;
    }
}
