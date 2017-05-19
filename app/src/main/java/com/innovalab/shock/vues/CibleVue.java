package com.innovalab.shock.vues;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.innovalab.shock.modele.ObjetFrappe;
import com.innovalab.shock.modele.Vector3;

import java.util.Random;

import static android.graphics.Color.BLACK;

/**
 * Created by innovalab5 on 11/05/2017.
 */

public class CibleVue extends View {

    private ObjetFrappe objetFrappe;
    private int time = 0;
    public CibleVue(Context context) {
        super(context);
    }

    public CibleVue(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CibleVue(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.rgb(100,108,122));
        canvas.drawCircle(this.getWidth()/2, this.getHeight()/2,210, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setShader(new LinearGradient(0, this.getHeight() / 2, this.getWidth(),
                this.getHeight() /2, Color.rgb(67, 76, 89), Color.rgb(90, 115, 153), Shader.TileMode.MIRROR));
        //paint.setColor(Color.rgb(72,88,114));
        canvas.drawCircle(this.getWidth()/2,this.getHeight()/2, 170, paint);

        paint.setStyle(Paint.Style.FILL);
        //paint.setColor(Color.rgb(80,109,155));
        paint.setShader(new LinearGradient(this.getWidth()/2-50, this.getHeight() / 2, this.getWidth()/2+50, this.getHeight() /2, Color.rgb(84, 102, 130), Color.rgb(112, 133, 165), Shader.TileMode.CLAMP));
        canvas.drawCircle(this.getWidth()/2, this.getHeight()/2, 100, paint);

        if(objetFrappe == null)
            return;
        float[] coord_G = objetFrappe.getG_position(time);
        Log.i("centre"," (x,y) : "+coord_G[0]+" "+coord_G[1]);
        paint.setStyle(Paint.Style.FILL);
        //paint.setColor(Color.rgb(216, 38, 10));
        paint.setShader(new RadialGradient((float)(this.getWidth()/2+coord_G[0]), (float)(this.getHeight()/2+coord_G[1]), 15, Color.rgb(229, 4, 4), Color.TRANSPARENT, Shader.TileMode.CLAMP));
        canvas.drawCircle((float)(this.getWidth()/2+coord_G[0]), (float)(this.getHeight()/2+coord_G[1]), 15, paint);

    }
    public void setFrappePoint(float[] pos_G)
    {
        Paint paint = new Paint();
        Canvas canvas = new Canvas();
        paint.setStyle(Paint.Style.FILL);
        //paint.setColor(Color.rgb(216, 38, 10));
        paint.setShader(new RadialGradient((float)(this.getWidth()/2+pos_G[0]), (float)(this.getHeight()/2+pos_G[1]), 15, Color.rgb(229, 4, 4), Color.TRANSPARENT, Shader.TileMode.CLAMP));
        canvas.drawCircle((float)(this.getWidth()/2+pos_G[0]), (float)(this.getHeight()/2+pos_G[1]), 15, paint);
    }


    public void display(){this.invalidate();}

    public void setObjetFrappe(ObjetFrappe obj){objetFrappe = obj;}
    public void next(){time++;}
    public void setPointOnTime(int t){
        time = t;
    }
}

