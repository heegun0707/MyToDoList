package hee.study.hiltstudy

import hee.study.hiltstudy.model.Post
import hee.study.hiltstudy.model.RssFeed
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("rss")
    fun getNews(
        @Query("hl") language: String,
        @Query("gl") country: String,
        @Query("ceid") ceid: String
    ): Single<RssFeed>

    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("posts")
    fun getPostsToRx(): Single<List<Post>>

    @GET("posts/{userId}")
    fun getUser(@Path("userId") userId: Int): Call<Post>

    @GET("posts/{userId}")
    fun getPosts(@Query("userId") userId: Int): Call<List<Post>>
}