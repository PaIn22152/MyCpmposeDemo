package com.example.mycompose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycompose.ui.theme.MyComposeTheme

class MainActivity : AppCompatActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun Greeting(name: String) {
    
    var v by remember { mutableStateOf(true) }
    var count by remember { mutableStateOf(0) }
    var tar by remember { mutableStateOf(0f) }
    
    val r by animateFloatAsState(
        targetValue = tar,
        animationSpec = tween(durationMillis = 2000, easing = LinearEasing),
        finishedListener = {
            println(" rotate finish  $it")
        }
    )
    
    
    
    Column {
        
        
        Box {
            Image(
                painter = painterResource(id = R.drawable.home_compass),
                contentDescription = "bg"
            )
            
            Image(
                painter = painterResource(id = R.drawable.home_compass_pointer),
                contentDescription = "pointer",
                Modifier.rotate(r)
            )
        }
        
        
        
        AnimatedVisibility(
            visible = v,
            enter = expandIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically(),
        ) {
            Text(text = "Visibility")
        }
        
        Text(text = "count=$count")
        
        Text(
            text = "旋转一圈", Modifier
                .padding(10.dp)
                .clickable(onClick = {
                    tar += 360f
            
                })
        )
        Button(onClick = {
            v = !v
        }) {
            Text(text = (if (v) "Hide" else "Show"))
        }
        
        Button(onClick = {
            count++
        }) {
            Text(text = " add")
        }
    }
    
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyComposeTheme {
        Greeting("Android")
    }
}