package com.example.database.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;

public class Lined extends AppCompatEditText {

   private Rect mrect;
   private Paint mpaint;


    public Lined(Context context, AttributeSet attrs) {
        super(context, attrs);
        mrect=new Rect();
        mpaint=new Paint();
        mpaint.setStyle(Paint.Style.STROKE);
        mpaint.setStrokeWidth(2);
        mpaint.setColor(0xFFFD966);
    }




    @Override
    protected void onDraw(Canvas canvas) {

       int height=((View)this.getParent()).getHeight();
       int lineHeight=getLineHeight();
        int numofLines=height/lineHeight;
        Rect r=mrect;
        Paint p=mpaint;
        int baseline=getLineBounds(0,r);
        for(int i=0;i<numofLines;i++){
            canvas.drawLine(r.left,baseline+1,r.right,baseline+1,p);
            baseline+=lineHeight;
        }
        super.onDraw(canvas);
    }


}
