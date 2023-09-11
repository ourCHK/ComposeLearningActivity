package com.chk.composelearningactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chk.composelearningactivity.ui.theme.ComposeLearningActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLearningActivityTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
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
fun ArtistCard(onClick:()->Unit) {
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.Blue)
            .padding(10.dp)
            .clickable(onClick = onClick)
            .fillMaxWidth()
    ) {
        Image(painter = painterResource(id = R.drawable.image), contentDescription = null )
        Text("CHK", modifier = Modifier.offset(x = 0.dp))
        Text("CHK2")
    }
}

@Preview
@Composable
fun BoxLayout() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.matchParentSize().background(Color.Yellow))
        Image(painter = painterResource(id = R.drawable.image), contentDescription = null)
        Text(text = "Image")
    }
}