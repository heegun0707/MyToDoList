package hee.study.todo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hee.study.data.remote.AdviceService
import hee.study.data.remote.WeatherService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideWeatherUrl() = "http://api.openweathermap.org/data/2.5/"

    @Provides
    fun provideAdviceUrl() = "https://api.adviceslip.com/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    @Provides
    @Singleton
    @Named("Weather")
    fun provideWeatherRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(provideWeatherUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    @Named("Advice")
    fun provideAdviceRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(provideAdviceUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherService(@Named("Weather") retrofit: Retrofit) : WeatherService = retrofit.create(WeatherService::class.java)

    @Provides
    @Singleton
    fun provideAdviceService(@Named("Advice") retrofit: Retrofit) : AdviceService = retrofit.create(AdviceService::class.java)

    // provideAdviceService()와 provideAdviceService()모두 Retrofit을 객체로 받는데, provide된 Retrofit가 두 개니 어떤 Retrofit 객체를 주입할지 모르는 것이다.
    // 그래서 duplicateBinding 에러가 발생한다.
    // Named 어노테이션으로 동일 반환 타입의 @Provides 메서드들 중에서 선택해서 의존성 주입을 받을 수 있다.
}