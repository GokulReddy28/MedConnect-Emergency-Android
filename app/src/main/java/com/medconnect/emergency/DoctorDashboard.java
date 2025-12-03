package com.medconnect.emergency;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import com.android.volley.*;
import com.android.volley.toolbox.*;

public class DoctorDashboard extends AppCompatActivity {

    Button btnAcceptCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dashboard);

        btnAcceptCase = findViewById(R.id.btnAcceptCase);

        btnAcceptCase.setOnClickListener(v -> acceptCase());
    }

    private void acceptCase() {

        String URL = "https://bluffable-unacquisitive-magan.ngrok-free.dev/api/emergency/accept/1";

        RequestQueue queue = Volley.newRequestQueue(DoctorDashboard.this);

        StringRequest req = new StringRequest(Request.Method.PUT, URL,
                response -> Toast.makeText(this, "Case Accepted!", Toast.LENGTH_SHORT).show(),
                error -> Toast.makeText(this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show()
        );

        queue.add(req);
    }
}
