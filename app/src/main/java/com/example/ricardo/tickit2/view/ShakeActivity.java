
package com.example.ricardo.tickit2.view;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.ricardo.tickit2.R;

public class ShakeActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;
    private ShakeOption shakeOption = ShakeOption.SHOW;
    private boolean isShaking = false;

    @Override
    public void onSensorChanged(SensorEvent event) {
        int type = event.sensor.getType();
        if (type == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            if (Math.abs(x) > 17 || Math.abs(y) > 17 || Math.abs(z) > 17 || !isShaking) {
                isShaking = true;
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        Log.d("ShakeActivity", "shaking!!!");
                        // TODO showShakeResult(shakeOption);
                    }
                };
                thread.start();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    enum ShakeOption {
        SHOW
    }

    private void initSensor() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initSensor();
        Button button1 = (Button) findViewById(R.id.shake_button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shakeOption = shakeOption.SHOW;
            }
        });
        // TODO button 2 & 3
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);
    }

    @Override
    protected void onPause() {
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
        super.onPause();
    }
}
