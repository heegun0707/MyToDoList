package hee.study.hiltstudy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import hee.study.hiltstudy.databinding.ActivityMainBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var retrofitService: RetrofitService
    private lateinit var adapter: NewsAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initRecyclerView()
        getNews()
//        setRetrofit()
//        launchExample()
//        asyncExample()
    }

    private fun getNews() {
        val language = "ko" // 한국어
        val country = "KR" // 대한민국
        val ceid = "KR:ko" // 대한민국의 한국어 뉴스

        retrofitService = RetrofitClient.getRetrofitInstance().create(RetrofitService::class.java)
        val call = retrofitService.getNews(language, country, ceid)
        call.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e(TAG, "getNews")
                val items = it.channel?.newsItems
                adapter.submitList(items)
                items?.forEach { item ->
                    Log.e(TAG, "title :  ${item.title}")
//                    Log.e(TAG, "Link :  ${item.link}")
//                    Log.e(TAG, "description :  ${item.description}")
//                    Log.e(TAG, "pubDate :  ${item.pubDate}")
                }
            }, { throwable ->
                Log.e("news, ", throwable.message, throwable)
            })
    }

    private fun initRecyclerView() {
        adapter = NewsAdapter { url ->
            startActivity(Intent(this, WebViewActivity::class.java).putExtra("url", url))
        }
        binding.recyclerNews.adapter = adapter
        binding.recyclerNews.itemAnimator = null
    }

    private fun setRetrofit() {
        retrofitService = RetrofitClient.getRetrofitInstance().create(RetrofitService::class.java)
        val call = retrofitService.getPostsToRx()
        val callUser = retrofitService.getUser(1)
        call.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e(TAG, "posts : $it")
            }, { throwable ->
                Log.e("posts, ", throwable.message, throwable)
            })
//        call.enqueue(object : Callback<List<Post>> {
//            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
//                val posts = response.body()
//                Log.e(TAG, "posts : $posts")
//                // 데이터 사용
//            }
//
//            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
//                Log.e("MainActivity", "Error occurred while fetching posts: ${t.message}")
//                // 오류 처리
//            }
//        })

//        callUser.enqueue(object : Callback<Post> {
//            override fun onResponse(call: Call<Post>, response: Response<Post>) {
//                val posts = response.body()
//                Log.d(TAG, "posts : $posts")
//                // 데이터 사용
//            }
//
//            override fun onFailure(call: Call<Post>, t: Throwable) {
//                Log.e("MainActivity", "Error occurred while fetching posts: ${t.message}")
//                // 오류 처리
//            }
//        })
    }

    private fun launchExample() {
        println("Start")
        Log.e("launchExample", "Start")
        GlobalScope.launch {
            delay(1000) // 1초 동안 일시 중단
            println("Coroutine executed")
            Log.e("launchExample", "Coroutine executed")
        }

        println("End")
        Log.e("launchExample", "End")
    }

    private fun asyncExample() {
        println("Start")
        Log.e("launchExample", "Start")
        runBlocking {
            val deferred = GlobalScope.async {
                delay(1000) // 1초 동안 일시 중단
                "World"
            }

            println("End")
            Log.e("launchExample", "Hello ${deferred.await()}")
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}