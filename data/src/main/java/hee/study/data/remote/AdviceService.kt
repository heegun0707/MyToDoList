package hee.study.data.remote

import hee.study.data.model.AdviceResponse
import retrofit2.http.GET

interface AdviceService {

    @GET("advice")
    suspend fun getRandomAdvice(): AdviceResponse
}