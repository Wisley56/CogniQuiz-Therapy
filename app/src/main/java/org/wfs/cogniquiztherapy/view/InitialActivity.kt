package org.wfs.cogniquiztherapy.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.wfs.cogniquiztherapy.ui.theme.LightPink

class InitialActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            
        }
    }
}

@Composable
fun InitialScreen() {
    Column(modifier = Modifier.fillMaxSize()
        .background(color = LightPink)) {
        Column {

        }
    }
}


@Preview(name = "InitialScreenPreview")
@Composable
fun InitialScreenPreview() {
    InitialScreen()
}
