package com.example.msapps.models

interface IArticle {
    val source: Source?
    val author: String?
    val title: String?
    val description: String?
    val url: String?
    val urlToImage: String?
    val publishedAt: String?
    val content: String?
}

data class Article(
    override val source: Source?,
    override val author: String?,
    override val title: String?,
    override val description: String?,
    override val url: String?,
    override val urlToImage: String?,
    override val publishedAt: String?,
    override val content: String?
) : IArticle

data class Source(
    val id: String,
    val name: String
)

//Creating a wrapper class to Article so I can use it in Retrofit
data class ArticleResponse(
    val articles: List<Article>
)


//"source": {
//    "id": "business-insider",
//    "name": "Business Insider"
//},
//"author": "snagarajan@businessinsider.com (Shalini Nagarajan)",
//"title": "Tesla falls as much as 5% after Elon Musk warns it hasn't yet signed the Hertz contract for a record-breaking 100,000 orders",
//"description": "\"If any of this is based on Hertz, I'd like to emphasize that no contract has been signed yet,\" Musk said in reply to a comment about Tesla's bull run.",
//"url": "https://markets.businessinsider.com/news/stocks/tesla-stock-price-elon-musk-hertz-contract-not-signed-yet-2021-11",
//"urlToImage": "https://images2.markets.businessinsider.com/61810eb91037b100181550ca?format=jpeg",
//"publishedAt": "2021-11-02T11:34:50Z",
//"content": "Tesla CEO Elon Musk.\r\nMatt Rourke/AP Photo\r\nTesla shares fell as much as 5% in Tuesday's premarket session, after its CEO Elon Musk said the electric-vehicle maker has not yet signed a contract with … [+1903 chars]"
