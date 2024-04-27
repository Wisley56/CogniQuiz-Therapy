package org.wfs.cogniquiztherapy.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.wfs.cogniquiztherapy.MainActivity
import org.wfs.cogniquiztherapy.R
import org.wfs.cogniquiztherapy.ui.theme.LightPink
import org.wfs.cogniquiztherapy.ui.theme.Wine
import org.wfs.cogniquiztherapy.view_model.utils.FuncoesUtil
import java.util.Timer
import java.util.TimerTask

class SplashScreen : ComponentActivity() {
    private val timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreenContent()
        }

        timer.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    FuncoesUtil().proximaTela(this@SplashScreen, MainActivity::class.java)
                }
            }
        }, 3000)
    }
}

@Composable
fun SplashScreenContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxSize()
            .background(color = LightPink),
        contentAlignment = Alignment.Center,

    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()) {
            Image(modifier = Modifier.size(400.dp), painter = painterResource(id = R.drawable.logo_cogniquiz_therapy),
                contentDescription = "Logo")
            Spacer(modifier = Modifier.padding(16.dp))
            CircularProgressIndicator(color = Wine)
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreenContent()
}

