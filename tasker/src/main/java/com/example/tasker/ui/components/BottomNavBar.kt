package com.example.tasker.ui.components

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.tasker.MainActivity
import com.example.tasker.PlanActivity
import com.example.tasker.R
import com.example.tasker.SettingsActivity
import com.example.tasker.ui.theme.Muted
import com.example.tasker.ui.theme.TealDark
import com.example.tasker.ui.theme.TealSoft

private data class NavItem(@StringRes val labelRes: Int, val icon: ImageVector)

private val navItems = listOf(
    NavItem(R.string.nav_tasks, Icons.Outlined.Checklist),
    NavItem(R.string.nav_plan, Icons.Outlined.CalendarToday),
    NavItem(R.string.nav_settings, Icons.Outlined.Settings)
)

@Composable
fun BottomNavBar(selected: Int, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    NavigationBar(modifier = modifier, containerColor = Color.White) {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selected,
                onClick = { if (index != selected) openScreen(context, index) },
                icon = { Icon(item.icon, null) },
                label = { Text(stringResource(item.labelRes)) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = TealDark,
                    selectedTextColor = TealDark,
                    indicatorColor = TealSoft,
                    unselectedIconColor = Muted,
                    unselectedTextColor = Muted
                )
            )
        }
    }
}

private fun openScreen(context: Context, index: Int) {
    val target = when (index) {
        0 -> MainActivity::class.java
        1 -> PlanActivity::class.java
        else -> SettingsActivity::class.java
    }
    context.startActivity(
        Intent(context, target).addFlags(
            Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_NO_ANIMATION
        )
    )
    (context as? Activity)?.overridePendingTransition(0, 0)
}

@Preview(showBackground = true)
@Composable
private fun BottomNavBarPreview() {
    PreviewWrapper {
        BottomNavBar(selected = 0)
    }
}
