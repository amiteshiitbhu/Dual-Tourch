package com.exmple.firebase.dualflash;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private boolean hasFlash;
    private CameraManager camManager;
    private String cameraId;
    private boolean isBackLightOn = true;
    private Button buttonBack;
    private Button buttonFront;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getApplicationContext() != null) {
            hasFlash = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        }

        ToggleButton toggleBack = findViewById(R.id.toggle_back);
        ToggleButton toggleFront = findViewById(R.id.toggle_front);

        buttonBack = findViewById(R.id.back_light_on_off);
        buttonFront = findViewById(R.id.front_light_on_off);

        if (hasFlash) {
            toggleBack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        toggleBackLight(true);
                        buttonBack.setBackgroundColor(Color.YELLOW);
                    } else {
                        toggleBackLight(false);
                        buttonBack.setBackgroundColor(Color.CYAN);
                    }
                }
            });
        }


        if (hasFlash) {
            toggleFront.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        toggleFrontLight(true);
                        buttonFront.setBackgroundColor(Color.YELLOW);
                    } else {
                        toggleFrontLight(false);
                        buttonFront.setBackgroundColor(Color.CYAN);
                    }
                }
            });
        }

    }

    private void toggleFrontLight(boolean state) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            camManager = (CameraManager) MainActivity.this.getSystemService(CAMERA_SERVICE);
            cameraId = null;
            try {
                cameraId = camManager.getCameraIdList()[1];
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    camManager.setTorchMode(cameraId, state);
                }

            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }

//    @Override
//    public void onClick(View v) {
//        if (hasFlash) {
//            switch (v.getId()) {
//                case R.id.back_light_on_off:
//                    isBackLightOn = true;
//
//
//
//
//                    break;
//
//                case R.id.back_light_off:
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        camManager = (CameraManager) MainActivity.this.getSystemService(CAMERA_SERVICE);
//                        cameraId = null;
//                        try {
//                            cameraId = camManager.getCameraIdList()[0];
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                                camManager.setTorchMode(cameraId, false);
//                            }
//                        } catch (CameraAccessException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    break;
//
//                case R.id.front_light_on:
//
//                    break;
//
//                case R.id.front_light_off:
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        camManager = (CameraManager) MainActivity.this.getSystemService(CAMERA_SERVICE);
//                        cameraId = null;
//                        try {
//                            cameraId = camManager.getCameraIdList()[1];
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                                camManager.setTorchMode(cameraId, false);
//                            }
//
//                        } catch (CameraAccessException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    break;
//            }
//        }
//    }

    private void toggleBackLight(boolean state) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            camManager = (CameraManager) MainActivity.this.getSystemService(CAMERA_SERVICE);
            cameraId = null;
            try {
                cameraId = camManager.getCameraIdList()[0];
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    camManager.setTorchMode(cameraId, state);
                }
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
