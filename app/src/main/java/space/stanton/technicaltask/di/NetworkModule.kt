package space.stanton.technicaltask.di

import androidx.viewbinding.BuildConfig
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import space.stanton.technicaltask.data.network.ApiEndpoints
import space.stanton.technicaltask.data.network.ApiService
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun apiService(
        apiEndpoints: ApiEndpoints
    ) = ApiService(apiEndpoints)

    @Singleton
    @Provides
    fun apiEndpoints(
        retrofit: Retrofit
    ): ApiEndpoints =
        retrofit.create(ApiEndpoints::class.java)

    @Singleton
    @Provides
    fun retrofit(
        okHttpClient: OkHttpClient
    ) =
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

    @Singleton
    @Provides
    fun okhttpClient() = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
        })
        .build()
}