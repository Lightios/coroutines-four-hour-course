package com.kotlinconf.workshop.articles.tasks

import com.kotlinconf.workshop.articles.model.Article
import com.kotlinconf.workshop.articles.network.BlogService
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

// Task: Implement concurrent loading of articles
suspend fun loadArticlesConcurrently(service: BlogService): List<Article> = coroutineScope {
    val articleInfoList = service.getArticleInfoList()

    articleInfoList.map {
        async {
            Article(
                it,
                service.getComments(
                    it,
                )
            )
        }
    }.awaitAll()
}