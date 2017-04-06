package com.hudati.emlearning.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by huylv on 02-Apr-17.
 */

public class APIClient {
    public static String BASE_URL = "http://45.118.133.196:8080/emilear/";

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
