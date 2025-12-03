package com.medconnect.emergency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.medconnect.emergency.api.ApiClient;
import com.medconnect.emergency.api.ApiInterface;
import com.medconnect.emergency.api.LoginRequest;
import com.medconnect.emergency.api.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText editEmail, editPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize UI components
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // On click → login
        btnLogin.setOnClickListener(v -> loginUser());
    }

    private void loginUser() {

        // Get values from inputs
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create login request body
        LoginRequest request = new LoginRequest(email, password);

        ApiInterface api = ApiClient.getApi();

        api.login(request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (!response.isSuccessful() || response.body() == null) {
                    Toast.makeText(LoginActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                    return;
                }

                LoginResponse data = response.body();

                if (data.getStatus().equalsIgnoreCase("success")) {

                    switch (data.getRole()) {

                        case "PATIENT":
                            startActivity(new Intent(LoginActivity.this, PatientDashboard.class));
                            break;

                        case "DOCTOR":
                            startActivity(new Intent(LoginActivity.this, DoctorDashboard.class));
                            break;

                        case "HOSPITAL":
                            startActivity(new Intent(LoginActivity.this, HospitalDashboard.class));
                            break;
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
