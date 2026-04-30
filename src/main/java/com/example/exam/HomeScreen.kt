package com.example.exam

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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

data class Product(
    val id: Int,
    val name: String,
    val unit: String,
    val price: String,
    val imageRes: Int,
    val color: Color = Color.White
)

data class Category(
    val name: String,
    val imageRes: Int,
    val backgroundColor: Color
)

@Composable
fun HomeScreen(onProductClick: (Product) -> Unit, onCartClick: () -> Unit) {
    // Using lists as requested
    val exclusiveOffers = listOf(
        Product(1, "Naturel Red Apple", "1kg, Price", "$4.99", android.R.drawable.ic_menu_gallery),
        Product(2, "Organic Bananas", "7pcs, Price", "$4.99", android.R.drawable.ic_menu_gallery)
    )

    val bestSelling = listOf(
        Product(3, "Bell Pepper Red", "1kg, Price", "$4.99", android.R.drawable.ic_menu_gallery),
        Product(4, "Ginger", "250g, Price", "$4.99", android.R.drawable.ic_menu_gallery)
    )

    val groceries = listOf(
        Category("Pulses", android.R.drawable.ic_menu_gallery, Color(0xFFFEF1E4)),
        Category("Rice", android.R.drawable.ic_menu_gallery, Color(0xFFE5F3EA))
    )

    Scaffold(
        bottomBar = { 
            BottomNavigationBar(
                currentTab = "Shop", 
                onTabSelected = { tab ->
                    if (tab == "Cart") onCartClick()
                }
            ) 
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .background(Color.White)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null, tint = Color(0xFFF3603F), modifier = Modifier.size(30.dp))
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, modifier = Modifier.size(18.dp), tint = Color(0xFF4C4F4D))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Dhaka, Banassre", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF4C4F4D))
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            SearchBar()
            Spacer(modifier = Modifier.height(20.dp))

            // Banner Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .height(115.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF2F3F2))
            ) {
                Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(100.dp))
                    Column {
                        Text(text = "Fresh Vegetables", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text(text = "Get Up To 40% OFF", fontSize = 14.sp, color = Color(0xFF53B175))
                    }
                }
                Image(
                    painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                    contentDescription = null,
                    modifier = Modifier.fillMaxHeight().width(100.dp),
                    contentScale = ContentScale.Fit
                )
            }

            SectionHeader("Exclusive Offer")
            LazyRow(contentPadding = PaddingValues(horizontal = 24.dp), horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                items(exclusiveOffers) { product ->
                    ProductCard(product, onClick = { onProductClick(product) })
                }
            }

            SectionHeader("Best Selling")
            LazyRow(contentPadding = PaddingValues(horizontal = 24.dp), horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                items(bestSelling) { product ->
                    ProductCard(product, onClick = { onProductClick(product) })
                }
            }

            SectionHeader("Groceries")
            LazyRow(contentPadding = PaddingValues(horizontal = 24.dp), horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                items(groceries) { category -> CategoryCard(category) }
            }
            
            Spacer(modifier = Modifier.height(15.dp))
            LazyRow(contentPadding = PaddingValues(horizontal = 24.dp), horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                item {
                    val p = Product(5, "Beef Bone", "1kg, Price", "$4.99", android.R.drawable.ic_menu_gallery)
                    ProductCard(p, onClick = { onProductClick(p) })
                }
                item {
                    val p = Product(6, "Broiler Chicken", "1kg, Price", "$4.99", android.R.drawable.ic_menu_gallery)
                    ProductCard(p, onClick = { onProductClick(p) })
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun SearchBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .height(52.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color(0xFFF2F3F2))
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = Color.Black)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Search Store", color = Color(0xFF7C7C7C), fontSize = 14.sp)
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = "See all", color = Color(0xFF53B175), fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun ProductCard(product: Product, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(174.dp)
            .height(248.dp)
            .border(1.dp, Color(0xFFE2E2E2), RoundedCornerShape(18.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(15.dp)) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(80.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = product.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = product.unit, color = Color(0xFF7C7C7C), fontSize = 14.sp)
            Spacer(modifier = Modifier.weight(1f))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(text = product.price, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Box(
                    modifier = Modifier.size(45.dp).clip(RoundedCornerShape(17.dp)).background(Color(0xFF53B175)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = Color.White)
                }
            }
        }
    }
}

@Composable
fun CategoryCard(category: Category) {
    Row(
        modifier = Modifier
            .width(248.dp)
            .height(105.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(category.backgroundColor)
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = category.imageRes),
            contentDescription = null,
            modifier = Modifier.size(70.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.width(15.dp))
        Text(text = category.name, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun BottomNavigationBar(currentTab: String, onTabSelected: (String) -> Unit) {
    NavigationBar(containerColor = Color.White, tonalElevation = 8.dp) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("Shop") },
            selected = currentTab == "Shop",
            onClick = { onTabSelected("Shop") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF53B175),
                selectedTextColor = Color(0xFF53B175),
                unselectedIconColor = Color.Black,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Search, contentDescription = null) },
            label = { Text("Explore") },
            selected = currentTab == "Explore",
            onClick = { onTabSelected("Explore") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = null) },
            label = { Text("Cart") },
            selected = currentTab == "Cart",
            onClick = { onTabSelected("Cart") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF53B175),
                selectedTextColor = Color(0xFF53B175),
                unselectedIconColor = Color.Black,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.FavoriteBorder, contentDescription = null) },
            label = { Text("Favourite") },
            selected = currentTab == "Favourite",
            onClick = { onTabSelected("Favourite") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = null) },
            label = { Text("Account") },
            selected = currentTab == "Account",
            onClick = { onTabSelected("Account") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(onProductClick = {}, onCartClick = {})
}
