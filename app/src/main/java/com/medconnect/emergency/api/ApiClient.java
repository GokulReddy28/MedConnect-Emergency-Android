package com.medconnect.emergency.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;

    // 🔥 Always put your EXACT ngrok URL ending with '/'
    private static final String BASE_URL = "https://bluffable-unacquisitive-magan.ngrok-free.dev/";

    public static ApiInterface getApi() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(ApiInterface.class);
    }
}
