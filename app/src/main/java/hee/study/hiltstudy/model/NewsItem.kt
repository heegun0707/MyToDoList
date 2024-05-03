package hee.study.hiltstudy.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
data class NewsItem(
    @field:Element(name = "title")
    var title: String? = null,

    @field:Element(name = "link")
    var link: String? = null,

    @field:Element(name = "description")
    var description: String? = null,

    @field:Element(name = "pubDate")
    var pubDate: String? = null
)
