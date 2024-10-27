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
import androidx.compose.material3.TextField // add this import if it’s not present
import androidx.compose.runtime.remember // add this import if it’s not present
import androidx.compose.runtime.mutableStateOf // add this import if it’s not present
import androidx.compose.runtime.*
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import com.example.myhomemachine.data.DeviceManager

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp



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

@Composable
fun FirstScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to MyHomeMachine", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("login") }) {
            Text(text = "Login")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { navController.navigate("signup") }) {
            Text(text = "Sign Up")
        }
    }
}

@Composable
fun SignupScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var signupSuccess by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sign Up")

        Spacer(modifier = Modifier.height(16.dp))

        // Email Input
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Password Input
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Sign Up Button
        if (!signupSuccess) {
            Button(onClick = {
                signupSuccess = true
            }) {
                Text(text = "Sign Up")
            }
        }

        // Success Message and Continue Button
        if (signupSuccess) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Sign-up successful!")
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                navController.navigate("first")
            }) {
                Text(text = "Continue")
            }
        }
    }
}


@Composable
fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginSuccess by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Login")

        Spacer(modifier = Modifier.height(16.dp))

        // Email Input
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Password Input
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Login Button
        if (!loginSuccess) {
            Button(onClick = {
                loginSuccess = true
            }) {
                Text(text = "Login")
            }
        }

        // Success Message and Continue Button
        if (loginSuccess) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Login successful!")
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                navController.navigate("home")
            }) {
                Text(text = "Continue")
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Clock Schedule Button
                Button(onClick = {
                    navController.navigate("schedule")
                }) {
                    Text(text = "Clock Schedule")
                }

                // Add Device Button
                Button(onClick = {
                    navController.navigate("addDevice")
                }) {
                    Text(text = "+ Add Device")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Home Page",
                style = MaterialTheme.typography.bodyLarge, // Default style, replaces `h5`
                modifier = Modifier.padding(16.dp)
            )
            Button(onClick = { navController.navigate("lights") }) {
                Text(text = "Lights")
            }
            Button(onClick = { navController.navigate("plugs") }) {
                Text(text = "Plugs")
            }
            Button(onClick = { navController.navigate("cameras") }) {
                Text(text = "Cameras")
            }
            Button(onClick = { navController.navigate("sensors") }) {
                Text(text = "Sensors")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDeviceScreen(onDeviceAdded: () -> Unit, navController: NavHostController) {
    // Define device types and associated device names
    val deviceTypes = listOf("camera", "light", "plug", "sensor", "other1", "other2")
    val devicesByType = mapOf(
        "camera" to listOf("camera1", "camera2"),
        "light" to listOf("light1", "light2"),
        "plug" to listOf("plug1", "plug2"),
        "sensor" to listOf("sensor1", "sensor2")
    )

    // State variables for device type and name selection
    var selectedDeviceType by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedDeviceName by remember { mutableStateOf<String?>(null) }
    var customDeviceName by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        // Dropdown for selecting device type
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
                .padding(16.dp)
        ) {
            Text(text = selectedDeviceType.ifEmpty { "Select Device Type" })
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            deviceTypes.forEach { type ->
                DropdownMenuItem(
                    text = { Text(type) },
                    onClick = {
                        selectedDeviceType = type
                        expanded = false
                        selectedDeviceName = null // Reset selection
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display device names based on selected type
        devicesByType[selectedDeviceType]?.forEach { deviceName ->
            Text(
                text = deviceName,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { selectedDeviceName = deviceName }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Input field for renaming the selected device
        if (selectedDeviceName != null) {
            TextField(
                value = customDeviceName,
                onValueChange = { customDeviceName = it },
                label = { Text("Rename Device") },
                placeholder = { Text("Enter a custom name") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Button to add the selected device
        Button(
            onClick = {
                customDeviceName.let { deviceName ->
                    selectedDeviceType.let { deviceType ->
                        DeviceManager.addDevice(deviceName, deviceType)
                        onDeviceAdded() // Navigate back to the home page
                    }
                }
            },
            enabled = selectedDeviceName != null,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Add Device")
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Back Button at the bottom left
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Button(
                onClick = { navController.navigate("home") },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Back")
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LightsScreen(navController: NavHostController) {
    val knownLights = DeviceManager.knownLights
    var expanded by remember { mutableStateOf(false) }
    var selectedLight by remember { mutableStateOf<String?>(null) }
    var isLightOn by remember { mutableStateOf(false) }
    var selectedColor by remember { mutableStateOf(Color.White) }
    var showScheduleDialog by remember { mutableStateOf(false) }
    var showConfirmation by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Lights", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown for selecting a known light
        Box(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { expanded = true }) {
                Text(text = selectedLight ?: "Select a Light")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                knownLights.forEach { light ->
                    DropdownMenuItem(
                        onClick = {
                            selectedLight = light
                            expanded = false
                        },
                        text = { Text(text = light) }
                    )
                }
            }
        }

        // Show selected light status or a message if no light is selected
        Text(
            text = if (selectedLight != null) {
                "Selected Light: $selectedLight"
            } else {
                "No Light Selected"
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Show actions if a light is selected
        if (selectedLight != null) {
            // Toggle On/Off Button
            Button(
                onClick = { isLightOn = !isLightOn },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(text = if (isLightOn) "Turn Off" else "Turn On")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Change Color Button
            Text(text = "Change Light Color")
            ColorPicker(selectedColor, onColorChange = { selectedColor = it })
            Spacer(modifier = Modifier.height(16.dp))

            // Set Schedule Button
            Button(
                onClick = { showScheduleDialog = true },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(text = "Set Schedule")
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Confirm Options Button
            Button(
                onClick = {
                    showConfirmation = true
                    // Save the device settings here if needed, or show a confirmation message
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Confirm Options")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Back Button at the bottom left
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Button(
                onClick = { navController.navigate("home") },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Back")
            }
        }
    }

    // Schedule Dialog
    if (showScheduleDialog) {
        ScheduleDialog(onDismiss = { showScheduleDialog = false })
    }
    // Confirmation Dialog
    if (showConfirmation) {
        AlertDialog(
            onDismissRequest = { showConfirmation = false },
            title = { Text("Confirmation") },
            text = { Text("Settings for $selectedLight saved.") },
            confirmButton = {
                Button(onClick = { showConfirmation = true
                    // Save the device settings here if needed, or show a confirmation message
                    navController.navigate("home") }) {
                    Text("OK")
                }
            }
        )
    }
}

// Placeholder for ColorPicker composable
@Composable
fun ColorPicker(selectedColor: Color, onColorChange: (Color) -> Unit) {
    // Placeholder UI for color picker
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(selectedColor)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = { onColorChange(Color.Red) }) { Text("Red") }
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = { onColorChange(Color.Blue) }) { Text("Blue") }
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = { onColorChange(Color.Green) }) { Text("Green") }
    }
}

// Placeholder for ScheduleDialog composable
@Composable
fun ScheduleDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Set Schedule") },
        text = { Text("Schedule operations here (e.g., time of day, duration, etc.)") },
        confirmButton = {
            Button(onClick = onDismiss) { Text("Confirm") }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("Cancel") }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlugsScreen(navController: NavHostController) {
    var knownPlugs = DeviceManager.knownPlugs
    var expanded by remember { mutableStateOf(false) }
    var selectedPlug by remember { mutableStateOf<String?>(null) }
    var isPlugOn by remember { mutableStateOf(false) }
    var showScheduleDialog by remember { mutableStateOf(false) }
    var showConfirmation by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Plugs", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown showing known devices
        Box(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { expanded = true }) {
                Text(text = selectedPlug ?: "Select a Smart Plug")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DeviceManager.knownPlugs.forEach { plug ->
                    DropdownMenuItem(
                        onClick = {
                            selectedPlug = plug
                            expanded = false
                        },
                        text = { Text(text = plug) }
                    )
                }
            }
        }

        Text(text = if (selectedPlug != null) {
            "Selected Smart Plug: $selectedPlug"
        } else {
            "No Smart Plug Selected"
        })

        Spacer(modifier = Modifier.height(16.dp))

        // Show actions if a light is selected
        if (selectedPlug != null) {
            // Toggle On/Off Button
            Button(
                onClick = { isPlugOn = !isPlugOn },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(text = if (isPlugOn) "Turn Off" else "Turn On")
            }

            // Set Schedule Button
            Button(
                onClick = { showScheduleDialog = true },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(text = "Set Schedule")
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Confirm Options Button
            Button(
                onClick = {
                    showConfirmation = true
                    // Save the device settings here if needed, or show a confirmation message
                    navController.navigate("home")
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Confirm Options")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Back Button at the bottom left
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Button(
                onClick = { navController.navigate("home") },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Back")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CamerasScreen(navController: NavHostController) {
    var knownCameras = DeviceManager.knownCameras
    var expanded by remember { mutableStateOf(false) }
    var selectedCamera by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Cameras", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown showing known devices
        Box(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { expanded = true }) {
                Text(text = selectedCamera ?: "Select a Camera")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DeviceManager.knownCameras.forEach { camera ->
                    DropdownMenuItem(
                        onClick = {
                            selectedCamera = camera
                            expanded = false
                        },
                        text = { Text(text = camera) }
                    )
                }
            }
        }

        Text(text = if (selectedCamera != null) {
            "Selected Camera: $selectedCamera"
            "Live Camera Feed will appear here !"
        } else {
            "No Camera Selected"
        })
        Spacer(modifier = Modifier.height(16.dp))

        // Back Button at the bottom left
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Button(
                onClick = { navController.navigate("home") },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Back")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SensorsScreen(navController: NavHostController) {
    var knownSensors = DeviceManager.knownSensors
    var expanded by remember { mutableStateOf(false) }
    var selectedSensor by remember { mutableStateOf<String?>(null) }

    // Example data for temperature and air quality history
    val temperatureHistory = listOf(72, 74, 76, 75, 73)
    val airQualityHistory = listOf(42, 39, 47, 40, 43)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Sensors", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown for selecting sensor
        Box(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { expanded = true }) {
                Text(text = selectedSensor ?: "Select a Sensor")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                knownSensors.forEach { sensor ->
                    DropdownMenuItem(
                        onClick = {
                            selectedSensor = sensor
                            expanded = false
                        },
                        text = { Text(text = sensor) }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display sensor-specific data
        if (selectedSensor == "Temperature Sensor") {
            TemperatureDisplay(temperatureHistory)
        } else if (selectedSensor == "Air Quality Sensor") {
            AirQualityDisplay(airQualityHistory)
        } else {
            Text("No Sensor Selected")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Back Button at the bottom left
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Button(
                onClick = { navController.navigate("home") },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Back")
            }
        }
    }
}

@Composable
fun TemperatureDisplay(temperatureHistory: List<Int>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Temperature History", style = MaterialTheme.typography.titleMedium)
        temperatureHistory.forEachIndexed { index, temp ->
            Text(text = "Time ${index + 1}: $temp°F", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun AirQualityDisplay(airQualityHistory: List<Int>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Air Quality History", style = MaterialTheme.typography.titleMedium)
        airQualityHistory.forEachIndexed { index, quality ->
            Text(text = "Time ${index + 1}: $quality AQI", style = MaterialTheme.typography.bodyLarge)
        }
    }
}


@Composable
fun SchedulePage(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Device Schedules", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Display each schedule
        DeviceManager.schedules.forEach { schedule ->
            Text(text = schedule, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Back Button at the bottom left
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Button(
                onClick = { navController.navigate("home") },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text("Back")
            }
        }
    }
}


@Composable
fun MyNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "first") {
        composable("first") { FirstScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("signup") { SignupScreen(navController) }
        composable("home") { HomeScreen(navController = navController) }
        composable("addDevice") { AddDeviceScreen(onDeviceAdded = { navController.popBackStack() }, navController = navController) }
        composable("lights") { LightsScreen(navController = navController) }
        composable("plugs") { PlugsScreen(navController = navController) }
        composable("cameras") { CamerasScreen(navController = navController) }
        composable("sensors") { SensorsScreen(navController = navController) }
        composable("schedule") {SchedulePage(navController = navController)}
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
    val navController = rememberNavController() // Mock NavController for preview
    HomeScreen(navController = navController)
}
