package com.example.moneyme;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Progress extends AppCompatActivity {
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        DBHelper db = new DBHelper(this);

        String username = db.getUsername();
        pieChart = findViewById(R.id.pieChart);

        ArrayList<PieEntry> votes = new ArrayList<>();
        votes.add(new PieEntry(Integer.parseInt(db.getVerySatisfied(username)), "Perfect"));
        votes.add(new PieEntry(Integer.parseInt(db.getSatisfied(username)), "Good"));
        votes.add(new PieEntry(Integer.parseInt(db.getDissatisfied(username)), "Bad"));
        votes.add(new PieEntry(Integer.parseInt(db.getVeryDissatisfied(username)), "Very bad"));

        PieDataSet pieDataSet = new PieDataSet(votes, "| Votes");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Votes 2021");
        pieChart.animate();

    }
}