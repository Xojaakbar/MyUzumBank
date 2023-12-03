package luis.mvi.uzumbank.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.intuit.sdp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import luis.mvi.uzumbank.data.network.api.AuthApi
import luis.mvi.uzumbank.data.network.api.CardsApi
import luis.mvi.uzumbank.data.network.api.HistoryApi
import luis.mvi.uzumbank.data.network.api.PaymentsApi
import luis.mvi.uzumbank.data.network.api.ProfileApi
import luis.mvi.uzumbank.data.network.api.TransferApi
import luis.mvi.uzumbank.utils.SharedPref
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/*
created by Xo'jaakbar on 07.10.2023 at 21:18
*/
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideClient(
        @ApplicationContext context: Context,
        mySharedPrefs: SharedPref
    ): OkHttpClient =
        OkHttpClient.Builder().addInterceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            if (mySharedPrefs.accessToken.isNotEmpty()) {
                requestBuilder.addHeader("Authorization", "Bearer ${mySharedPrefs.accessToken}")
            }
            val response = chain.proceed(requestBuilder.build())
            response
        }.addInterceptor(ChuckerInterceptor.Builder(context).build()).build()

//    @Provides
//    fun provideClient(@ApplicationContext context:Context):OkHttpClient =
//        OkHttpClient.Builder()
//            .addInterceptor(ChuckerInterceptor.Builder(context).build())
//            .build()

    @[Provides Singleton]
    fun provideRetrofit(client: OkHttpClient):Retrofit = Retrofit.Builder()
        .baseUrl(luis.mvi.uzumbank.BuildConfig.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideGson(): Gson = Gson()

    @[Provides Singleton]
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @[Provides Singleton]
    fun provideProfileApi(retrofit: Retrofit): ProfileApi = retrofit.create(ProfileApi::class.java)

    @[Provides Singleton]
    fun provideCardApi(retrofit: Retrofit): CardsApi = retrofit.create(CardsApi::class.java)

    @[Provides Singleton]
    fun provideHistoryApi(retrofit: Retrofit): HistoryApi = retrofit.create(HistoryApi::class.java)

    @[Provides Singleton]
    fun provideTransferApi(retrofit: Retrofit): TransferApi = retrofit.create(TransferApi::class.java)

    @[Provides Singleton]
    fun providePaymentsApi(retrofit: Retrofit): PaymentsApi = retrofit.create(PaymentsApi::class.java)

}
