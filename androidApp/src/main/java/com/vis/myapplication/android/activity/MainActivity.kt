package com.vis.myapplication.android.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vis.myapplication.android.MyApplicationTheme
import com.vis.myapplication.android.widgets.MainScreenWithData
import com.vis.myapplication.viewmodels.MainScreenViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    when (val uiState = viewModel.uiState.collectAsStateWithLifecycle().value) {
                        is MainScreenViewModel.UiState.Success -> {
                            MainScreenWithData(uiState.movies)
                        }

                        is MainScreenViewModel.UiState.Error -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "Error: " + uiState.errorMessage)
                            }
                        }


                        MainScreenViewModel.UiState.Loading -> {
                            LoadingContainer()
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun LoadingContainer() {

}
@Composable
fun GreetingView(text: String) {
    Text(text = text)
    Greeting(text)
}
@Composable
fun Greeting(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
