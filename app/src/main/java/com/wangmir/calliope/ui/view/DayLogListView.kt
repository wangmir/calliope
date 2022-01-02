package com.wangmir.calliope.ui.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.wangmir.calliope.ui.theme.CalliopeTheme

// TODO: temporal
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CalliopeTheme {
        Greeting("Android")
    }
}
