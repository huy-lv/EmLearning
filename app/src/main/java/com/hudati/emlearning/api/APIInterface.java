package com.hudati.emlearning.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @GET
    Call<SectionResponse> loadSections(@Url String url);

    @GET
    Call<QuestionResponse> loadQuestion(@Url String url);

    @GET
    Call<ReadingTestResponse> loadReadingTest(@Url String url);

    @GET
    Call<ReadingQuestionResponse> loadReadingAnswer(@Url String url);

    @GET
    Call<AudioListRespone> loadAudioList(@Url String url);

    @FormUrlEncoded
    @POST
    Call<LoginResponse> login(@Url String url,
                              @Field("email") String username,
                              @Field("password") String password,
                              @Field("facebookId") String facebookId,
                              @Field("googlePlusId") String googlePlusId);

    @FormUrlEncoded
    @POST
    Call<BaseResponse> logout(@Url String url,
                              @Field("accessToken") String accessToken);
}
