package com.just.rebate.ui.activity;



import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.just.rebate.data.DataServer;
import com.just.rebate.R;
import com.just.rebate.adapter.recycle.ArrivalDetailsAdapter;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;



public class ArrivalDetailsActivity extends AppCompatActivity {

    @BindView(R.id.rv_list3)
    RecyclerView mrecyclerView;
    private List<DataServer> mDataServer=new ArrayList<>();

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_arrival_details);

        initRecyclerview();
        initData();

    }


    private void initRecyclerview(){
        mrecyclerView=findViewById(R.id.rv_list3);
        ArrivalDetailsAdapter mArrivalDetailsAdapter=new ArrivalDetailsAdapter(mDataServer);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mrecyclerView.addItemDecoration(new MyDecoration());
        mrecyclerView.setAdapter(mArrivalDetailsAdapter);
}

    private void initData() {
        DataServer dataServer=new DataServer();
        dataServer.setName("淘宝购物");
        dataServer.setTime("2019-7-31");
        dataServer.setRebate("返利  2.62元");
        dataServer.setDetailed("+ 2.62元");
        mDataServer.add(dataServer);


        DataServer dataServer1=new DataServer();
        dataServer1.setName("京东购物");
        dataServer1.setTime("2019-7-31");
        dataServer1.setRebate("返利  1.32元");
        dataServer1.setDetailed("+ 1.32元");
        mDataServer.add(dataServer1);


        DataServer dataServer2=new DataServer();
        dataServer2.setName("美团外卖");
        dataServer2.setTime("2019-8-1");
        dataServer2.setRebate("返利  2.62元");
        dataServer2.setDetailed("+ 2.62元");
        mDataServer.add(dataServer2);

        DataServer dataServer3=new DataServer();
        dataServer3.setName("淘宝购物");
        dataServer3.setTime("2019-8-2");
        dataServer3.setRebate("返利  3.29元");
        dataServer3.setDetailed("+ 3.29元");
        mDataServer.add(dataServer3);

        DataServer dataServer4=new DataServer();
        dataServer4.setName("邀请好友");
        dataServer4.setTime("2019-8-2");
        dataServer4.setRebate("奖励  10.00元");
        dataServer4.setDetailed("+ 10.00元");
        mDataServer.add(dataServer4);


        DataServer dataServer5=new DataServer();
        dataServer5.setName("邀请好友");
        dataServer5.setTime("2019-8-2");
        dataServer5.setRebate("奖励  10.00元");
        dataServer5.setDetailed("+ 10.00元");
        mDataServer.add(dataServer5);


        DataServer dataServer6=new DataServer();
        dataServer6.setName("淘宝购物");
        dataServer6.setTime("2019-8-3");
        dataServer6.setRebate("返利  2.62元");
        dataServer6.setDetailed("+ 2.62元");
        mDataServer.add(dataServer6);

        DataServer dataServer7=new DataServer();
        dataServer7.setName("邀请好友");
        dataServer7.setTime("2019-3-04");
        dataServer7.setRebate("奖励  2.00元");
        dataServer7.setDetailed("+ 2.00元");
        mDataServer.add(dataServer7);

        DataServer dataServer8=new DataServer();
        dataServer8.setName("淘宝购物");
        dataServer8.setTime("2019-7-31");
        dataServer8.setRebate("返利  2.62元");
        dataServer8.setDetailed("+ 2.62元");
        mDataServer.add(dataServer8);

    }

    class MyDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0, 0, 0, getResources().getDimensionPixelOffset(R.dimen.dibufengex));

        }
    }
}
