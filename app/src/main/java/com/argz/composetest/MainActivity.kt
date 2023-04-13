package com.argz.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.argz.composetest.models.Album
import com.argz.composetest.ui.theme.ComposetestTheme
import com.argz.composetest.models.Comment
import com.argz.composetest.models.Post


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposetestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainView()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(
    mainViewModel: MainViewModel = MainViewModel()
) {
    val posts = remember { mainViewModel.posts }
    val comments = remember { mainViewModel.comments }
    val albums = remember { mainViewModel.albums }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Centered TopAppBar",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
        content = {it
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxWidth(),
            ) {
                MainContent(posts.value, comments.value, albums.value)
            }
        }
    )
}

@Composable
fun MainContent(
    posts: List<Post>,
    comments: List<Comment>,
    albums: List<Album>,
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
    ) {

        if (albums.isNotEmpty()) {
            Text(text = "Albums currently have ${albums.size}")
        } else {
            CircularProgressIndicator()
        }

        if (posts.isNotEmpty()) {
            Text(text = "Posts currently have ${posts.size}")
        } else {
            CircularProgressIndicator()
        }

        if (comments.isNotEmpty()) {
            Text(text = "Comments currently have ${comments.size}")
        } else {
            CircularProgressIndicator()
        }
    }
}
@Preview(showBackground = true)
@Composable
fun MainContentPreview() {
    ComposetestTheme {
        MainContent(
            listOf(
                Post(1,1,"test","test"),
                Post(2,2,"test","test"),
                Post(3,3,"test","test"),
            ),
            listOf(
                Comment(1,1,"test","test","test"),
                Comment(1,1,"test","test","test"),
                Comment(1,1,"test","test","test"),
            ),
            listOf(
                Album(1,1,"test","test"),
                Album(2,2,"test","test"),
                Album(3,3,"test","test"),
            ),
        )
    }
}