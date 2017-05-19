package com.innovalab.shock.vues;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.androidplot.Series;
import com.androidplot.xy.BoundaryMode;
import com.innovalab.shock.R;
import com.innovalab.shock.modele.ObjetFrappe;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYSeriesFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by innovalab2 on 10/05/2017.
 */

public class GraphView extends LinearLayout {

    private ObjetFrappe objetFrappe;

    private List<List> listeGraph;
    private ListView lv;
    private com.jjoe64.graphview.GraphView graphView;
    public float[] pointTap;

    public GraphView(Context context) {
        super(context);
        init(context);
    }

    public GraphView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    public GraphView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.graph_view, this, true);
        //lv = (ListView) findViewById(R.id.listViewGraph);
        listeGraph = new ArrayList<List>();

        graphView = (com.jjoe64.graphview.GraphView) findViewById(R.id.graph);
        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setMinY(0);
        graphView.getViewport().setMaxY(255);

        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMinX(0);

        graphView.getViewport().setScalable(true);
    }

    public void setObjetFrappe(ObjetFrappe obj){objetFrappe = obj;}

    public void display(OnDataPointTapListener listener)
    {
        graphView.removeAllSeries();

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(getPoints(objetFrappe.getTemps(), objetFrappe.getforceVecteurFrappeNum()));

        series.setOnDataPointTapListener(listener);

        graphView.addSeries(series);
    }

    private DataPoint[] getPoints(List<Float> x, List<Float> y)
    {
        DataPoint[] points = new DataPoint[x.size()];
        graphView.getViewport().setMaxX(x.size());

        for(int i=0;i<x.size();i++)
        {
            points[i] = new DataPoint(x.get(i),y.get(i));
        }

        return points;
    }
}
