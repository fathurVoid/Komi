package com.kopidannyemil

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

object Routes {
    const val SPLASH = "splash"
    const val SIGNUP = "signup"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
    const val PROFILE = "profile"
    const val ADDRESS = "address"
    const val CONFIRM = "confirm"
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KopiTheme {
                val nav = rememberNavController()
                val state = remember { AppState() }

                val entry by nav.currentBackStackEntryAsState()
                val route = entry?.destination?.route
                val view = LocalView.current
                SideEffect {
                    val window = (view.context as Activity).window
                    WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                        route !in setOf(Routes.SIGNUP, Routes.LOGIN, Routes.REGISTER, Routes.PROFILE)
                }

                fun toHome() = nav.navigate(Routes.HOME) {
                    popUpTo(Routes.SIGNUP) { inclusive = true }
                    launchSingleTop = true
                }

                Scaffold { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .consumeWindowInsets(innerPadding)
                    ) {
                        NavHost(navController = nav, startDestination = Routes.SPLASH) {
                            composable(Routes.SPLASH) {
                                SplashScreen(onStart = { nav.navigate(Routes.SIGNUP) })
                            }
                            composable(Routes.SIGNUP) {
                                SignUpScreen(
                                    onRegister = { nav.navigate(Routes.REGISTER) },
                                    onLogin = { nav.navigate(Routes.LOGIN) }
                                )
                            }
                            composable(Routes.LOGIN) {
                                LoginScreen(state, onBack = { nav.popBackStack() }, onSuccess = { toHome() })
                            }
                            composable(Routes.REGISTER) {
                                RegisterScreen(state, onBack = { nav.popBackStack() }, onSuccess = { toHome() })
                            }
                            composable(Routes.HOME) {
                                HomeScreen(
                                    state,
                                    onOrder = { nav.navigate(Routes.ADDRESS) },
                                    onProfile = { nav.navigate(Routes.PROFILE) }
                                )
                            }
                            composable(Routes.PROFILE) {
                                ProfileScreen(
                                    state,
                                    onBack = { nav.popBackStack() },
                                    onLogout = {
                                        state.logout()
                                        nav.navigate(Routes.SIGNUP) {
                                            popUpTo(Routes.HOME) { inclusive = true }
                                        }
                                    }
                                )
                            }
                            composable(Routes.ADDRESS) {
                                AddressScreen(
                                    state,
                                    onBack = { nav.popBackStack() },
                                    onConfirm = { nav.navigate(Routes.CONFIRM) }
                                )
                            }
                            composable(Routes.CONFIRM) {
                                ConfirmScreen(state, onDone = {
                                    state.cart.clear()
                                    nav.navigate(Routes.HOME) {
                                        popUpTo(Routes.HOME) { inclusive = true }
                                    }
                                })
                            }
                        }
                    }
                }
            }
        }
    }
}
