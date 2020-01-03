package com.just.integralmanagement;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.just.integralmanagement.entity.ApprenticeSloganData;

import java.util.ArrayList;
import java.util.List;

import Adapter.ApprenticeSloganAdapter;

public class ApprenticeSloganActivity extends AppCompatActivity {

    private RecyclerView mRv_Slogan;
    private List<ApprenticeSloganData> apprenticeSloganData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apprentice_slogan);
        getSupportActionBar().setTitle("收徒宣传语");
        setToolbars();
        mRv_Slogan = findViewById(R.id.mRv_Slogan);
        initRecyclerviewData();
        initRecyclerview();
    }

    private void initRecyclerviewData() {
        ApprenticeSloganData apprenticeSloganData0 = new ApprenticeSloganData();
        apprenticeSloganData0.setSlogan("来跟我赚钱吧，躺赚的那种!");
        apprenticeSloganData.add(apprenticeSloganData0);

        ApprenticeSloganData apprenticeSloganData1 = new ApprenticeSloganData();
        apprenticeSloganData1.setSlogan("我发现了一个可以躺赚的APP，快来跟我一起赚钱吧!");
        apprenticeSloganData.add(apprenticeSloganData1);

        ApprenticeSloganData apprenticeSloganData2 = new ApprenticeSloganData();
        apprenticeSloganData2.setSlogan("躺赚的方法除了收租还有这个!");
        apprenticeSloganData.add(apprenticeSloganData2);

        ApprenticeSloganData apprenticeSloganData3 = new ApprenticeSloganData();
        apprenticeSloganData3.setSlogan("想成为一代宗师吗?来试试这个吧!");
        apprenticeSloganData.add(apprenticeSloganData3);

        ApprenticeSloganData apprenticeSloganData4 = new ApprenticeSloganData();
        apprenticeSloganData4.setSlogan("我有个赚钱的秘密想和你分享......");
        apprenticeSloganData.add(apprenticeSloganData4);

        ApprenticeSloganData apprenticeSloganData5 = new ApprenticeSloganData();
        apprenticeSloganData5.setSlogan("来不及解释了，快上车!");
        apprenticeSloganData.add(apprenticeSloganData5);

    }

    private void initRecyclerview() {
        mRv_Slogan.setLayoutManager(new LinearLayoutManager(this));
        ApprenticeSloganAdapter apprenticeSloganAdapter = new ApprenticeSloganAdapter(apprenticeSloganData, this);
        mRv_Slogan.setAdapter(apprenticeSloganAdapter);
    }

    private void setToolbars() {
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);//这两句就可以让actionBar的图标可以响应点击事件
        mActionBar.setDisplayHomeAsUpEnabled(true);//这一句主要用于后面返回效果，后面会讲
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:  //id不要写错，前面要加android
                onBackPressed();
                break;
        }
        return true;
    }
}
