package com.example.slideshowapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.slideshowapp.data.Datasource
import com.example.slideshowapp.model.Quote
import com.example.slideshowapp.ui.theme.SlideshowAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SlideshowAppTheme {
                    QuoteApp (
//                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun Slideshow(keyboardOptions: KeyboardOptions, modifier: Modifier = Modifier) {
//    val totalSlides = 5
//    var slideNum: Int by remember { mutableIntStateOf(1) }
//    val imageResource = when(slideNum) {
//        1 -> R.drawable.image_1
//        2 -> R.drawable.image_2
//        3 -> R.drawable.image_3
//        4 -> R.drawable.image_4
//        else -> R.drawable.image_5
//    }
//    val caption = when(slideNum) {
//        1 -> "\"I say 'try'; if we never try, we shall never succeed.\""
//        2 -> "\"An eye for an eye will only make the whole world blind.\""
//        3 -> "\"Learn from yesterday, live for today, hope for tomorrow. The important thing is not to stop questioning.\""
//        4 -> "\"Impossible is a word to be found only in the dictionary of fools.\""
//        else -> "\"At his best, man is the noblest of all animals; separated from law and justice he is the worst.\""
//    }
//    val person = when(slideNum) {
//        1 -> "Abraham Lincoln"
//        2 -> "Mahatma Gandhi"
//        3 -> "Albert Einstein"
//        4 -> "Napoleon Bonaparte"
//        else -> "Aristotle"
//    }
//    Column (
//        modifier = modifier
//            .fillMaxSize()
//            .padding(5.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ){
//        Text(
//            text = "Famous Quotes",
//            fontSize = 35.sp,
//            lineHeight = 30.sp,
//            textAlign = TextAlign.Center,
//            fontWeight = FontWeight.Bold,
//            textDecoration = TextDecoration.Underline,
//        )
//        Image(
//            painter = painterResource(imageResource),
//            contentDescription = null,
//            modifier = modifier.width(150.dp),
//            contentScale = ContentScale.Inside
//        )
//        Text(
//            text = caption,
//            fontSize = 25.sp,
//            lineHeight = 28.sp,
//            textAlign = TextAlign.Center,
//        )
//        Text(
//            text = "-$person",
//            fontSize = 22.sp,
//            lineHeight = 25.sp,
//            textAlign = TextAlign.Center,
//            fontWeight = FontWeight.Black,
//        )
//    }
//}

@Composable fun QuoteApp() {
    val layoutDirection = LocalLayoutDirection.current
    Surface (
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues().calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing.asPaddingValues().calculateEndPadding(layoutDirection)
            )
    ) {
        QuoteList(
            quoteList = Datasource().loadQuotes()
        )
    }
}

@Composable
fun QuoteCard(quote: Quote, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column {
            Image(
                painter = painterResource(quote.imageResourceId),
                contentDescription = stringResource(quote.personResourceId),
                modifier = Modifier.fillMaxSize().height(194.dp),
                contentScale = ContentScale.Inside
            )
            Text(
                text = LocalContext.current.getString(quote.captionResourceId),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "-" + LocalContext.current.getString(quote.personResourceId),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Black,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Composable
fun QuoteList(quoteList: List<Quote>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(quoteList) { quote ->
            QuoteCard(
                quote = quote,
                modifier = modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SlideshowPreview() {
    SlideshowAppTheme {
        QuoteApp(
        )
    }
}