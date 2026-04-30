package com.example.exam

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProductDetailScreen(
    product: Product, 
    onBack: () -> Unit,
    onAddToBasket: () -> Unit
) {
    val quantity = remember { mutableStateOf(1) }
    val isDetailExpanded = remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Back",
                        modifier = Modifier.size(32.dp)
                    )
                }
                IconButton(onClick = { /* Share logic with Intent */ }) {
                    Icon(Icons.Default.Share, contentDescription = "Share")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp))
                    .background(Color(0xFFF2F3F2)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = product.name,
                    modifier = Modifier.size(200.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Column(modifier = Modifier.padding(24.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = product.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = null, tint = Color.Gray)
                }

                Text(text = product.unit, color = Color.Gray, modifier = Modifier.padding(vertical = 4.dp))

                Spacer(modifier = Modifier.height(20.dp))

                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { if (quantity.value > 1) quantity.value-- }) {
                        Icon(Icons.Default.Remove, contentDescription = null, tint = Color.Gray)
                    }
                    Box(
                        modifier = Modifier.size(45.dp).border(1.dp, Color.LightGray, RoundedCornerShape(17.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = quantity.value.toString(), fontWeight = FontWeight.Bold)
                    }
                    IconButton(onClick = { quantity.value++ }) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = Color(0xFF53B175))
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = product.price, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(30.dp))
                HorizontalDivider(color = Color(0xFFE2E2E2))

                // Using lambdas and mutablestateof for expansion
                Column(modifier = Modifier.fillMaxWidth().clickable { isDetailExpanded.value = !isDetailExpanded.value }) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Product Detail", fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
                        Icon(
                            imageVector = if (isDetailExpanded.value) Icons.Default.KeyboardArrowDown else Icons.Default.ChevronRight,
                            contentDescription = null
                        )
                    }
                    if (isDetailExpanded.value) {
                        Text(
                            text = "Apples are nutritious. Apples may be good for weight loss. Apples may be good for your heart.",
                            color = Color.Gray
                        )
                    }
                }

                HorizontalDivider(color = Color(0xFFE2E2E2))

                Button(
                    onClick = onAddToBasket,
                    modifier = Modifier.fillMaxWidth().height(67.dp).padding(top = 20.dp),
                    shape = RoundedCornerShape(19.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF53B175))
                ) {
                    Text(text = "Add To Basket", fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailPreview() {
    ProductDetailScreen(
        product = Product(1, "Naturel Red Apple", "1kg, Price", "$4.99", android.R.drawable.ic_menu_gallery),
        onBack = {},
        onAddToBasket = {}
    )
}
