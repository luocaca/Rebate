package com.just.rebate.adapter.recycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.just.rebate.R;
import com.just.rebate.data.Chat_Msg_DataServer;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private List<Chat_Msg_DataServer> msgs;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat,parent ,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chat_Msg_DataServer msg = msgs.get(position);
        if (msg.getType() == Chat_Msg_DataServer.TYPE_RECEIVED){
            holder.LeftLayout.setVisibility(View.VISIBLE);
            holder.RightLayout.setVisibility(View.GONE);
            holder.LeftImageView.setImageResource(msg.getImage());
            holder.LeftTextView.setText(msg.getContent());
        } else if(msg.getType()== Chat_Msg_DataServer.TYPE_SENT){
            holder.LeftLayout.setVisibility(View.GONE);
            holder.RightLayout.setVisibility(View.VISIBLE);
            holder.RightImageView.setImageResource(msg.getImage());
            holder.RightTextView.setText(msg.getContent());
        }

    }

    @Override
    public int getItemCount() {
        return msgs.size();
    }

    public ChatAdapter(List<Chat_Msg_DataServer> msgList) {
        msgs = msgList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout LeftLayout;
        LinearLayout RightLayout;
        ImageView LeftImageView;
        ImageView RightImageView;
        TextView LeftTextView;
        TextView RightTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            LeftLayout = itemView.findViewById(R.id.left_layout);
            RightLayout = itemView.findViewById(R.id.right_layout);
            LeftImageView = itemView.findViewById(R.id.left_img);
            RightImageView = itemView.findViewById(R.id.right_img);
            LeftTextView = itemView.findViewById(R.id.left_msg);
            RightTextView = itemView.findViewById(R.id.right_msg);

        }
    }

}
