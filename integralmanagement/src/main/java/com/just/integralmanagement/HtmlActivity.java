package com.just.integralmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class HtmlActivity extends AppCompatActivity {
    private TextView mTv_HtmlText;
    private String string,string2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acrivity_html);
        mTv_HtmlText=findViewById(R.id.htmlText);
        initReceveData();
        setToolbars();
        URLImageGetter ReviewImgGetter = new URLImageGetter(this, mTv_HtmlText);
        mTv_HtmlText.setText(Html.fromHtml(string,ReviewImgGetter,null));
    }

    private void initReceveData() {
        Intent intent=getIntent();
        string=intent.getStringExtra("HtmlText");
        string2=intent.getStringExtra("HtmlTitle");
    }

    private void setToolbars() {
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setTitle(string2);
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
