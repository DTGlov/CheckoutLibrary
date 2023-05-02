package com.example.checkoutlibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.checkoutlibrary.ui.checkout.CheckoutScreen
import com.example.checkoutlibrary.ui.theme.CheckTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CheckTheme {
                // A surface container using the 'background' color from the theme
                CheckoutScreen()
            }
        }
    }
}
