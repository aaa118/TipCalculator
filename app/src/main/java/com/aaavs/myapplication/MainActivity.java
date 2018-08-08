package com.aaavs.myapplication;


import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import static android.app.ActivityManager.*;

public class MainActivity extends AppCompatActivity {
    EditText editTextTotalBill;
    Float totalBill;
    Float tip;
    SeekBar seekBarTip;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBarTip = findViewById(R.id.seekBarTip);


//        String packageName =  ProcessManager.getRunningForegroundApps(getApplicationContext()).get(0).getPackageName();



//
//        ActivityManager activityManager = (ActivityManager) this.getSystemService( ACTIVITY_SERVICE );
//        List<AppTask> procInfos = activityManager.getAppTasks();
//
//        Log.i(TAG, "onCreate: "+procInfos);





        editTextTotalBill = findViewById(R.id.etTotalBill);
        editTextTotalBill.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i(TAG, "beforeTextChanged: ");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i(TAG, "onTextChanged: ");


            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i(TAG, "afterTextChanged: ");
                TipCalculate();

            }
        });

        // Tip percentage Seekbar

        seekBarTip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tip = (float) seekBar.getProgress();
                TextView textViewPercent = findViewById(R.id.tvTipPercent);
                textViewPercent.setText(String.valueOf(tip+ "%"));
                TipCalculate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                tip = (float) seekBar.getProgress();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        while (true) {
            final int PROCESS_STATE_TOP = 2;
            ActivityManager.RunningAppProcessInfo currentInfo = null;
            Field field = null;
            try {
                field = ActivityManager.RunningAppProcessInfo.class.getDeclaredField("processState");
            } catch (Exception ignored) {
            }
            ActivityManager am = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> appList = am.getRunningAppProcesses();
            Log.i(TAG, "onCreate: " + appList.size());
            for (ActivityManager.RunningAppProcessInfo app : appList) {
                if (app.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                        && app.importanceReasonCode == ActivityManager.RunningAppProcessInfo.REASON_UNKNOWN) {
                    Integer state = null;
                    try {
                        state = field.getInt(app);
                    } catch (Exception e) {
                    }
                    if (state != null && state == PROCESS_STATE_TOP) {
                        currentInfo = app;
                        Log.i(TAG, "onCreate: " + currentInfo.pkgList);
                        break;
                    }
                }
            }
        }



    }


    private void TipCalculate() {
        TextView tvTipResult = findViewById(R.id.tvTipResult);
        try {
            totalBill = Float.parseFloat(editTextTotalBill.getText().toString());
            Float tipResult = tip*totalBill/100;
            tvTipResult.setText(String.valueOf(tipResult));
            Float totalAmount = tipResult+totalBill;
            TextView tv_totalAmount = findViewById(R.id.tv_totalAmount);
            tv_totalAmount.setText(String.valueOf(totalAmount));
        } catch (NumberFormatException | NullPointerException e){
            Toast.makeText(MainActivity.this, "Enter an amount", Toast.LENGTH_SHORT).show();
        }

        


    }
}
