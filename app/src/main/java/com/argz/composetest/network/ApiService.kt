package com.argz.composetest.network

import com.argz.composetest.models.Album
import com.argz.composetest.models.Comment
import com.argz.composetest.models.Post
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("albums")
    suspend fun getAlbums(): List<Album>

    @GET("todos")
    suspend fun getComments(): List<Comment>
}