package com.wangmir.calliope.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wangmir.calliope.model.entities.Date
import com.wangmir.calliope.model.entities.DayLog
import com.wangmir.calliope.model.entities.TextLog
import com.wangmir.calliope.ui.components.BottomMenuContent
import com.wangmir.calliope.ui.components.BottomNavigator
import com.wangmir.calliope.ui.theme.CalliopeTheme

@ExperimentalMaterialApi
@Composable
fun DayLogList(
    dayLogs: List<DayLog>
) {
    LazyColumn(contentPadding = PaddingValues(vertical = 4.dp)) {
        items(dayLogs) { dayLog ->
            DayLogRow(dayLog = dayLog)
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun DayLogListPreview() {

    val dayLogs = listOf(
        DayLog.getMockDayLog(),
        DayLog.getMockDayLog().copy(
            date = Date(2022, 1, 17),
            textLog = TextLog("""
                그릭 요거트는 존맛탱이다.
                하지만, 다이어트 한답시고 이렇게 많이 먹다간 그리스 사람이 될 것 만 같다.
            """.trimIndent())
        )
    )

    CalliopeTheme {
        Surface {
            DayLogList(dayLogs = dayLogs)
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun DayLogRow(
    dayLog: DayLog,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            modifier = modifier.padding(start = 8.dp, top = 4.dp),
            text = dayLog.date.toString(),
            style = MaterialTheme.typography.caption
        )
        Divider(modifier = modifier.padding(start = 8.dp, end = 8.dp))
        ListItem(
            icon = {
                Icon(Icons.Rounded.PlayArrow, null)
            },
            text = {
                Text(
                    text = dayLog.textLog.asSingleLine(),
                    style = MaterialTheme.typography.body2,
                    softWrap = true,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            },
            trailing = {
                Icon(Icons.Rounded.Edit, null)
            },
            singleLineSecondaryText = true
        )
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun DayLogRowPreview() {
    CalliopeTheme {
        Surface {
            DayLogRow(dayLog = DayLog.getMockDayLog())
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun DayLogViewPreview() {
    CalliopeTheme {
        Scaffold(
            bottomBar = {
                BottomNavigator(
                    items = BottomMenuContent.values().toList(),
                    // todo: change this with regarding state
                    currentRoute = BottomMenuContent.LIST_VIEW.route,
                    navigateToRoute = {}
                )
            }
        ) {
            DayLogListPreview()
        }
    }
}
