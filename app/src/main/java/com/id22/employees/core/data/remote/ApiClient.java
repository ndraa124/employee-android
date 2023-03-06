package com.id22.employees.core.data.remote;

import static com.id22.employees.core.utils.Constant.CONNECT_TIME_OUT;
import static com.id22.employees.core.utils.Constant.READ_TIME_OUT;
import static com.id22.employees.core.utils.Constant.WRITE_TIME_OUT;

import com.id22.employees.BuildConfig;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;

    private static OkHttpClient provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        return okHttpClient.addInterceptor(loggingInterceptor)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .client(provideHttpLoggingInterceptor())
                    .build();
        }

        return retrofit;
    }
}
