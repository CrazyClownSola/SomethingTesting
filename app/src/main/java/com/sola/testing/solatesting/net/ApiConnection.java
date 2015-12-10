package com.sola.testing.solatesting.net;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;


/**
 * author: Sola
 * 2015/10/30
 */
public class ApiConnection {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================

    private static OkHttpClient httpClient;
    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    //添加对于RxJava的适用，致使可以在Api接口当中直接使用Rxjava
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    //直接使用Gson转换进行对于参数的适配
                    .addConverterFactory(GsonConverterFactory.create(gson));

    private static Retrofit.Builder normalBuilder
            = new Retrofit.Builder()
            //添加对于RxJava的适用，致使可以在Api接口当中直接使用Rxjava
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create());


    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================
    public static <S> S createService(String baseUrl, Class<S> serviceClass) {
        if (httpClient == null) {
            httpClient = new OkHttpClient();
            httpClient.interceptors().add(new LoggingInterceptor());
        }
        Retrofit retrofit = builder.baseUrl(baseUrl).client(httpClient).build();
        return retrofit.create(serviceClass);
    }

    public static <S> S createServiceCallString(String baseUrl, Class<S> serviceClass) {
        if (httpClient == null) {
            httpClient = new OkHttpClient();
            httpClient.interceptors().add(new LoggingInterceptor());
        }
        Retrofit retrofit = normalBuilder.baseUrl(baseUrl).client(httpClient).build();
        return retrofit.create(serviceClass);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================


    static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            Log.d("OkHttp", String.format("Sending request %s on %s%n%s body [%s]",
                    request.url(), chain.connection(), request.headers(), request.body()));
//            MultipartBuilder
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            Log.d("OkHttp", String.format("Received response for %s in %.1fms%n%s ",
                    response.request().url(),
                    (t2 - t1) / 1e6d,
                    response.headers()));
//            Log.d("OkHttp", "Response: " + response.body().string());
            return response;
        }
    }
}
