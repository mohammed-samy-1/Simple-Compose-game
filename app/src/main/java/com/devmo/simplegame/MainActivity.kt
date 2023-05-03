package com.devmo.simplegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devmo.simplegame.ui.theme.Character
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ui()
        }
    }
}

@Composable
fun Ui() {
    var humanHp by remember {
        mutableStateOf(100)
    }
    var monsterHp by remember {
        mutableStateOf(100)
    }
    var isGameOver by remember {
        mutableStateOf(false)
    }
    var gameOverText by remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = monsterHp) {
        delay(300)
        humanHp -= 10
        if (humanHp == 0){
            isGameOver = true
            gameOverText = "you lost"
        }else if(monsterHp == 0) {
            isGameOver = true
            gameOverText = "you Won"
        }
    }
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
    ,
        contentAlignment = Alignment.Center
    ) {
        if (isGameOver) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = gameOverText,
                    color = Color.White
                )
                Button(onClick = {
                    isGameOver = false
                    humanHp = 100
                    monsterHp = 100
                }) {
                    Text(text = "Restart")
                }
            }
        } else {
            Row(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                // you can animate the boxes to move the characters
                Box {
                    Character(
                        name = "human",
                        characterPainter = painterResource(id = R.drawable.ic_launcher_foreground),
                        currentHp = humanHp,
                        maxHp = 100
                    )
                }
                Box {
                    Character(
                        name = "monster",
                        characterPainter = painterResource(id = R.drawable.ic_launcher_foreground),
                        currentHp = monsterHp,
                        maxHp = 100
                    )
                }
            }
            Button(
                onClick = {
                    monsterHp -= 10
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(15.dp)
            ) {
                Text(text = "Kick")
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}