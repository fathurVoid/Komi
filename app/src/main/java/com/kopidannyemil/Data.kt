package com.kopidannyemil

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.util.Locale

enum class Category(val label: String) {
    KOPI("Coffee"), MINUMAN("Drinks"), MAKANAN("Food")
}

data class MenuItem(
    val id: Int,
    val name: String,
    val desc: String,
    val price: Int,
    val category: Category,
    val imageRes: Int
)

data class User(val username: String, val password: String, val name: String)

val dummyMenu = listOf(
    // Coffee
    MenuItem(1, "Espresso", "Pure concentrated & bold coffee shot", 18000, Category.KOPI, R.drawable.espresso),
    MenuItem(2, "Doppio", "Rich & intense double shot espresso", 22000, Category.KOPI, R.drawable.doppio),
    MenuItem(3, "Americano", "Classic espresso diluted with fresh water", 20000, Category.KOPI, R.drawable.americano),
    MenuItem(4, "Cold Brew Coffee", "Smooth & refreshing cold-steeped coffee", 25000, Category.KOPI, R.drawable.coldbrewcoffee),
    MenuItem(5, "Cappuccino", "Espresso with steamed milk & velvety foam", 25000, Category.KOPI, R.drawable.cappucino),
    MenuItem(6, "Mocca", "Espresso blended with rich chocolate & fresh milk", 27000, Category.KOPI, R.drawable.mocca),
    MenuItem(7, "Latte", "Smooth espresso with creamy steamed milk", 26000, Category.KOPI, R.drawable.latte),
    MenuItem(8, "Macchiato", "Espresso marked with a touch of milk foam", 24000, Category.KOPI, R.drawable.macchiato),
    
    // Drinks
    MenuItem(9, "Hot Chocolate", "Rich and creamy Belgian chocolate drink", 24000, Category.MINUMAN, R.drawable.hotchocolate),
    MenuItem(10, "Fresh Milk", "Pure chilled fresh dairy milk", 15000, Category.MINUMAN, R.drawable.freshmilk),
    MenuItem(11, "Matcha Green Tea", "Authentic Japanese matcha with fresh milk", 28000, Category.MINUMAN, R.drawable.matchagreentea),
    MenuItem(12, "Lemon Tea", "Refreshing black tea with fresh lemon slice", 16000, Category.MINUMAN, R.drawable.lemontea),
    MenuItem(13, "Taro Latte", "Sweet and creamy taro flavored milk drink", 26000, Category.MINUMAN, R.drawable.tarolatte),

    // Food
    MenuItem(14, "Chocolate Donut", "Soft & fluffy donut with chocolate glaze", 12000, Category.MAKANAN, R.drawable.chocolatedonut),
    MenuItem(15, "Sourdough Loaf", "Naturally fermented sourdough bread", 35000, Category.MAKANAN, R.drawable.sourdoughloaf),
    MenuItem(16, "Butter Croissant", "Flaky & buttery fresh pastry", 22000, Category.MAKANAN, R.drawable.buttercroissant),
    MenuItem(17, "French Fries", "Crispy golden fries served with dip", 18000, Category.MAKANAN, R.drawable.frenchfries)
)

fun formatRupiah(value: Int): String =
    "Rp " + String.format(Locale.US, "%,d", value).replace(',', '.')

class AppState {
    val users = mutableStateListOf(User("demo", "demo12", "demo"))
    var currentUser by mutableStateOf<User?>(null)
    val cart = mutableStateMapOf<Int, Int>()

    val displayName: String get() = currentUser?.name ?: "Guest"
    val totalItems: Int get() = cart.values.sum()
    val totalPrice: Int
        get() = cart.entries.sumOf { (id, qty) -> (dummyMenu.firstOrNull { it.id == id }?.price ?: 0) * qty }

    fun add(id: Int) { cart[id] = (cart[id] ?: 0) + 1 }
    fun remove(id: Int) {
        val q = (cart[id] ?: 0) - 1
        if (q <= 0) cart.remove(id) else cart[id] = q
    }

    fun login(username: String, password: String): String? {
        val u = users.firstOrNull { it.username.equals(username.trim(), true) && it.password == password }
        return if (u == null) "Invalid username or password" else { currentUser = u; null }
    }

    fun register(name: String, username: String, password: String): String? {
        if (name.isBlank() || username.isBlank() || password.isBlank()) return "All fields are required"
        if (password.length < 6) return "Password must be at least 6 characters"
        if (users.any { it.username.equals(username.trim(), true) }) return "Username is already taken"
        val u = User(username.trim(), password, name.trim())
        users.add(u); currentUser = u
        return null
    }

    fun updateName(newName: String) {
        val u = currentUser ?: return
        val updated = u.copy(name = newName.trim())
        val i = users.indexOf(u)
        if (i >= 0) users[i] = updated
        currentUser = updated
    }

    fun logout() { currentUser = null; cart.clear() }
}
