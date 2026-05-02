package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diceroller.ui.theme.DiceRollerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                DiceRollerApp()
            }
        }
    }
}

@Preview
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }
    var isRolling by remember { mutableStateOf(false) }
    var hasWon by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val rotation = remember { Animatable(0f) }

    val imageResource = when (result) {
        1  -> R.drawable.numero1
        2  -> R.drawable.numero2
        3  -> R.drawable.numero3
        4  -> R.drawable.numero4
        5  -> R.drawable.numero5
        6  -> R.drawable.numero6
        7  -> R.drawable.numero7
        8  -> R.drawable.numero8
        9  -> R.drawable.numero9
        10 -> R.drawable.numero10
        11 -> R.drawable.numero11
        12 -> R.drawable.numero12
        13 -> R.drawable.numero13
        14 -> R.drawable.numero14
        15 -> R.drawable.numero15
        16 -> R.drawable.numero16
        17 -> R.drawable.numero17
        18 -> R.drawable.numero18
        19 -> R.drawable.numero19
        20 -> R.drawable.numero20
        else -> R.drawable.numero90
    }

    Column(
        modifier = modifier
            .background(Color(0xFFF5F0E8)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "DICE ROLLER",
            fontSize = 26.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 6.sp,
            color = Color(0xFF2C2C2C),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(280.dp)
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = result.toString(),
                modifier = Modifier
                    .size(280.dp)
                    .rotate(rotation.value)
            )
            Text(
                text = result.toString(),
                fontSize = if (result >= 10) 30.sp else 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A1A1A),
                modifier = Modifier.offset(y = 27.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (hasWon) {
            Text(
                text = "HAI VINTO!",
                fontSize = 32.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 4.sp,
                color = Color(0xFF8B6914),
                textAlign = TextAlign.Center
            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (!isRolling) {
                    scope.launch {
                        isRolling = true
                        hasWon = false
                        rotation.animateTo(
                            targetValue = rotation.value + 720f,
                            animationSpec = tween(
                                durationMillis = 600,
                                easing = FastOutSlowInEasing
                            )
                        )
                        val roll = listOf(
                            1,2,3,4,5,6,7,8,9,10,
                            11,12,13,14,15,16,17,18,19,20,
                            90
                        ).random()
                        result = roll
                        if (roll == 90) hasWon = true
                        isRolling = false
                    }
                }
            },
            enabled = !isRolling,
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF2C2C2C),
                disabledBackgroundColor = Color(0xFF9E9E9E)
            ),
            modifier = Modifier
                .width(220.dp)
                .height(56.dp)
        ) {
            Text(
                text = stringResource(R.string.roll),
                color = Color(0xFFF5F0E8),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 3.sp
            )
        }
    }
}
