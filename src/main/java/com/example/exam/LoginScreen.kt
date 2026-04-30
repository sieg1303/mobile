package com.example.exam

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    // Using mutableStateOf as requested
    val phoneText = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
            Image(
                painter = painterResource(id = android.R.drawable.ic_menu_gallery), 
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth().padding(24.dp).weight(1.5f)
        ) {
            Text(
                text = "Get your groceries\nwith nectar",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 32.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(30.dp))

            Column {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "🇧🇩", fontSize = 24.sp)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "+880", fontSize = 18.sp, color = Color.Black)
                    Spacer(modifier = Modifier.width(8.dp))

                    TextField(
                        value = phoneText.value,
                        onValueChange = { phoneText.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        singleLine = true
                    )
                }
                HorizontalDivider(thickness = 1.dp, color = Color(0xFFE2E2E2))
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Or connect with social media",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color(0xFF828282),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(35.dp))

            // Using lambdas for click handling
            SocialButton(
                text = "Continue with Google",
                backgroundColor = Color(0xFF5383EC),
                iconText = "G"
            ) { onLoginSuccess() }

            Spacer(modifier = Modifier.height(20.dp))

            SocialButton(
                text = "Continue with Facebook",
                backgroundColor = Color(0xFF4A66AC),
                iconText = "f"
            ) { onLoginSuccess() }
        }
    }
}

@Composable
fun SocialButton(
    text: String,
    backgroundColor: Color,
    iconText: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(65.dp),
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = iconText,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 22.sp,
                modifier = Modifier.align(Alignment.CenterStart).padding(start = 16.dp)
            )
            Text(
                text = text,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(onLoginSuccess = {})
}
