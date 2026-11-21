package com.kotlinconf.workshop.articles.tasks

import com.kotlinconf.workshop.articles.model.Article
import com.kotlinconf.workshop.articles.network.BlogService
import com.kotlinconf.workshop.articles.network.BlogServiceBlocking
import com.kotlinconf.workshop.articles.network.createBlogService
import com.kotlinconf.workshop.blog.ArticleInfo

// Task: Implement blocking and non-blocking versions for articles loading

// This function is invoked when you select the "BLOCKING" option in the UI.
fun loadArticles(serviceBlocking: BlogServiceBlocking): List<Article> {
    val articleInfoList = serviceBlocking.getArticleInfoList()
    val results = mutableListOf<Article>()

    for (articleInfo in articleInfoList) {
        results.add(
            Article(
                info = articleInfo,
                comments = serviceBlocking.getComments(articleInfo)
            )
        )
    }

    return results
}

// This function is invoked when you select the "SUSPENDING" option in the UI.
suspend fun loadArticles(service: BlogService): List<Article> {

    val articleInfoList = service.getArticleInfoList()
    val results = mutableListOf<Article>()

    for (articleInfo in articleInfoList) {
        results.add(
            Article(
                info = articleInfo,
                comments = service.getComments(articleInfo)
            )
        )
    }

    return results
}

fun main0() {
    val serviceBlocking = BlogServiceBlocking()
    val articles = loadArticles(serviceBlocking)
    println(articles)
}

suspend fun main() {
    val service = createBlogService()
    println(loadArticles(service))
}