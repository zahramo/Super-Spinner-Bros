package com.example.cpstest;

import android.content.Intent;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cpstest.R;

import static android.hardware.Sensor.TYPE_GYROSCOPE;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;
    Button gyroscopeBtn;
    Button gravityBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gyroscopeBtn = (Button)findViewById(R.id.gyroscopeBtn);
        gravityBtn = (Button)findViewById(R.id.gravityBtn);

        gravityBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent gravityActivity = new Intent(getBaseContext(), GravityActivity.class);
                startActivity(gravityActivity);
            }
        });

        gyroscopeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent gyroscopeActivity = new Intent(getBaseContext(), GyroscopeActivity.class);
                startActivity(gyroscopeActivity);
            }
        });


//        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        sensor = sensorManager.getDefaultSensor(TYPE_GYROSCOPE);
//        tv = findViewById(R.id.tv1);

    }


    @Override
    protected void onResume() {
        super.onResume();
       // sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //tv.setText("X:" + event.values[0] + ", Y:" + event.values[1] + ", Z:" + event.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

