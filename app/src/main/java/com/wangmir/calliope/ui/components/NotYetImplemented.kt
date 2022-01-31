package com.wangmir.calliope.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Construction
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wangmir.calliope.ui.theme.CalliopeTheme

@Composable
fun NotYetImplementedView() {
    Surface {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Icon(
                imageVector = Icons.Filled.Construction,
                contentDescription = "On the construction",
                modifier = Modifier.size(128.dp)
            )
            Text("On the construction.")
        }
    }
}

@Composable
@Preview
fun NotYetImplementedPreview() {
    CalliopeTheme {
        NotYetImplementedView()
    }
}
