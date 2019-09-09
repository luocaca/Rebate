package com.just.rebate.wedget;



import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import me.luocaca.wedget.R;
class RecyclerviewCornerRdius extends RecyclerView.ItemDecoration{

    private int wight;
    private int height;
    private int item_height;
    private int item_padding;
    private Paint paint;


    public RecyclerviewCornerRdius(Context context){
        wight=context.getResources().getDisplayMetrics().widthPixels;
        height=context.getResources().getDisplayMetrics().heightPixels;
        paint=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);

    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }
}

