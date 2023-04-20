package com.varani.isitvegan.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.varani.isitvegan.common.drawTopBorder

/**
 * Created by Ana Varani on 18/04/2023.
 */
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = MaterialTheme.colors.onPrimary,
                unselectedContentColor = MaterialTheme.colors.onSecondary,
                modifier = Modifier.drawTopBorder(MaterialTheme.colors.onPrimary, selected),
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if (item.badgeCount > 0) {
                            BadgedBox(
                                badge = {
                                    Text(text = item.badgeCount.toString())
                                },
                            ) {
                                Icon(
                                    painter = painterResource(id = item.icon),
                                    contentDescription = stringResource(id = item.name)
                                )
                            }
                        } else {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = stringResource(id = item.name)
                            )
                        }
                        Text(
                            text = stringResource(id = item.name),
                            textAlign = TextAlign.Center,
                            fontSize = 10.sp
                        )
                    }
                }
            )
        }
    }
}