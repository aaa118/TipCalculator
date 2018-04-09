package com.aaavs.myapplication;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editTextTotalBill;
    Float totalBill;
    Float tip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        editTextTotalBill = findViewById(R.id.etTotalBill);
        // Tip percentage Seekbar
        final SeekBar seekBarTip = findViewById(R.id.seekBarTip);
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
//            totalBill = 0.0F;
            Toast.makeText(MainActivity.this, "Enter an amount", Toast.LENGTH_SHORT).show();
        }

        


    }
}
