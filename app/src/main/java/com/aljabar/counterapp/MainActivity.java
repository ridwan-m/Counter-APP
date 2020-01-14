package com.aljabar.counterapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private  final String STATE_COUNT = "state_count"; // konstanta utunk state count
    private final String STATE_LAST_ORIENTATION = "state_last_orientation";

    //pembuatan objek commponent
    TextView tvCount;
    Button btnCount, btnReset;
    int count = 0;// type data Primitive
    //Integer count = 0;// type data Reference

    int last_orientation = 1; //1 for portrait

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//bind ke layout yg akan ditampilkan

        //binding commponent ke view
        tvCount = findViewById(R.id.tv_count);
        btnCount = findViewById(R.id.btn_count);
        btnReset = findViewById(R.id.btn_reset);

        if(getResources().getConfiguration().orientation == 1){ // jika orientasi saat ini adalah portrait
            last_orientation = 2; // last nya adalah landscape
        }

        if(savedInstanceState != null){// cek apakah ada state yang tersimpan ?
            count = savedInstanceState.getInt(STATE_COUNT);
            last_orientation = savedInstanceState.getInt(STATE_LAST_ORIENTATION);
            if(count >= 20){
                //set font size
                if(last_orientation == Configuration.ORIENTATION_LANDSCAPE){
                    //if last is landscape and now is portrait
                    tvCount.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.font_default));
                }
                else{
                    //if last is portrait and now is lands
                    tvCount.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.font_land_default));
                }
            }
            else{
                if(last_orientation == Configuration.ORIENTATION_LANDSCAPE){
                    //if last is landscape and now is portrait
                    tvCount.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.font_non_default));
                }
                else{
                    //if last is portrait and now is lands
                    tvCount.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.font_land_default));
                }
            }
            tvCount.setText(String.valueOf(count));
        }

        //cara 1 untuk set OnClick pada Commponent
        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do somethisg here
                count++;
                if(count == 20){
                    if(last_orientation == Configuration.ORIENTATION_LANDSCAPE){
                        //if last landscape and now in portrait
                        tvCount.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.font_non_default));
                    }
                    else{
                        //if last is portrait and now is landscape
                        tvCount.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.font_land_default));
                    }
                }
                tvCount.setText(String.valueOf(count));//Primitive
                //tvCount.setText(count.toString());
                Log.i("COUNT IS : ",String.valueOf(count));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                if(last_orientation == Configuration.ORIENTATION_LANDSCAPE){
                    //if last is landscape and now portrait
                    tvCount.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.font_land_default));
                }
                else{
                    //if last is portrait and now landscape
                    tvCount.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.font_default));
                }
                tvCount.setText(String.valueOf(count));
                Log.i("RESET IS : ",String.valueOf(count));

            }
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //save state

        outState.putInt(STATE_COUNT,count);
        outState.putInt(STATE_LAST_ORIENTATION,getResources().getConfiguration().orientation);
    }

}
