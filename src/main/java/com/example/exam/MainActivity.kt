package com.example.exam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.exam.ui.theme.ExamTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExamTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    // Using a list to hold the shared cart state
    val cartItemsState = remember { mutableStateOf(listOf<CartItem>()) }
    val selectedProductState = remember { mutableStateOf<Product?>(null) }

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen {
                navController.navigate("home")
            }
        }
        composable("home") {
            HomeScreen(
                onProductClick = { product ->
                    selectedProductState.value = product
                    navController.navigate("detail")
                },
                onCartClick = {
                    navController.navigate("cart")
                }
            )
        }
        composable("detail") {
            selectedProductState.value?.let { product ->
                ProductDetailScreen(
                    product = product,
                    onBack = { navController.popBackStack() },
                    onAddToBasket = {
                        // Using list logic: check if item exists with find
                        val currentList = cartItemsState.value
                        val existingItem = currentList.find { it.product.id == product.id }
                        
                        if (existingItem != null) {
                            cartItemsState.value = currentList.map { 
                                if (it.product.id == product.id) it.copy(quantity = it.quantity + 1) else it 
                            }
                        } else {
                            cartItemsState.value = currentList + CartItem(product, 1)
                        }
                    }
                )
            }
        }
        composable("cart") {
            CartScreen(
                cartItems = cartItemsState.value,
                onCheckout = { /* Handle Checkout logic */ },
                onBackToShop = { navController.navigate("home") },
                onRemoveItem = { itemToRemove ->
                    cartItemsState.value = cartItemsState.value.filter { it.product.id != itemToRemove.product.id }
                },
                onUpdateQuantity = { product, newQty ->
                    cartItemsState.value = cartItemsState.value.map {
                        if (it.product.id == product.id) it.copy(quantity = newQty) else it
                    }
                }
            )
        }
    }
}
