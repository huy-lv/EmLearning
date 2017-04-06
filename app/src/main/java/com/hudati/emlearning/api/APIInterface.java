package com.hudati.emlearning.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by huylv on 03-Apr-17.
 */

public interface APIInterface {
//    @GET("api/home/books")
//    Call<BookListResponse> getBookList();
//
//    @GET("api/home/tests")
//    Call<BookListResponse> getTestList();
//
//    @GET("api/home/banners")
//    Call<BannerResponse> getBanners();
//

    @GET
    Call<LectureResponse> loadLectures(@Url String url);

    @GET
    Call<HomeResponse> loadHomePageFromApi(@Url String url);

    @GET("api/root")
    Call<RootApiResponse> loadApiList();

    @GET
    Call<BannerResponse> loadBanners(@Url String url);

    @GET
    Call<BookListResponse> loadBookList(@Url String url);

    @GET
    Call<PracticeDetailResponse> loadPracticeDetail(@Url String url);

    @GET
    Call<PracticeListResponse> loadPracticeList(@Url String url);
}
