package hee.study.hiltstudy.model

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
data class Channel(
    @field:ElementList(inline = true, required = false)
    var newsItems: List<NewsItem>? = null
)
