package com.example.exam

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import java.util.Locale

@Composable
fun CartScreen(
    cartItems: List<CartItem>,
    onCheckout: () -> Unit,
    onBackToShop: () -> Unit,
    onRemoveItem: (CartItem) -> Unit,
    onUpdateQuantity: (Product, Int) -> Unit
) {
    // Calculate total using lists.map and sumOf
    val totalPrice = cartItems.sumOf { item ->
        val priceValue = item.product.price.replace("$", "").toDoubleOrNull() ?: 0.0
        priceValue * item.quantity
    }

    Scaffold(
        topBar = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp), contentAlignment = Alignment.Center) {
                    Text(text = "My Cart", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                HorizontalDivider(color = Color(0xFFE2E2E2))
            }
        },
        bottomBar = {
            Column {
                Box(modifier = Modifier.padding(24.dp).fillMaxWidth()) {
                    Button(
                        onClick = onCheckout,
                        modifier = Modifier.fillMaxWidth().height(67.dp),
                        shape = RoundedCornerShape(19.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF53B175))
                    ) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Go to Checkout", modifier = Modifier.align(Alignment.Center))
                            Surface(
                                color = Color(0xFF489E67),
                                shape = RoundedCornerShape(4.dp),
                                modifier = Modifier.align(Alignment.CenterEnd)
                            ) {
                                Text(
                                    text = "$${String.format(Locale.US, "%.2f", totalPrice)}",
                                    color = Color.White,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }
                }
                BottomNavigationBar(
                    currentTab = "Cart", 
                    onTabSelected = { tab -> if (tab == "Shop") onBackToShop() }
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            items(cartItems) { item ->
                CartItemRow(
                    item = item,
                    onRemove = { onRemoveItem(item) },
                    onIncrease = { onUpdateQuantity(item.product, item.quantity + 1) },
                    onDecrease = { if (item.quantity > 1) onUpdateQuantity(item.product, item.quantity - 1) }
                )
                HorizontalDivider(color = Color(0xFFE2E2E2), modifier = Modifier.padding(horizontal = 24.dp))
            }
        }
    }
}

data class CartItem(
    val product: Product,
    val quantity: Int
)

@Composable
fun CartItemRow(
    item: CartItem,
    onRemove: () -> Unit,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = item.product.imageRes),
            contentDescription = null,
            modifier = Modifier.size(70.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.width(20.dp))
        Column(modifier = Modifier.weight(1f)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = item.product.name, fontWeight = FontWeight.Bold)
                IconButton(onClick = onRemove, modifier = Modifier.size(24.dp)) {
                    Icon(Icons.Default.Close, contentDescription = null, tint = Color.Gray)
                }
            }
            Text(text = item.product.unit, color = Color.Gray)
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onDecrease,
                        modifier = Modifier.size(45.dp).border(1.dp, Color.LightGray, RoundedCornerShape(17.dp))
                    ) {
                        Icon(Icons.Default.Remove, contentDescription = null, tint = Color.Gray)
                    }
                    Text(text = item.quantity.toString(), modifier = Modifier.padding(horizontal = 15.dp), fontWeight = FontWeight.Bold)
                    IconButton(
                        onClick = onIncrease,
                        modifier = Modifier.size(45.dp).border(1.dp, Color.LightGray, RoundedCornerShape(17.dp))
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = Color(0xFF53B175))
                    }
                }
                Text(text = item.product.price, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {
    CartScreen(
        cartItems = listOf(),
        onCheckout = {},
        onBackToShop = {},
        onRemoveItem = {},
        onUpdateQuantity = { _, _ -> }
    )
}
