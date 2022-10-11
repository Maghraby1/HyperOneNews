package com.maghraby.hyperonenews.di.module

import android.annotation.SuppressLint
import android.content.Context
import com.maghraby.hyperonenews.data.api.ApiHelper
import com.maghraby.hyperonenews.data.api.ApiHelperImp
import com.maghraby.hyperonenews.data.api.ApiService
import com.maghraby.hyperonenews.utils.Constants.BASE_URL
//import com.maghraby.hyperonenews.utils.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

//    private var mContext: Context? = null
//
//    @SuppressLint("NotConstructor")
//    fun AppModule(mContext: Context?) {
//        this.mContext = mContext
//    }
//
//    @Provides
//    @Singleton
//    fun provideApplication(): Context? {
//        return mContext
//    }

//    @Provides
//    @Singleton
//    fun provideNetworkHelper(context: Context) = NetworkHelper(context)
//
    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient
        .Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()
//
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
//
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImp): ApiHelper = apiHelper

}