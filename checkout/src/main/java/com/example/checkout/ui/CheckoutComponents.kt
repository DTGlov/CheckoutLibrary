package com.example.checkout.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.checkout.R
import com.example.checkoutlibrary.ui.checkout.utils.Dimens
import com.example.checkoutlibrary.ui.theme.AppTypography
import com.example.checkoutlibrary.ui.theme.CheckTheme
import com.example.checkoutlibrary.ui.theme.Orange700
import com.example.checkoutlibrary.ui.theme.Yellow100
import java.text.DecimalFormat

@Composable
fun HBScaffold(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    isFloatingActionButtonDocked: Boolean = false,
    drawerContent: @Composable (ColumnScope.() -> Unit)? = null,
    drawerGesturesEnabled: Boolean = true,
    drawerShape: Shape = MaterialTheme.shapes.large,
    drawerElevation: Dp = DrawerDefaults.Elevation,
    drawerBackgroundColor: Color = CheckTheme.colors.cardBackground,
    drawerContentColor: Color = CheckTheme.colors.uiBackground,
    drawerScrimColor: Color = Color.Black.copy(alpha = 0.3f),
    backgroundColor: Color = CheckTheme.colors.uiBackground,
    contentColor: Color = CheckTheme.colors.textPrimary,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier,
        scaffoldState,
        topBar,
        bottomBar,
        snackbarHost,
        floatingActionButton,
        floatingActionButtonPosition,
        isFloatingActionButtonDocked,
        drawerContent,
        drawerGesturesEnabled,
        drawerShape,
        drawerElevation,
        drawerBackgroundColor,
        drawerContentColor,
        drawerScrimColor,
        backgroundColor,
        contentColor,
        content
    )
}

@Composable
fun HBTopAppBar(
    title: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
    onNavigateUp: (() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null,
    backgroundColor: Color = CheckTheme.colors.uiBackground2,
    contentColor: Color = CheckTheme.colors.textPrimary,
    elevation: Dp = 1.dp,
) {
    HBTopAppBar(
        modifier, backgroundColor,
        contentColor, elevation
    ) {
        if (onNavigateUp != null) {
            BackArrowButton(onNavigateUp)
        } else {
            Box(androidx.compose.ui.Modifier.size(BACK_BUTTON_SIZE))
        }

        CompositionLocalProvider(LocalTextStyle provides CheckTheme.typography.h3) {
            title()
        }

        if (actions != null) {
            actions()
        } else {
            Box(androidx.compose.ui.Modifier.size(BACK_BUTTON_SIZE))
        }
    }
}

@Composable
fun HBTopAppBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = CheckTheme.colors.uiBackground2,
    contentColor: Color = CheckTheme.colors.textPrimary,
    elevation: Dp = 2.dp,
    content: @Composable RowScope.() -> Unit,
) {
    Surface(
        elevation = elevation,
        color = backgroundColor,
        contentColor = contentColor,
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .heightIn(min = 56.dp)
                .fillMaxWidth() then modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            content = content,
        )
    }
}

val BACK_BUTTON_SIZE = 40.dp

@Composable
fun BackArrowButton(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = { onBackPressed() },
        modifier = modifier
    ) {
        Image(imageVector = Icons.Default.ArrowBack, contentDescription = null)

    }
}

@Composable
internal fun LoadingTealTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = false,
    loading: Boolean = false
) {
    TealButton(
        onClick = { if (!loading) onClick() },
        enabled = enabled,
        modifier = modifier
    ) {
        if (loading) {
            CircularProgressIndicator(
                color = CheckTheme.colors.colorOnPrimary,
                modifier = Modifier.size(30.dp)
            )
        } else {
            Text(
                text = text.uppercase(),
                style = CheckTheme.typography.button,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun TealButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit,
) {
    FlatButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = CheckTheme.colors.colorPrimary,
            contentColor = CheckTheme.colors.colorOnPrimary,
            disabledBackgroundColor = CheckTheme.colors.buttonDisabled,
            disabledContentColor = CheckTheme.colors.textDisabled
        ),
    ) {
        content.invoke(this)
    }
}

@Composable
internal fun FlatButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    roundness: Dp = 6.dp,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        shape = RoundedCornerShape(roundness),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.5.dp,
            disabledElevation = 0.dp
        ),
        colors = colors,
        contentPadding = contentPadding
    ) { content() }
}


@Composable
internal fun CheckoutReceiptCard(
    logoUrl: String?,
    accountName: String,
    accountNumber: String,
    serviceName: String?,
//    fees: List<CheckoutFee>,
    amount: Double,
    total: Double,
    modifier: Modifier = Modifier
) {

    val amountParts = remember(amount) { amount.formatMoneyParts(includeDecimals = true) }
    val totalParts = remember(total) { total.formatMoneyParts(includeDecimals = true) }

    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        // zig-zag top
        Image(
            painter = painterResource(R.drawable.fc_ic_receipt_zigzag_top),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 10.dp)
                .rotate(180f),
        )

        Column(
            modifier = Modifier
                .background(CheckTheme.colors.cardBackground),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Dimens.spacingDefault),
        ) {

            // service image & user info
            Row(
                horizontalArrangement = Arrangement.spacedBy(Dimens.paddingDefault),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = Dimens.paddingMedium)
            ) {
                Image(
                    painter = rememberImagePainter(logoUrl),
                    contentDescription = serviceName,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(Dimens.paddingNano),
                ) {
                    Text(text = accountName)

                    if (accountNumber.isNotBlank()) {
                        Text(
                            text = accountNumber,
                            color = CheckTheme.colors.textSecondary,
                            style = AppTypography.body2,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.padding(top = Dimens.spacingDefault))

            Divider(
                thickness = 1.dp,
                color = CheckTheme.colors.outline,
                modifier = Modifier
                    .background(CheckTheme.colors.cardBackground)
                    .padding(Dimens.paddingDefault),
            )

            // amount
            Row(
                modifier = Modifier.padding(horizontal = Dimens.paddingDefault),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Amount",
                    style = AppTypography.body1,
                )

                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = amountParts.first,
                        style = AppTypography.caption,
                    )

                    Text(
                        text = "${amountParts.second}${amountParts.third}",
                        style = AppTypography.body1,
                        modifier = Modifier.padding(start = 6.dp),
                    )
                }
            }

            // fees section
//            for (feeItem in fees) {
//                FeeListItem(
//                    title = feeItem.feeName ?: "",
//                    fee = feeItem.feeAmount ?: 0.0,
//                    modifier = Modifier.padding(horizontal = 16.dp),
//                )
//            }

            Spacer(Modifier.padding(vertical = 8.dp))

            // total
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Yellow100)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = "You will be charged",
                    color = Orange700,
                )

                Row {
                    Text(
                        text = totalParts.first,
                        style = AppTypography.body2,
                        modifier = Modifier.padding(top = Dimens.paddingMicro)
                    )

                    Text(
                        text = "${totalParts.second}${totalParts.third}",
                        style = AppTypography.h1,
                    )
                }
            }
        }

        // zig zag bottom
        Image(
            painter = painterResource(R.drawable.fc_ic_receipt_zigzag_top),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            colorFilter = ColorFilter.tint(Yellow100),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 10.dp),
        )
    }
}

@Composable
private fun FeeListItem(
    title: String?,
    fee: Double?,
    modifier: Modifier = Modifier
) {
    val amountParts = remember(fee) {
        fee.formatMoneyParts(includeDecimals = true)
    }

    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title ?: "",
            style = AppTypography.body1,
        )

        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = amountParts.first,
                style = AppTypography.caption,
            )

            Text(
                text = "${amountParts.second}${amountParts.third}",
                style = AppTypography.body1,
                modifier = Modifier.padding(start = Dimens.paddingMicro),
            )
        }
    }
}


fun Double?.formatMoneyParts(
    currency: String? = null,
    includeDecimals: Boolean = false
): Triple<String, String, String> {
    val numberParts = this.formatWithDelimiters().split(".")
    val decimalPart = numberParts.getOrNull(1) ?: "00"

    if (decimalPart == "00" && !includeDecimals) {
        return Triple(currency ?: "GHS ", numberParts[0], "")
    }

    return Triple(currency ?: "GHS ", numberParts[0], ".$decimalPart")
}

fun Double?.formatWithDelimiters(): String {
    if (this != null) {
        return DecimalFormat("#,###,##0.00").format(this)
    }

    return 0.0.formatWithDelimiters()
}


@Composable
fun TextFieldOverlay(modifier: Modifier) {
    Card(
        modifier = modifier
            .height(55.dp)
            .fillMaxWidth(),
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {}
}