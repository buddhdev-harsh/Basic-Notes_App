package com.example.database.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class itemspacing extends RecyclerView.ItemDecoration {

    private final int verticleHeight;

    public itemspacing(int verticleHeight) {
        this.verticleHeight = verticleHeight;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom=verticleHeight;
    }
}
