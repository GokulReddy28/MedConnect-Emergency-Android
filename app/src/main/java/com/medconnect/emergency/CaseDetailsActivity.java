package com.medconnect.emergency;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.content.Intent;
import android.net.Uri;

import com.android.volley.*;
import com.android.volley.toolbox.*;

import org.json.*;

public class CaseDetailsActivity extends AppCompatActivity {

    TextView txtType, txtLocation, txtStatus, txtPolice;

    double lat, lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_details);

        txtType = findViewById(R.id.txtType);
        txtLocation = findViewById(R.id.txtLocation);
        txtStatus = findViewById(R.id.txtStatus);
        txtPolice = findViewById(R.id.txtPolice);

        int id = getIntent().getIntExtra("caseId", 1);
        loadCase(id);

        findViewById(R.id.btnNavigate).setOnClickListener(v -> {
            String url = "https://www.google.com/maps/search/?api=1&query=" + lat + "," + lng;
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        });
    }

    private void loadCase(int id) {
        String URL = "https://bluffable-unacquisitive-magan.ngrok-free.dev/api/emergency/details/" + id;

        RequestQueue q = Volley.newRequestQueue(this);

        StringRequest req = new StringRequest(Request.Method.GET, URL,
                res -> {
                    try {
                        JSONObject obj = new JSONObject(res);

                        txtType.setText("Type: " + obj.getString("emergencyType"));
                        txtStatus.setText("Status: " + obj.getString("status"));

                        String loc = obj.getString("location");
                        txtLocation.setText("Location: " + loc);

                        lat = Double.parseDouble(loc.split(",")[0]);
                        lng = Double.parseDouble(loc.split(",")[1]);

                        txtPolice.setText("Police Notified: ✔ YES");

                    } catch (Exception e) {
                        Toast.makeText(this, "Parsing error", Toast.LENGTH_SHORT).show();
                    }
                },
                err -> Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show()
        );

        q.add(req);
    }
}
