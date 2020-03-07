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
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cpstest.R;

import static android.hardware.Sensor.TYPE_GYROSCOPE;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;
    Button gyroscopeBtn;
    Button gravityBtn;

    private EditText ball1X;
    private EditText ball2X;
    private EditText ball1Y;
    private EditText ball2Y;
    private EditText ball1Speed;
    private EditText ball2Speed;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gyroscopeBtn = (Button)findViewById(R.id.gyroscopeBtn);
        gravityBtn = (Button)findViewById(R.id.gravityBtn);

        ball1X = (EditText) findViewById(R.id.ball1X);
        ball1Y = (EditText) findViewById(R.id.ball1Y);
        ball1Speed = (EditText) findViewById(R.id.ball1Speed);
        ball2X = (EditText) findViewById(R.id.ball2X);
        ball2Y = (EditText) findViewById(R.id.ball2Y);
        ball2Speed = (EditText) findViewById(R.id.ball2Speed);


        gravityBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent gravityActivity = new Intent(getBaseContext(), GravityActivity.class);
                gravityActivity = setInputDatas(gravityActivity);
                startActivity(gravityActivity);
            }
        });

        gyroscopeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent gyroscopeActivity = new Intent(getBaseContext(), GyroscopeActivity.class);
                gyroscopeActivity = setInputDatas(gyroscopeActivity);
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

    private Intent setInputDatas(Intent intent){
        intent.putExtra("ball1X", ball1X.getText().toString());
        intent.putExtra("ball2X", ball2X.getText().toString());
        intent.putExtra("ball1Y", ball1Y.getText().toString());

        intent.putExtra("ball2Y", ball2Y.getText().toString());
        intent.putExtra("ball1Speed", ball1Speed.getText().toString());
        intent.putExtra("ball2Speed", ball2Speed.getText().toString());
        return intent;
    }
}

