package com.wangmir.calliope.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Leaderboard
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wangmir.calliope.R
import com.wangmir.calliope.ui.theme.CalliopeTheme

enum class BottomMenuContent(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
) {
    LIST_VIEW(R.string.list_view_navi, Icons.Outlined.List, "home/list_view"),
    CALENDAR_VIEW(R.string.calendar_view_navi, Icons.Outlined.CalendarToday, "home/calendar_view"),
    STAT_VIEW(R.string.statistics_view_navi, Icons.Outlined.Leaderboard, "home/stat_view"),
    SETTING_VIEW(R.string.setting_view_navi, Icons.Outlined.Settings, "home/setting_view")
}

@Composable
fun BottomNavigator(
    items: List<BottomMenuContent>,
    currentRoute: String,
    navigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier,

    ) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(8.dp)
    ) {
        items.forEach { item ->
            BottomMenuItem(
                item = item,
                isSelected = currentRoute == item.route,
            ) {
                navigateToRoute(item.route)
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit
) {
    val color =
        if (isSelected) LocalContentColor.current else LocalContentColor.current.copy(alpha = 0.5F)
    Box(
        modifier = modifier.selectable(selected = isSelected, onClick = onItemClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(item.icon, item.name, tint = color)
    }
}

@Preview
@Composable
fun BottomNavigatorPreview() {
    CalliopeTheme {
        Scaffold(
            bottomBar = {
                BottomNavigator(
                    items = BottomMenuContent.values().toList(),
                    currentRoute = BottomMenuContent.LIST_VIEW.route,
                    navigateToRoute = {})
            }
        ) {

        }
    }
}
