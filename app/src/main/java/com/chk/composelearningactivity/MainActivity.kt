package com.chk.composelearningactivity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.chk.composelearningactivity.ui.theme.ComposeLearningActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLearningActivityTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//                    Greeting("Android")
                    MyShowContent()
//                    ClickItemToChange()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyShowContent() {
    var xOffset by remember {
        mutableStateOf(0)
    }
    val lazyListState = rememberLazyListState()

    if (lazyListState.isScrollInProgress) {
        xOffset = lazyListState.firstVisibleItemScrollOffset
    }
    Column(modifier = Modifier.fillMaxSize(1f)) {
        MovableTextHighPerformance {
            xOffset
        }
        MovableText(xOffset = xOffset)
        ShowMyList(dataList = listOf("1","2","1","2","1","2","1","2","end"),lazyListState)
    }
}


@Composable
fun MovableText(xOffset: Int) {
    Text(text = "This is the Text", textAlign = TextAlign.Center, modifier = Modifier
        .offset(x = xOffset.dp)
        .fillMaxWidth()
    )
}

@Composable
fun MovableTextHighPerformance(distance:()->Int) {
    Text(text = "This is the Text", textAlign = TextAlign.Center, modifier = Modifier
        .offset {
            IntOffset(x = distance(), 0)
        }
        .fillMaxWidth()
    )
}

@Composable
fun ShowMyList(dataList:List<String>, lazyListState:LazyListState) {
    LazyColumn(state = lazyListState) {
        items(dataList) { index ->
            Text(text = "This is the $index", textAlign = TextAlign.Center, modifier = Modifier
                .padding(vertical = 5.dp)
                .height(100.dp)
                .fillMaxWidth()
                .background(Color.Yellow)
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeLearningActivityTheme {
        Greeting("Android")
    }
}


@Preview(showBackground = true)
@Composable
fun ContentLayout() {
    Column(modifier = Modifier.fillMaxSize(1f)) {
        ArtistCard {
            println("you click this")
        }
    }
}

@Composable
fun ArtistCard(onClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.Blue)
            .padding(10.dp)
            .clickable(onClick = onClick)
            .fillMaxWidth()
    ) {
        Image(painter = painterResource(id = R.drawable.image), contentDescription = null)
        Text("CHK", modifier = Modifier.offset(x = 0.dp))
        Text("CHK2")
    }
}

@Preview
@Composable
fun BoxLayout() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Yellow)
        )
        Image(painter = painterResource(id = R.drawable.image), contentDescription = null)
        Text(text = "Image")
    }
}

@Preview(showBackground = true)
@Composable
fun ClickItemToChange() {
    var count by rememberSaveable {
        mutableStateOf(0)
    }
    Column {
        Button(onClick = { count++ }) {
            Text(text = "you click $count")
        }
    }
}

@Preview
@Composable
fun HelloScreen() {
    var name by rememberSaveable {
        mutableStateOf("")
    }
    HelloContent(name = name, onNameChange = {name = it})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelloContent(name: String, onNameChange: (String) -> Unit) {
    Column {
        Text(
            text = "Hello,$name",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        OutlinedTextField(value = name, onValueChange = onNameChange, label = { Text(text = "Name") })
    }
}