package com.youzh.xiaowuxiang.api;

import com.youzh.xiaowuxiang.Constant;
import com.youzh.xiaowuxiang.utils.StringUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by youzehong on 16/6/7.
 */
public class MovieService {

    private final MovieApi mMovieApi;

    public MovieService() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL)
                .addConverterFactory(new ToStringConverterFactory())
                .client(httpClient)
                .build();
        mMovieApi = retrofit.create(MovieApi.class);
    }

    public void getMovieList(String page, Callback<String> callback) {
        Call<String> call = mMovieApi.getMovieListPage(page);
        call.enqueue(callback);
    }

    public void getMovieDetail(String url, Callback<String> callback) {
        Call<String> call = mMovieApi.getMovieDetail(StringUtils.getStr(url));
        call.enqueue(callback);
    }

    public static class ToStringConverterFactory extends Converter.Factory {
        static final MediaType MEDIA_TYPE = MediaType.parse("text/plain");

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                                Retrofit retrofit) {
            if (String.class.equals(type)) {
                return new Converter<ResponseBody, String>() {
                    @Override
                    public String convert(ResponseBody value) throws IOException {
                        return value.string();
                    }
                };
            }
            return null;
        }

        @Override
        public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                              Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
            if (String.class.equals(type)) {
                return new Converter<String, RequestBody>() {
                    @Override
                    public RequestBody convert(String value) throws IOException {
                        return RequestBody.create(MEDIA_TYPE, value);
                    }
                };
            }
            return null;
        }
    }
}
