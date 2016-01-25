package com.example.chart;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.animation.AnimationEasing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;
import com.github.mikephil.charting.utils.PercentFormatter;

public class MainActivity extends Activity {

	 	private PieChart mChart;
	    private SeekBar mSeekBarX, mSeekBarY;
	    private TextView tvX, tvY;
	    
	    private Typeface tf;
	   
	    protected String[] mParties = new String[] {
	            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
	            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
	            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
	            "Party Y", "Party Z"
	    };

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	                WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        setContentView(R.layout.activity_main);

	        mChart = (PieChart) findViewById(R.id.chart1);
	        mChart.setUsePercentValues(true);

	        // change the color of the center-hole
	        // mChart.setHoleColor(Color.rgb(235, 235, 235));
	        mChart.setHoleColorTransparent(true);


	        mChart.setHoleRadius(60f);

	        mChart.setDescription("");

	        mChart.setDrawCenterText(true);

	        mChart.setDrawHoleEnabled(true);

	        mChart.setRotationAngle(0);
	        // enable rotation of the chart by touch
	        mChart.setRotationEnabled(true);

	        // mChart.setUnit(" €");
	        // mChart.setDrawUnitsInChart(true);

	        // mChart.setTouchEnabled(false);

	        mChart.setCenterText("MPAndroidChart\nby Philipp Jahoda");

	        setData(3, 100);

	        mChart.animateXY(1500, 1500, AnimationEasing.EasingOption.EaseOutBack);
	        // mChart.spin(2000, 0, 360);

	        Legend l = mChart.getLegend();
	        l.setPosition(LegendPosition.RIGHT_OF_CHART);
	        l.setXEntrySpace(7f);
	        l.setYEntrySpace(5f);
	    }

  

	    private void setData(int count, float range) {

	        float mult = range;

	        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

	        // IMPORTANT: In a PieChart, no values (Entry) should have the same
	        // xIndex (even if from different DataSets), since no values can be
	        // drawn above each other.
	        for (int i = 0; i < count + 1; i++) {
	            yVals1.add(new Entry((float) (Math.random() * mult) + mult / 5, i));
	        }

	        ArrayList<String> xVals = new ArrayList<String>();

	        for (int i = 0; i < count + 1; i++)
	            xVals.add(mParties[i % mParties.length]);

	        PieDataSet dataSet = new PieDataSet(yVals1, "Election Results");
	        dataSet.setSliceSpace(3f);

	        // add a lot of colors

	        ArrayList<Integer> colors = new ArrayList<Integer>();

	        for (int c : ColorTemplate.VORDIPLOM_COLORS)
	            colors.add(c);

	        for (int c : ColorTemplate.JOYFUL_COLORS)
	            colors.add(c);

	        for (int c : ColorTemplate.COLORFUL_COLORS)
	            colors.add(c);

	        for (int c : ColorTemplate.LIBERTY_COLORS)
	            colors.add(c);

	        for (int c : ColorTemplate.PASTEL_COLORS)
	            colors.add(c);

	        colors.add(ColorTemplate.getHoloBlue());

	        dataSet.setColors(colors);

	        PieData data = new PieData(xVals, dataSet);
	        data.setValueFormatter(new PercentFormatter());
	        data.setValueTextSize(11f);
	        data.setValueTextColor(Color.WHITE);
	        data.setValueTypeface(tf);
	        mChart.setData(data);

	        // undo all highlights
	        mChart.highlightValues(null);

	        mChart.invalidate();
	    }


	 

}
