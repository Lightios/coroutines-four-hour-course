package com.kotlinconf.workshop.articles.tasks

import com.kotlinconf.workshop.articles.model.Article
import com.kotlinconf.workshop.articles.network.BlogService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

// Run both versions and try to cancel
suspend fun loadArticlesNonCancelable(service: BlogService): List<Article> {
    val articleInfoList = service.getArticleInfoList()

    // non-cancelable
//    return articleInfoList.map {
//        GlobalScope.async {
//            Article(
//                it,
//                service.getComments(
//                    it,
//                )
//            )
//        }
//    }.awaitAll()

    // cancelable
    return coroutineScope {
        articleInfoList.map {
            async {
                Article(
                    it,
                    service.getComments(
                        it,
                    )
                )
            }
        }
    }.awaitAll()
}