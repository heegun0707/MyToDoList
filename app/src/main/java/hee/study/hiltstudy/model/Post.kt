package hee.study.hiltstudy.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// REST API 응답 데이터 구조
/*{
	"userId": 1,
	"id": 1,
	"title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
	"body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
}*/

data class Post(
    @SerializedName("userId")
    val userId: Int,

    @SerializedName("id")
    val postId: Int,

    val title: String,

    @SerializedName("body")
    @Expose(serialize = false)
    val content: String
)
