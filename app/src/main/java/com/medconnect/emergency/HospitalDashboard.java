package com.medconnect.emergency;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import com.android.volley.*;
import com.android.volley.toolbox.*;

import org.json.*;

import java.util.*;

public class HospitalDashboard extends AppCompatActivity {

    ListView listEmergencies;
    ArrayList<String> caseList = new ArrayList<>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_dashboard);

        listEmergencies = findViewById(R.id.listEmergencies);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, caseList);
        listEmergencies.setAdapter(adapter);

        loadCases();

        listEmergencies.setOnItemClickListener((a, v, pos, id) -> {
            Intent i = new Intent(HospitalDashboard.this, CaseDetailsActivity.class);
            i.putExtra("caseId", pos + 1); // simplified for demo
            startActivity(i);
        });
    }

    private void loadCases() {
        String URL = "https://bluffable-unacquisitive-magan.ngrok-free.dev/api/emergency/dashboard";

        RequestQueue q = Volley.newRequestQueue(HospitalDashboard.this);

        StringRequest req = new StringRequest(Request.Method.GET, URL,
                res -> {
                    try {
                        JSONArray arr = new JSONArray(res);

                        caseList.clear();

                        for (int i = 0; i < arr.length(); i++) {
                            JSONObject obj = arr.getJSONObject(i);

                            String item =
                                    "Case ID: " + obj.getLong("id") + "\n" +
                                            "Type: " + obj.getString("emergencyType") + "\n" +
                                            "Status: " + obj.getString("status");

                            caseList.add(item);
                        }

                        adapter.notifyDataSetChanged();

                    } catch (Exception e) {
                        Toast.makeText(this, "Parse error", Toast.LENGTH_SHORT).show();
                    }
                },
                err -> Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show()
        );

        q.add(req);
    }
}
