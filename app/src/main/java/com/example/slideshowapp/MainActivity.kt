package com.example.slideshowapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.slideshowapp.ui.theme.SlideshowAppTheme
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SlideshowAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Slideshow (
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Slideshow(keyboardOptions: KeyboardOptions, modifier: Modifier = Modifier) {
    val totalSlides = 5
    var slideNum: Int by remember { mutableIntStateOf(1) }
    val imageResource = when(slideNum) {
        1 -> R.drawable.abraham_lincoln
        2 -> R.drawable.mahatma_gandhi
        3 -> R.drawable.albert_einstein
        4 -> R.drawable.napoleon_bonaparte
        else -> R.drawable.aristotle
    }
    val caption = when(slideNum) {
        1 -> "\"I say 'try'; if we never try, we shall never succeed.\""
        2 -> "\"An eye for an eye will only make the whole world blind.\""
        3 -> "\"Learn from yesterday, live for today, hope for tomorrow. The important thing is not to stop questioning.\""
        4 -> "\"Impossible is a word to be found only in the dictionary of fools.\""
        else -> "\"At his best, man is the noblest of all animals; separated from law and justice he is the worst.\""
    }
    val person = when(slideNum) {
        1 -> "Abraham Lincoln"
        2 -> "Mahatma Gandhi"
        3 -> "Albert Einstein"
        4 -> "Napoleon Bonaparte"
        else -> "Aristotle"
    }
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Famous Quotes",
            fontSize = 35.sp,
            lineHeight = 30.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,
        )
        Image(
            painter = painterResource(imageResource),
            contentDescription = null,
            modifier = modifier.width(150.dp),
            contentScale = ContentScale.Inside
        )
        Text(
            text = caption,
            fontSize = 25.sp,
            lineHeight = 28.sp,
            textAlign = TextAlign.Center,
        )
        Text(
            text = "-$person",
            fontSize = 22.sp,
            lineHeight = 25.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Black,
        )
        Row (
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(
                onClick = {
                    if(slideNum <= 1) {
                        slideNum = totalSlides
                    }
                    else {
                        slideNum -= 1
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color.Black
                ),
            ) {
                Text(stringResource(R.string.previous), fontSize = 22.sp)
            }
            Button(
                onClick = {
                    if(slideNum >= totalSlides) {
                        slideNum = 1
                    }
                    else {
                        slideNum += 1
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color.Black
                ),
            ) {
                Text(stringResource(R.string.next), fontSize = 22.sp)
            }
        }
        TextField(
            value = slideNum.toString(),
            onValueChange = {
                newText: String ->
                val lastSlideNum: Int = slideNum
                slideNum = newText.toIntOrNull() ?: slideNum
                if(slideNum < 1 || slideNum > totalSlides) {
                    slideNum = lastSlideNum
                }
            },
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 22.sp
            ),
            modifier = modifier.width(100.dp)
            .border(
                width = 5.dp,
                color = Color.Black,
                shape = RoundedCornerShape(15.dp)
            ),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.LightGray
            ),
            keyboardOptions = keyboardOptions
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SlideshowPreview() {
    SlideshowAppTheme {
        Slideshow(
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
        )
    }
}