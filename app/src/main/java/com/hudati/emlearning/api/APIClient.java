package com.hudati.emlearning.api;

import com.hudati.emlearning.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by huylv on 02-Apr-17.
 */

public class APIClient {
    public static String BASE_URL = BuildConfig.BASE_URL;

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static APIInterface getInterface(){
        return getClient().create(APIInterface.class);
    }

}
