package com.example.moneyme;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

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
    TextView savings, outcome, free;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        DBHelper db = new DBHelper(this);

        String username = db.getUsername();
        pieChart = findViewById(R.id.pieChart);
        savings = findViewById(R.id.savings);
        outcome = findViewById(R.id.outcome);
        free = findViewById(R.id.free);

        String inc = db.getIncome(username);
        double save = Double.parseDouble(db.getSavings(username))/100.0;
        double expences = Double.parseDouble(db.getExpences(username))/100.0;
        double fre = Double.parseDouble(db.getFree(username))/100.0;

        if(!inc.equals("")){
            double income = Double.parseDouble(inc);
            save = income * save;
            expences  = income * expences;
            fre = income * fre;

            savings.setText(String.format("   %.2f   ", save));
            outcome.setText(String.format("   %.2f   ", expences));
            free.setText(String.format("   %.2f   ", fre));
        }
        else{
            savings.setText("Please ");
            outcome.setText("enter");
            free.setText("an income!");
        }

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