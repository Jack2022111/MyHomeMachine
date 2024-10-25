package com.example.myhomemachine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myhomemachine.ui.theme.MyHomeMachineTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyHomeMachineTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyNavHost(navController = navController, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// Move LoginScreen and HomeScreen composables here, above MyNavHost

@Composable
fun LoginScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Welcome! Please Log In")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("home") }) {
            Text(text = "Login")
        }
    }
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Define each button with the specified labels
        Button(onClick = { /* No action required */ }) {
            Text(text = "Lights")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { /* No action required */ }) {
            Text(text = "Plugs")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { /* No action required */ }) {
            Text(text = "Sensors")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { /* No action required */ }) {
            Text(text = "Other1")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { /* No action required */ }) {
            Text(text = "Other2")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { /* No action required */ }) {
            Text(text = "Other")
        }
    }
}


// Now MyNavHost can reference the LoginScreen and HomeScreen correctly
@Composable
fun MyNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen() }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    val navController = rememberNavController()
    LoginScreen(navController)
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}
