package com.medconnect.emergency;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import com.android.volley.*;
import com.android.volley.toolbox.*;

import java.util.*;

public class PatientDashboard extends AppCompatActivity {

    Button btnEmergency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_dashboard);

        btnEmergency = findViewById(R.id.btnEmergency);

        btnEmergency.setOnClickListener(v -> sendEmergency());
    }

    private void sendEmergency() {

        String URL = "https://bluffable-unacquisitive-magan.ngrok-free.dev/api/emergency/mobile-alert";

        RequestQueue queue = Volley.newRequestQueue(PatientDashboard.this);

        StringRequest req = new StringRequest(Request.Method.POST, URL,
                response -> Toast.makeText(this, "Emergency Sent!", Toast.LENGTH_SHORT).show(),
                error -> Toast.makeText(this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show()
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> p = new HashMap<>();
                p.put("lat", "12.97");
                p.put("lng", "77.59");
                p.put("message", "Patient emergency!");
                return p;
            }
        };

        queue.add(req);
    }
}
