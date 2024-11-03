package com.example.myapplication

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    @SuppressLint("RememberReturnType")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val guesedNumber = remember {
                mutableStateOf("")
            }

            val test = remember {
                mutableStateOf("")
            }

            val history = remember {
                mutableStateListOf<String>()
            }

            val myNum = remember {
                Random.nextInt(0, 10)
            }

            val stat = remember {
                mutableStateOf("")

            }

            MyApplicationTheme {
                Column(modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                    )
                {
                   TextField(
                       modifier = Modifier
                           .padding(top = 20.dp)
                           .clip(RoundedCornerShape(20.dp))
                           .width(300.dp),
                       value = guesedNumber.value,
                       onValueChange = {
                        guesedNumber.value = it
                   })

                    Button(onClick = {
                        println(myNum)
                        if (guesedNumber.value.toIntOrNull() != null){
                            if(guesedNumber.value.toInt() > myNum){
                                stat.value = "bigger"

                            }
                            else if (guesedNumber.value.toInt() < myNum){
                                stat.value = "smaller"
                            }
                            else{
                                stat.value = "Trueee"
                            }
                            test.value = guesedNumber.value
                            history.reverse()
                            history.add(guesedNumber.value)
                            history.reverse()
                            guesedNumber.value = ""
                        }
                        else{
                            guesedNumber.value = ""
                            history.clear()
                            test.value = ""
                        }

                                     }, modifier = Modifier
                        .padding(top = 30.dp)
                        .width(300.dp)) {
                        Text(text = "Try It!")
                    }

                    Button(onClick = {
                         guesedNumber.value = ""
                         history.clear()
                         test.value = ""}, modifier = Modifier
                        .padding(top = 20.dp)
                        .width(300.dp)) {
                        Text(text = "Reset")
                    }

                    LazyColumn {
                        items(history){ test ->
                            Text(text = String.format("Number: %s, your number is %s than Mine", test, stat.value), modifier = Modifier
                                .width(300.dp)
                                .padding(10.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(androidx.compose.ui.graphics.Color.Blue)
                                .padding(10.dp), color = androidx.compose.ui.graphics.Color.White
)
                        }
                    }
                }
            }
        }
    }
}
