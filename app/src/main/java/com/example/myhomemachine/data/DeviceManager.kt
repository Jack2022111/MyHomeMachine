package com.example.myhomemachine.data

import androidx.compose.runtime.mutableStateListOf

object DeviceManager {
    private val knownDevices = mutableMapOf<String, MutableList<String>>(
        "camera" to mutableStateListOf(),
        "light" to mutableStateListOf(),
        "plug" to mutableStateListOf(),
        "sensor" to mutableStateListOf(),
        "other1" to mutableStateListOf(),
        "other2" to mutableStateListOf()
    )

    // Store schedules
    private val _schedules = mutableStateListOf<String>()  // Or use a custom data type for more complex scheduling
    val schedules: List<String> get() = _schedules

    fun addSchedule(schedule: String) {
        _schedules.add(schedule)
    }

    // Public accessors for each device type
    val knownCameras: List<String> get() = knownDevices["camera"] ?: emptyList()
    val knownLights: List<String> get() = knownDevices["light"] ?: emptyList()
    val knownPlugs: List<String> get() = knownDevices["plug"] ?: emptyList()
    val knownSensors: List<String> get() = knownDevices["sensor"] ?: emptyList()
    val knownOther1: List<String> get() = knownDevices["other1"] ?: emptyList()
    val knownOther2: List<String> get() = knownDevices["other2"] ?: emptyList()

    // Function to add a device with a specific type
    fun addDevice(deviceName: String, type: String) {
        knownDevices[type]?.let { deviceList ->
            if (deviceName !in deviceList) {
                deviceList.add(deviceName)
            }
        }
    }
}
