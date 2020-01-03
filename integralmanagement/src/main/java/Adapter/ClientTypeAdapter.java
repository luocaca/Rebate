package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.just.integralmanagement.R;
import com.just.integralmanagement.entity.ClientTypeBean;

import java.util.List;

public class ClientTypeAdapter extends RecyclerView.Adapter<ClientTypeAdapter.ViewHolder>{
        private List<ClientTypeBean> clientTypeBeans;
        private Context mContext;
        private OnItemClickListener itemClickListener;


        public interface OnItemClickListener{
            public void onItemClick(View view, int position);
        }

        public ClientTypeAdapter(Context context, List<ClientTypeBean> clientTypeBean) {
            super();
            mContext = context;
            this.clientTypeBeans = clientTypeBean;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_diglog_client_type, parent, false);
            return new ViewHolder(view,itemClickListener);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.mTv_clientType.setText(clientTypeBeans.get(position).getClientType());
        }




        @Override
        public int getItemCount() {
            return clientTypeBeans.size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private OnItemClickListener onItemClickListener;
            private TextView mTv_clientType;

            public ViewHolder(View view,OnItemClickListener listener) {
                super(view);
                onItemClickListener = listener;
                view.setOnClickListener(this);
                // mImageView = (ImageView) view.findViewById(R.id.avatar);
                mTv_clientType = view.findViewById(R.id.clientType);
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
