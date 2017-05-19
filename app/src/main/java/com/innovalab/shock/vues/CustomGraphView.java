package com.innovalab.shock.vues;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.jjoe64.graphview.*;

/**
 * Created by innovalab2 on 18/05/2017.
 */

public class CustomGraphView extends com.jjoe64.graphview.GraphView
{
    public CustomGraphView(Context context) {
        super(context);
    }

    public CustomGraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomGraphView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void onDraw(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        float x = 100;
        float y = 0;
        canvas.drawLine(x,y,x,y+200,paint);
    }

}
