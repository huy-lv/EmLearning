package com.hudati.emlearning.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hudati.emlearning.R;
import com.hudati.emlearning.api.APIClient;
import com.hudati.emlearning.api.APIInterface;
import com.hudati.emlearning.api.BookListResponse;
import com.hudati.emlearning.base.BaseToolbarActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by huylv on 03-Apr-17.
 */

public class TestListActivity extends BaseToolbarActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_list;
    }

    void loadTestList(){
//        Call<BookListResponse> call  = APIClient.getClient().create(APIInterface.class).getTestList();
//        call.enqueue(new Callback<BookListResponse>() {
//            @Override
//            public void onResponse(Call<BookListResponse> call, Response<BookListResponse> response) {
//            }
//
//            @Override
//            public void onFailure(Call<BookListResponse> call, Throwable t) {
//            }
//        });
    }
}
