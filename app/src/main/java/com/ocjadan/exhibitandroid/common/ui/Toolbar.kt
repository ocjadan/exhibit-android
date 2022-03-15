package com.ocjadan.exhibitandroid.common.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ocjadan.exhibitandroid.R

@Composable
fun Toolbar(
    modifier: Modifier,
    @DrawableRes startIcon: Int,
    @StringRes startIconDescription: Int,
    @StringRes title: Int,
    @DrawableRes endIcon: Int? = null,
    onStartIconClicked: (() -> Unit)? = null,
    onEndIconClicked: (() -> Unit)? = null
) {
    Surface(Modifier.fillMaxWidth(), color = colorResource(R.color.red)) {
        Row(modifier) {
            IconButton(
                icon = startIcon,
                color = R.color.white,
                contentDescription = startIconDescription,
                onClick = {
                    if (onStartIconClicked != null) {
                        onStartIconClicked()
                    }
                }
            )
            Text(
                modifier = Modifier
                    .padding(16.dp, 0.dp, 8.dp, 0.dp)
                    .fillMaxWidth(),
                text = stringResource(title),
                color = colorResource(R.color.white)
            )
        }
    }
}