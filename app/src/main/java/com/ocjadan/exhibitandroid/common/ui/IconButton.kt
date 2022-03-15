package com.ocjadan.exhibitandroid.common.ui

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun IconButton(
    modifier: Modifier = Modifier.size(24.dp),
    @DrawableRes icon: Int,
    @ColorRes color: Int,
    @StringRes contentDescription: Int,
    onClick: () -> Unit,
    @StringRes testTag: Int? = null
) {
    androidx.compose.material.IconButton(onClick, modifier) {
        val iconModifier = if (testTag == null) {
            Modifier
        } else {
            Modifier.testTag(stringResource(testTag))
        }
        Icon(painterResource(icon), stringResource(contentDescription), iconModifier, tint = colorResource(color))
    }
}