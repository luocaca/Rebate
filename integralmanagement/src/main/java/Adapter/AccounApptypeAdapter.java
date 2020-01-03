package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.just.integralmanagement.R;
import com.just.integralmanagement.entity.AccountAppTypeBean;

import java.util.List;

public class AccounApptypeAdapter extends RecyclerView.Adapter<AccounApptypeAdapter.ViewHolder>{
        private List<AccountAppTypeBean> accountAppTypeBeans;
        private Context mContext;
        private OnItemClickListener itemClickListener;


        public interface OnItemClickListener{
            public void onItemClick(View view, int position);
        }

        public AccounApptypeAdapter(Context context, List<AccountAppTypeBean> accountAppTypeBean) {
            super();
            mContext = context;
            this.accountAppTypeBeans = accountAppTypeBean;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_diglo_app_type, parent, false);
            return new ViewHolder(view,itemClickListener);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.mTv_AppTypeName.setText(accountAppTypeBeans.get(position).getAccountAppTypeName());
        }




        @Override
        public int getItemCount() {
            return accountAppTypeBeans.size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private OnItemClickListener onItemClickListener;
            private TextView mTv_AppTypeName;

            public ViewHolder(View view,OnItemClickListener listener) {

                super(view);
                onItemClickListener = listener;
                view.setOnClickListener(this);
                // mImageView = (ImageView) view.findViewById(R.id.avatar);
                mTv_AppTypeName = view.findViewById(R.id.apptypename);
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
