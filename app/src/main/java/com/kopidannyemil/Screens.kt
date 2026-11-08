package com.kopidannyemil

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private fun Category.tint(): Color = when (this) {
    Category.KOPI -> Color(0xFFEFE6DD)
    Category.MINUMAN -> Color(0xFFE2E8F0)
    Category.MAKANAN -> Color(0xFFE9E9EB)
}

@Composable
fun Brand(height: Dp = 36.dp, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logo_komi),
        contentDescription = "Komi Shop",
        modifier = modifier.height(height),
        contentScale = ContentScale.Fit
    )
}

@Composable
fun CoffeeButton(
    text: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
    filled: Boolean = true,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    if (filled) {
        Button(
            onClick = onClick, enabled = enabled,
            modifier = modifier.height(54.dp),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Brown, contentColor = Cream,
                disabledContainerColor = Tan, disabledContentColor = Muted
            )
        ) { Text(text, fontWeight = FontWeight.SemiBold, fontSize = 16.sp) }
    } else {
        OutlinedButton(
            onClick = onClick, enabled = enabled,
            modifier = modifier.height(54.dp),
            shape = RoundedCornerShape(28.dp),
            border = BorderStroke(1.5.dp, Brown),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Brown)
        ) { Text(text, fontWeight = FontWeight.SemiBold, fontSize = 16.sp) }
    }
}

@Composable
fun CoffeeField(
    value: String,
    onChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    boxed: Boolean = true,
    password: Boolean = false,
    minLines: Int = 1
) {
    TextField(
        value = value,
        onValueChange = onChange,
        label = { Text(label) },
        modifier = modifier.fillMaxWidth(),
        singleLine = minLines == 1,
        minLines = minLines,
        shape = if (boxed) RoundedCornerShape(16.dp) else RoundedCornerShape(0.dp),
        visualTransformation = if (password) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = if (password) KeyboardType.Password else KeyboardType.Text),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = if (boxed) BeigeLight else Color.Transparent,
            unfocusedContainerColor = if (boxed) BeigeLight else Color.Transparent,
            focusedIndicatorColor = if (boxed) Color.Transparent else Brown,
            unfocusedIndicatorColor = if (boxed) Color.Transparent else Tan,
            cursorColor = Brown,
            focusedLabelColor = Brown,
            unfocusedLabelColor = Muted,
            focusedTextColor = Brown,
            unfocusedTextColor = Brown
        )
    )
}

@Composable
fun RoundIconButton(icon: ImageVector, desc: String, bg: Color = BeigeLight, tint: Color = Brown, onClick: () -> Unit) {
    Box(
        Modifier.size(44.dp).clip(CircleShape).background(bg).clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) { Icon(icon, desc, tint = tint) }
}

@Composable
fun ErrorText(msg: String?) {
    if (msg != null) Text(msg, color = ErrorRed, fontSize = 13.sp)
}

@Composable
fun HeroHeader(height: Dp, onBack: (() -> Unit)? = null) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
            .background(Brown)
            .statusBarsPadding()
    ) {
        if (onBack != null) {
            Box(Modifier.padding(16.dp).align(Alignment.TopStart)) {
                RoundIconButton(Icons.AutoMirrored.Filled.ArrowBack, "Back", bg = BeigeLight.copy(alpha = 0.2f), tint = Cream, onClick = onBack)
            }
        }
        Image(
            painter = painterResource(id = R.drawable.logo_komi),
            contentDescription = "Komi Shop Logo",
            colorFilter = ColorFilter.tint(Cream),
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.8f)
                .height(140.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun AuthScaffold(title: String, subtitle: String, onBack: () -> Unit, content: @Composable ColumnScope.() -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .coffeeBackground()
            .navigationBarsPadding()
            .imePadding()
            .verticalScroll(rememberScrollState())
    ) {
        HeroHeader(240.dp, onBack)
        Spacer(Modifier.height(20.dp))
        Column(Modifier.padding(horizontal = 28.dp)) {
            Text(title, fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Brown)
            Spacer(Modifier.height(6.dp))
            Text(subtitle, color = Muted, fontSize = 14.sp, lineHeight = 20.sp)
            Spacer(Modifier.height(20.dp))
            content()
            Spacer(Modifier.height(28.dp))
        }
    }
}

@Composable
fun FormScaffold(title: String, subtitle: String, onBack: () -> Unit, content: @Composable ColumnScope.() -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .coffeeBackground()
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RoundIconButton(Icons.AutoMirrored.Filled.ArrowBack, "Back", onClick = onBack)
            Spacer(Modifier.width(14.dp))
            Text(title, fontSize = 26.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(8.dp))
        Text(subtitle, color = Muted, fontSize = 14.sp)
        Spacer(Modifier.height(20.dp))
        content()
        Spacer(Modifier.height(20.dp))
    }
}

@Composable
fun SplashScreen(onStart: () -> Unit) {
    Box(
        Modifier
            .fillMaxSize()
            .coffeeBackground()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_komi),
                contentDescription = "Komi Shop Logo",
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(180.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(Modifier.height(12.dp))
            Text(
                "Get a taste of your wish",
                color = Muted,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
        }

        CoffeeButton(
            "Get Started",
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = onStart
        )
    }
}

@Composable
fun SignUpScreen(onRegister: () -> Unit, onLogin: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .coffeeBackground()
            .navigationBarsPadding()
    ) {
        HeroHeader(280.dp)
        Spacer(Modifier.height(24.dp))
        Column(
            Modifier
                .weight(1f)
                .padding(horizontal = 28.dp)
                .padding(bottom = 24.dp)
        ) {
            Text("Sign Up", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Brown)
            Spacer(Modifier.height(8.dp))
            Text(
                "Join as a member & experience delicious coffee and treats from Komi Shop",
                color = Muted, fontSize = 14.sp, lineHeight = 20.sp
            )
            Spacer(Modifier.weight(1f))
            CoffeeButton("Register", onClick = onRegister)
            Spacer(Modifier.height(12.dp))
            CoffeeButton("Login", filled = false, onClick = onLogin)
        }
    }
}

@Composable
fun LoginScreen(state: AppState, onBack: () -> Unit, onSuccess: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    AuthScaffold("Login", "Sign in to start ordering. (Demo account: demo / demo12)", onBack) {
        CoffeeField(username, { username = it; error = null }, "Username", boxed = false)
        Spacer(Modifier.height(8.dp))
        CoffeeField(password, { password = it; error = null }, "Password", boxed = false, password = true)
        Spacer(Modifier.height(12.dp))
        ErrorText(error)
        Spacer(Modifier.height(20.dp))
        CoffeeButton("Login") {
            error = state.login(username, password)
            if (error == null) onSuccess()
        }
    }
}

@Composable
fun RegisterScreen(state: AppState, onBack: () -> Unit, onSuccess: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    AuthScaffold("Register", "Create a new account, quick and free.", onBack) {
        CoffeeField(name, { name = it; error = null }, "Full Name", boxed = false)
        Spacer(Modifier.height(8.dp))
        CoffeeField(username, { username = it; error = null }, "Username", boxed = false)
        Spacer(Modifier.height(8.dp))
        CoffeeField(password, { password = it; error = null }, "Password (min. 6 characters)", boxed = false, password = true)
        Spacer(Modifier.height(12.dp))
        ErrorText(error)
        Spacer(Modifier.height(20.dp))
        CoffeeButton("Register") {
            error = state.register(name, username, password)
            if (error == null) onSuccess()
        }
    }
}

@Composable
fun HomeScreen(state: AppState, onOrder: () -> Unit, onProfile: () -> Unit) {
    var query by remember { mutableStateOf("") }
    var category by remember { mutableStateOf<Category?>(null) }

    val filtered = dummyMenu.filter {
        (category == null || it.category == category) &&
            (query.isBlank() || it.name.contains(query.trim(), true) || it.desc.contains(query.trim(), true))
    }

    Column(Modifier.fillMaxSize().coffeeBackground().statusBarsPadding().navigationBarsPadding()) {
        Column(
            Modifier.fillMaxWidth().padding(horizontal = 20.dp).padding(top = 12.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Brand(height = 36.dp)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        Modifier.size(40.dp).clip(CircleShape).background(BeigeLight)
                            .clickable(onClick = onOrder),
                        contentAlignment = Alignment.Center
                    ) {
                        if (state.totalItems > 0) {
                            BadgedBox(
                                badge = {
                                    Badge(containerColor = ErrorRed, contentColor = Color.White) {
                                        Text("${state.totalItems}")
                                    }
                                }
                            ) {
                                Icon(Icons.Default.ShoppingBag, "Order", tint = Brown, modifier = Modifier.size(20.dp))
                            }
                        } else {
                            Icon(Icons.Default.ShoppingBag, "Order", tint = Brown, modifier = Modifier.size(20.dp))
                        }
                    }
                    Box(
                        Modifier.size(40.dp).clip(CircleShape).background(BeigeLight)
                            .clickable(onClick = onProfile),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Person, "Profile", tint = Brown, modifier = Modifier.size(20.dp))
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            Text("Hello ${state.displayName},", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text("What coffee or snack would you like today?", color = Muted, fontSize = 13.sp)
        }

        TextField(
            value = query,
            onValueChange = { query = it },
            placeholder = { Text("Search menu...") },
            leadingIcon = { Icon(Icons.Default.Search, null) },
            singleLine = true,
            shape = RoundedCornerShape(28.dp),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 16.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = BeigeLight, unfocusedContainerColor = BeigeLight,
                focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Brown, focusedTextColor = Brown, unfocusedTextColor = Brown,
                focusedLeadingIconColor = BrownMid, unfocusedLeadingIconColor = Muted,
                focusedPlaceholderColor = Muted, unfocusedPlaceholderColor = Muted
            )
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item { CategoryChip("All", category == null) { category = null } }
            items(Category.values().toList()) { c ->
                CategoryChip(c.label, category == c) { category = c }
            }
        }

        Text(
            "Food & Drinks",
            Modifier.padding(horizontal = 20.dp, vertical = 14.dp),
            fontSize = 18.sp, fontWeight = FontWeight.Bold
        )

        if (filtered.isEmpty()) {
            Box(Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text("No items found", color = Muted)
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(filtered, key = { it.id }) { item ->
                    MenuCard(
                        item = item,
                        qty = state.cart[item.id] ?: 0,
                        onAdd = { state.add(item.id) },
                        onRemove = { state.remove(item.id) }
                    )
                }
            }
        }

        if (state.totalItems > 0) {
            Row(
                Modifier.fillMaxWidth().padding(16.dp).clip(RoundedCornerShape(26.dp)).background(Brown)
                    .padding(start = 20.dp, end = 10.dp, top = 10.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.ShoppingBag, null, tint = Cream)
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text("${state.totalItems} items", color = Tan, fontSize = 12.sp)
                    Text(formatRupiah(state.totalPrice), color = Cream, fontWeight = FontWeight.Bold, fontSize = 17.sp)
                }
                Button(
                    onClick = onOrder,
                    shape = RoundedCornerShape(22.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Cream, contentColor = Brown)
                ) { Text("Order", fontWeight = FontWeight.Bold) }
            }
        }
    }
}

@Composable
fun CategoryChip(label: String, selected: Boolean, onClick: () -> Unit) {
    Box(
        Modifier.clip(RoundedCornerShape(50)).background(if (selected) Brown else Tan)
            .clickable(onClick = onClick).padding(horizontal = 18.dp, vertical = 9.dp)
    ) {
        Text(label, color = if (selected) Cream else Brown, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
    }
}

@Composable
fun MenuCard(item: MenuItem, qty: Int, onAdd: () -> Unit, onRemove: () -> Unit) {
    Column(Modifier.fillMaxWidth().clip(RoundedCornerShape(22.dp)).background(BeigeLight).padding(10.dp)) {
        Box(
            Modifier.fillMaxWidth().height(96.dp).clip(RoundedCornerShape(16.dp)).background(item.category.tint()),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(Modifier.height(10.dp))
        Text(item.name, fontWeight = FontWeight.Bold, fontSize = 14.sp, lineHeight = 18.sp,
            minLines = 2, maxLines = 2, overflow = TextOverflow.Ellipsis)
        Text(item.desc, color = Muted, fontSize = 11.sp, lineHeight = 14.sp,
            minLines = 2, maxLines = 2, overflow = TextOverflow.Ellipsis)
        Spacer(Modifier.height(6.dp))
        Text(formatRupiah(item.price), fontWeight = FontWeight.Bold, color = BrownMid, fontSize = 14.sp)
        Spacer(Modifier.height(8.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically) {
            if (qty == 0) {
                Box(
                    Modifier.size(36.dp).clip(CircleShape).background(Brown).clickable(onClick = onAdd),
                    contentAlignment = Alignment.Center
                ) { Icon(Icons.Default.Add, "Add", tint = Cream, modifier = Modifier.size(20.dp)) }
            } else {
                Row(
                    Modifier.clip(RoundedCornerShape(50)).background(Brown).padding(horizontal = 4.dp, vertical = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(Modifier.size(32.dp).clip(CircleShape).clickable(onClick = onRemove), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Remove, "Reduce", tint = Cream, modifier = Modifier.size(18.dp))
                    }
                    Text("$qty", color = Cream, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 6.dp))
                    Box(Modifier.size(32.dp).clip(CircleShape).clickable(onClick = onAdd), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Add, "Add", tint = Cream, modifier = Modifier.size(18.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileScreen(state: AppState, onBack: () -> Unit, onLogout: () -> Unit) {
    var name by remember { mutableStateOf(state.displayName) }
    var saved by remember { mutableStateOf(false) }

    Column(
        Modifier.fillMaxSize().coffeeBackground().navigationBarsPadding().imePadding()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(bottomStart = 36.dp, bottomEnd = 36.dp))
                .background(Brown).statusBarsPadding().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(Modifier.fillMaxWidth()) {
                RoundIconButton(Icons.AutoMirrored.Filled.ArrowBack, "Back", onClick = onBack)
            }
            Image(
                painter = painterResource(id = R.drawable.logo_komi),
                contentDescription = "Komi Shop Logo",
                colorFilter = ColorFilter.tint(Cream),
                modifier = Modifier.height(32.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(Modifier.height(14.dp))
            Box(Modifier.size(80.dp).clip(CircleShape).background(Tan), contentAlignment = Alignment.Center) {
                Text(state.displayName.take(1).uppercase(), fontSize = 36.sp, fontWeight = FontWeight.Bold, color = Brown)
            }
            Spacer(Modifier.height(12.dp))
            Text("Hello ${state.displayName},", color = Cream, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text("My Profile", color = Tan, fontSize = 14.sp)
            Spacer(Modifier.height(16.dp))
        }
        Column(Modifier.padding(24.dp)) {
            Text("Profile Settings", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(Modifier.height(12.dp))
            CoffeeField(name, { name = it; saved = false }, "Full Name")
            if (saved) {
                Spacer(Modifier.height(10.dp))
                Text("✓ Profile updated successfully", color = Color(0xFF2E7D32), fontSize = 13.sp)
            }
            Spacer(Modifier.height(24.dp))
            CoffeeButton("Save Changes", enabled = name.isNotBlank()) {
                state.updateName(name)
                saved = true
            }
            Spacer(Modifier.height(12.dp))
            CoffeeButton("Back to Home", filled = false, onClick = onBack)
            Spacer(Modifier.height(8.dp))
            TextButton(onClick = onLogout, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text("Log Out", color = ErrorRed, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
fun AddressScreen(state: AppState, onBack: () -> Unit, onConfirm: () -> Unit) {
    var name by remember { mutableStateOf(state.displayName) }
    var address by remember { mutableStateOf("") }
    var landmark by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    FormScaffold("Order", "Fill in your delivery address.", onBack) {
        if (state.cart.isEmpty()) {
            Box(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(22.dp)).background(BeigeLight).padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("🛒", fontSize = 48.sp)
                    Spacer(Modifier.height(8.dp))
                    Text("Your cart is currently empty", fontWeight = FontWeight.Bold, color = Brown, fontSize = 16.sp)
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "Choose your favorite coffee or snack on Home first!",
                        color = Muted, fontSize = 13.sp, textAlign = TextAlign.Center
                    )
                }
            }
        } else {
            Column(Modifier.fillMaxWidth().clip(RoundedCornerShape(22.dp)).background(BeigeLight).padding(16.dp)) {
                Text("Order Summary", fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(10.dp))
                state.cart.entries.forEach { (id, qty) ->
                    val m = dummyMenu.firstOrNull { it.id == id } ?: return@forEach
                    Row(Modifier.fillMaxWidth().padding(vertical = 3.dp), verticalAlignment = Alignment.CenterVertically) {
                        Box(Modifier.size(32.dp).clip(RoundedCornerShape(10.dp)).background(m.category.tint()), contentAlignment = Alignment.Center) {
                            Image(
                                painter = painterResource(id = m.imageRes),
                                contentDescription = m.name,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(Modifier.width(10.dp))
                        Text("${m.name} ×$qty", Modifier.weight(1f), fontSize = 13.sp)
                        Text(formatRupiah(m.price * qty), fontSize = 13.sp, color = BrownMid)
                    }
                }
                Box(Modifier.fillMaxWidth().padding(vertical = 10.dp).height(1.dp).background(Tan))
                Row(Modifier.fillMaxWidth()) {
                    Text("Total", Modifier.weight(1f), fontWeight = FontWeight.Bold)
                    Text(formatRupiah(state.totalPrice), fontWeight = FontWeight.Bold)
                }
            }
        }
        Spacer(Modifier.height(20.dp))
        CoffeeField(name, { name = it; error = null }, "Full Name")
        Spacer(Modifier.height(14.dp))
        CoffeeField(address, { address = it; error = null }, "Delivery Address", minLines = 3)
        Spacer(Modifier.height(14.dp))
        CoffeeField(landmark, { landmark = it }, "Landmark / Additional Notes", minLines = 3)
        Spacer(Modifier.height(12.dp))
        ErrorText(error)
        Spacer(Modifier.height(20.dp))
        CoffeeButton("Place Order") {
            error = when {
                state.cart.isEmpty() -> "Your cart is empty, please select items from Home first"
                name.isBlank() || address.isBlank() -> "Name and delivery address are required"
                else -> null
            }
            if (error == null) onConfirm()
        }
    }
}

@Composable
fun ConfirmScreen(state: AppState, onDone: () -> Unit) {
    Column(
        Modifier.fillMaxSize().coffeeBackground().statusBarsPadding().navigationBarsPadding().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Brand(height = 36.dp, modifier = Modifier.align(Alignment.Start).padding(top = 16.dp))
        Spacer(Modifier.weight(1f))
        Box(Modifier.size(132.dp).clip(CircleShape).background(Brown), contentAlignment = Alignment.Center) {
            Icon(Icons.Default.Check, null, Modifier.size(68.dp), tint = Cream)
        }
        Spacer(Modifier.height(28.dp))
        Text(
            "Thank you for ordering at Komi Shop! Please wait a moment, our courier will deliver your order soon.",
            textAlign = TextAlign.Center, color = Brown, fontSize = 16.sp, lineHeight = 24.sp
        )
        Spacer(Modifier.height(20.dp))
        Column(
            Modifier.clip(RoundedCornerShape(20.dp)).background(BeigeLight).padding(horizontal = 28.dp, vertical = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("${state.totalItems} items", color = Muted, fontSize = 13.sp)
            Text(formatRupiah(state.totalPrice), fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
        Spacer(Modifier.weight(1f))
        CoffeeButton("Done", onClick = onDone)
    }
}
