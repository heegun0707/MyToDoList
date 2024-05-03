package hee.study.hiltstudy

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import hee.study.hiltstudy.databinding.ActivityMainBinding
import hee.study.hiltstudy.databinding.ActivityWebBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*

@AndroidEntryPoint
class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web)
        val url = intent.getStringExtra("url")
        showWebView(url)
    }

    private fun showWebView(url: String?) {
        binding.web.settings.javaScriptEnabled = true
        binding.web.loadUrl(url!!)
    }

    companion object {
        private const val TAG = "WebViewActivity"
    }
}