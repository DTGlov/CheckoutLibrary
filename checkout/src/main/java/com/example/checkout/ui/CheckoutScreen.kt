package com.example.checkout.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.checkoutlibrary.ui.checkout.utils.Dimens
import com.example.checkoutlibrary.ui.theme.CheckTheme

@Composable
fun CheckoutScreen() {
    HBScaffold(
        topBar = {
            HBTopAppBar(
                onNavigateUp = {
                },
                title = {
                    Text(
                        text = "Confirm",
                        style = CheckTheme.typography.h3,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1f),
                    )
                },
                actions = {
                    TextButton(
                        onClick = {
                        },
                    ) {
                        Text(
                            text = "Hello",
                            color = CheckTheme.colors.error,
                        )
                    }
                },
            )
        },
        bottomBar = {
            Column(Modifier.animateContentSize()) {
                Divider(
                    color = CheckTheme.colors.outline,
                    thickness = 2.dp,
                )

                LoadingTealTextButton(
                    text = "PAY NOW",
                    onClick = {
                    },
                    enabled = true,
                    loading = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize()
                        .padding(16.dp),
                )
            }
        },
        backgroundColor = CheckTheme.colors.uiBackground,
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {

            CheckoutReceiptCard(
                logoUrl = "https://i0.wp.com/thebftonline.com/wp-content/uploads/2021/08/vodafone-cash-ghana-min.jpg?fit=1200%2C1200&ssl=1",
                accountName = "David Tawiah Glover",
                accountNumber = "",
                serviceName = "Vodafone Cash",
//                fees = feeItems,
                amount = 20.00,
                total = 40.00,
                modifier = Modifier
                    .padding(12.dp)
                    .animateContentSize()
            )

            Text(
                text = "Pay with",
                style = CheckTheme.typography.h2,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimens.paddingDefault)
                    .padding(horizontal = Dimens.paddingDefault)
                    .background(
                        color = CheckTheme.colors.cardBackground,
                        shape = CheckTheme.shapes.medium.copy(
                            bottomStart = CornerSize(0.dp),
                            bottomEnd = CornerSize(0.dp),
                        ),
                    )
                    .padding(Dimens.paddingDefault),
            )

        }
    }
}