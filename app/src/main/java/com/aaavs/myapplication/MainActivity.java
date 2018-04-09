package com.aaavs.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static butterknife.ButterKnife.findById;

public class MainActivity extends AppCompatActivity {
//    @BindView(R.id.etTotalBill) EditText editTextTotalBill;
    EditText editTextTotalBill;
    Float totalBill;
    Float tip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(MainActivity.this, NewScreenActivity.class);
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
            }
        }, 5000);
//        ButterKnife.bind(this);

        editTextTotalBill = findViewById(R.id.etTotalBill);
        // Tip percentage Seekbar
        final SeekBar seekBarTip = findViewById(R.id.seekBarTip);
        seekBarTip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            ;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tip = Float.valueOf(seekBar.getProgress());
                TextView textViewPercent = findViewById(R.id.tvTipPercent);
                textViewPercent.setText(String.valueOf(tip+ "%"));
                TipCalculate();
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                tip = Float.valueOf(seekBar.getProgress());

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    private void TipCalculate() {
        TextView tvTipResult = findViewById(R.id.tvTipResult);
        totalBill = Float.parseFloat(editTextTotalBill.getText().toString());
        Float tipResult = tip*totalBill/100;
        tvTipResult.setText(String.valueOf(tipResult));
        Float totalAmount = tipResult+totalBill;
        TextView tv_totalAmount = findViewById(R.id.tv_totalAmount);
        tv_totalAmount.setText(String.valueOf(totalAmount));
//        Log.i("Str", String.valueOf(totalAmount));

    }
}
