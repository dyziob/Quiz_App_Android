package com.example.projekt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projekt.Repository.Login;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 1;
    private String selectedTopicName = "";
    FirebaseAuth auth;
    TextView textView;
    FirebaseUser user;
    SharedPreferences sharedPreferences;
    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        boolean nightMODE = sharedPreferences.getBoolean("night", false);

        if (nightMODE) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        final LinearLayout android = findViewById(R.id.android);
        final LinearLayout java = findViewById(R.id.java);
        final LinearLayout kotlin = findViewById(R.id.kotlin);
        final LinearLayout csharp = findViewById(R.id.csharp);

        final Button startbtn = findViewById(R.id.start);

        android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopicName = "Android";

                if (nightMODE) {
                    android.setBackgroundResource(R.drawable.round_back_dark_stroke10);
                    java.setBackgroundResource(R.drawable.round_back_dark10);
                    kotlin.setBackgroundResource(R.drawable.round_back_dark10);
                    csharp.setBackgroundResource(R.drawable.round_back_dark10);
                } else {
                    android.setBackgroundResource(R.drawable.round_back_white_stroke10);
                    java.setBackgroundResource(R.drawable.round_back_white10);
                    kotlin.setBackgroundResource(R.drawable.round_back_white10);
                    csharp.setBackgroundResource(R.drawable.round_back_white10);
                }

            }
        });

        java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopicName = "Java";

                if (nightMODE) {
                    android.setBackgroundResource(R.drawable.round_back_dark10);
                    java.setBackgroundResource(R.drawable.round_back_dark_stroke10);
                    kotlin.setBackgroundResource(R.drawable.round_back_dark10);
                    csharp.setBackgroundResource(R.drawable.round_back_dark10);
                } else {
                    android.setBackgroundResource(R.drawable.round_back_white10);
                    java.setBackgroundResource(R.drawable.round_back_white_stroke10);
                    kotlin.setBackgroundResource(R.drawable.round_back_white10);
                    csharp.setBackgroundResource(R.drawable.round_back_white10);
                }
            }
        });

        kotlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopicName = "Kotlin";

                if (nightMODE) {
                    android.setBackgroundResource(R.drawable.round_back_dark10);
                    java.setBackgroundResource(R.drawable.round_back_dark10);
                    kotlin.setBackgroundResource(R.drawable.round_back_dark_stroke10);
                    csharp.setBackgroundResource(R.drawable.round_back_dark10);
                } else {
                    android.setBackgroundResource(R.drawable.round_back_white10);
                    java.setBackgroundResource(R.drawable.round_back_white10);
                    kotlin.setBackgroundResource(R.drawable.round_back_white_stroke10);
                    csharp.setBackgroundResource(R.drawable.round_back_white10);
                }
            }
        });

        csharp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopicName = "C#";

                if (nightMODE) {
                    android.setBackgroundResource(R.drawable.round_back_dark10);
                    java.setBackgroundResource(R.drawable.round_back_dark10);
                    kotlin.setBackgroundResource(R.drawable.round_back_dark10);
                    csharp.setBackgroundResource(R.drawable.round_back_dark_stroke10);
                } else {
                    android.setBackgroundResource(R.drawable.round_back_white10);
                    java.setBackgroundResource(R.drawable.round_back_white10);
                    kotlin.setBackgroundResource(R.drawable.round_back_white10);
                    csharp.setBackgroundResource(R.drawable.round_back_white_stroke10);
                }
            }
        });

        // Initialize BluetoothAdapter
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // Add long click listeners for sharing via Bluetooth
        android.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                shareTopicViaBluetooth("Android");
                return true;
            }
        });

        java.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                shareTopicViaBluetooth("Java");
                return true;
            }
        });

        kotlin.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                shareTopicViaBluetooth("Kotlin");
                return true;
            }
        });

        csharp.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                shareTopicViaBluetooth("C#");
                return true;
            }
        });

        //start quiz button
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTopicName.isEmpty()) {
                    Toast.makeText(MainActivity.this, getString(R.string.TopicNoSelect), Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                    intent.putExtra("SelectedTopic", selectedTopicName);
                    startActivity(intent);
                }
            }
        });

        //Admob banner code
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                // on below line displaying a log that admob ads has been initialized.
                Log.i("Admob", "Admob Initialized.");
            }
        });

        // on below line creating and initializing variable for adView.
        AdView adView = findViewById(R.id.adView);
        // on below line creating and initializing variable for adRequest
        AdRequest adRequest = new AdRequest.Builder().build();
        // on below line loading request inside our adview.
        adView.loadAd(adRequest);

        //logout
        auth = FirebaseAuth.getInstance();
        textView = findViewById(R.id.user_details);
        user = auth.getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        } else {
            textView.setText(user.getEmail());
        }

        //setting button
        findViewById(R.id.settingsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*FirebaseAuth.getInstance().signOut();*/
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void shareTopicViaBluetooth(String topicName) {
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not supported on this device", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        } else {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "Check out this quiz on " + topicName + "!");
            intent.setPackage("com.android.bluetooth"); // Ensure the intent is sent via Bluetooth
            startActivity(Intent.createChooser(intent, "Share via"));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BT && resultCode == RESULT_OK) {
            Toast.makeText(this, "Bluetooth enabled", Toast.LENGTH_SHORT).show();
        } else if (requestCode == REQUEST_ENABLE_BT && resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Bluetooth not enabled", Toast.LENGTH_SHORT).show();
        }
    }
}
