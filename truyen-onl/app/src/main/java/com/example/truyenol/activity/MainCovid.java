package com.example.truyenol.activity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.truyenol.R;
import com.example.truyenol.api.ApiUtilities;
import com.example.truyenol.api.CountryData;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainCovid extends AppCompatActivity {

    TextView totalConfirm,totalRecovered,totalActive,totalDeath,totalTests;
    TextView todayConfirm,todayDeath,todayRecovered,todayActive,todayTests,dateTV;
    PieChart pieChart;
    private List<CountryData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_covid);
        list = new ArrayList<>();
        ApiUtilities.getApiInterface().getCountryData()
                .enqueue(new Callback<List<CountryData>>() {
                    @Override
                    public void onResponse(Call<List<CountryData>> call, Response<List<CountryData>> response) {
                        list.addAll(response.body());
                        for (int i = 0;i <list.size();i++){
                            if (list.get(i).getCountry().equals("Vietnam")){
                                int confirm = Integer.parseInt(list.get(i).getCases());
                                int active = Integer.parseInt(list.get(i).getActive());
                                int recovered = Integer.parseInt(list.get(i).getRecovered());
                                int death = Integer.parseInt(list.get(i).getDeaths());
                                int test = Integer.parseInt(list.get(i).getTests());


                                totalConfirm.setText(NumberFormat.getInstance().format(confirm));
                                totalRecovered.setText(NumberFormat.getInstance().format(recovered));
                                totalDeath.setText(NumberFormat.getInstance().format(death));
                                totalActive.setText(NumberFormat.getInstance().format(active));
                                totalTests.setText(NumberFormat.getInstance().format(test));


                                todayConfirm.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayCases())));
                                todayActive.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getActive())));
                                todayDeath.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayDeaths())));
                                todayRecovered.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayRecovered())));
//                                todayTests.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayTests())));

                                setText(list.get(i).getUpdated());


                                pieChart.addPieSlice(new PieModel("Confirm",confirm,getResources().getColor(R.color.yellow)));
                                pieChart.addPieSlice(new PieModel("Recovered",recovered,getResources().getColor(R.color.green_pie)));
                                pieChart.addPieSlice(new PieModel("Death",death,getResources().getColor(R.color.red_pie)));
                                pieChart.addPieSlice(new PieModel("Active",active,getResources().getColor(R.color.blue_pie)));

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CountryData>> call, Throwable t) {
                        Toast.makeText(MainCovid.this,"Error:"+t.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
        AnhXa();
    }

    private void setText(String updated) {
        DateFormat format = new SimpleDateFormat("MMM dd, yyyy");
        long milliseconds = Long.parseLong(updated);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        dateTV.setText("Uploaded at " +format.format(calendar.getTime()));
    }

    private void AnhXa(){
        totalActive = findViewById(R.id.totalActive);
        totalConfirm = findViewById(R.id.totalConfirm);
        totalDeath = findViewById(R.id.totalDeath);
        totalRecovered = findViewById(R.id.totalRecovered);
        totalTests = findViewById(R.id.totalTests);
        todayConfirm = findViewById(R.id.todayConfirm);
        todayRecovered = findViewById(R.id.todayRecovered);
        todayDeath = findViewById(R.id.todayDeath);
        todayActive = findViewById(R.id.todayActive);
        todayTests = findViewById(R.id.todayTests);
        pieChart = findViewById(R.id.pieChart);
        dateTV = findViewById(R.id.date);

    }
}