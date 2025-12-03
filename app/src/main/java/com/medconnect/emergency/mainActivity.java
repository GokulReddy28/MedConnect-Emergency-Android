package com.medconnect.emergency;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.medconnect.emergency.api.ApiClient;
import com.medconnect.emergency.api.ApiInterface;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mainActivity extends AppCompatActivity {

    Button emergencyButton;
    TextView txtStatusMessage;

    private static final int PERMISSION_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emergencyButton = findViewById(R.id.emergencyButton);
        txtStatusMessage = findViewById(R.id.txtStatusMessage);

        emergencyButton.setOnClickListener(v -> {

            // 1️⃣ CHECK CALL PERMISSION
            if (!hasPermission(Manifest.permission.CALL_PHONE)) {
                requestPermission(Manifest.permission.CALL_PHONE);
                return;
            }

            // 2️⃣ CHECK LOCATION PERMISSION
            if (!hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
                requestPermission(Manifest.permission.ACCESS_FINE_LOCATION);
                return;
            }

            // 3️⃣ GET GPS LOCATION
            GPSTracker gps = new GPSTracker(mainActivity.this);

            if (!gps.canGetLocation()) {
                gps.showSettingsAlert();
                return;
            }

            double lat = gps.getLatitude();
            double lng = gps.getLongitude();

            String gpsLocation = lat + "," + lng;
            txtStatusMessage.setText("📍 Location: " + gpsLocation);

            // 4️⃣ MAKE CALL TO POLICE
            makePoliceCall();

            // 5️⃣ SEND EMERGENCY ALERT TO SERVER
            sendEmergencyToServer(gpsLocation);
        });
    }

    // 🚨 SEND EMERGENCY DATA TO BACKEND
    private void sendEmergencyToServer(String location) {

        ApiInterface api = ApiClient.getApi();

        Map<String, Object> data = new HashMap<>();
        data.put("patientId", 1);
        data.put("patientName", "Gokul");
        data.put("patientContact", "+919876543210");
        data.put("location", location);
        data.put("emergencyType", "ACCIDENT");
        data.put("details", "Triggered from mobile app (GPS Enabled)");
        data.put("doctorAssigned", "Dr. Test");
        data.put("hospitalAssigned", "Apollo");

        api.triggerEmergency(data).enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                txtStatusMessage.setText("🚑 Emergency sent with GPS!");
                Toast.makeText(mainActivity.this, "GPS Emergency Sent!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                txtStatusMessage.setText("❌ Failed to send alert");
            }
        });
    }

    // 🚓 CALL POLICE
    private void makePoliceCall() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:+911234567890"));
        startActivity(callIntent);
    }

    // CHECK PERMISSION
    private boolean hasPermission(String permission) {
        return ActivityCompat.checkSelfPermission(this, permission)
                == PackageManager.PERMISSION_GRANTED;
    }

    // REQUEST PERMISSION
    private void requestPermission(String permission) {
        ActivityCompat.requestPermissions(
                this,
                new String[]{permission},
                PERMISSION_CODE
        );
    }

    // HANDLE PERMISSION RESULT
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted. Press button again!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission required!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
