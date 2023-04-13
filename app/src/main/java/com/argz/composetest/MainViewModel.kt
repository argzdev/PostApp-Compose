package com.argz.composetest

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.argz.composetest.models.Album
import com.argz.composetest.models.Comment
import com.argz.composetest.models.Post
import com.argz.composetest.network.Api
import com.argz.composetest.network.ApiService
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _posts = mutableStateOf(listOf<Post>())
    val posts: MutableState<List<Post>> = _posts

    private val _comments = mutableStateOf(listOf<Comment>())
    val comments: MutableState<List<Comment>> = _comments

    private val _albums = mutableStateOf(listOf<Album>())
    val albums: MutableState<List<Album>> = _albums

    private var api: ApiService = Api.create()

    init {
        fetchData()
    }

    private fun fetchData(){
        viewModelScope.launch {
            val api_posts = async { getPosts() }
            val api_comments = async { getComments()}
            val api_albums = async { getAlbums() }

            async { albums.value = api_albums.await() }
            async { posts.value = api_posts.await() }
            async { comments.value = api_comments.await() }
        }
    }

    suspend fun getPosts(): List<Post> {
        val posts = api.getPosts()
        delay(3000L)
        return posts
    }

    suspend fun getComments(): List<Comment> {
        val comments = api.getComments()
        delay(1000L)
        return comments
    }

    suspend fun getAlbums(): List<Album> {
        val albums = api.getAlbums()
        delay(5000L)
        return albums
    }
}