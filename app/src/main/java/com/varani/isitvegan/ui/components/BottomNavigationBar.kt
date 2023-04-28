package com.varani.isitvegan.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import com.varani.isitvegan.navigation.AppNavDestination
import com.varani.isitvegan.navigation.isTopLevelDestinationInHierarchy

/**
 * Created by Ana Varani on 18/04/2023.
 */
@Composable
fun BottomNavigationBar(
    items: List<AppNavDestination>,
    onNavigateToDestination: (AppNavDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
        tonalElevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(item)
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(item) },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = item.iconResId),
                            contentDescription = stringResource(id = item.textResId)
                        )
                        Text(
                            text = stringResource(id = item.textResId),
                            textAlign = TextAlign.Center,
                            fontSize = 10.sp
                        )
                    }
                }
            )
        }
    }
}