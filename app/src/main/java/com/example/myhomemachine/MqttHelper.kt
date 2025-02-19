package com.example.myhomemachine

import android.content.Context
import android.util.Log
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*

class MqttHelper(context: Context, private val onMotionDetected: (String) -> Unit) {
    private val serverUri = "tcp://10.5.2.37:1883"
    private val clientId = "AndroidClient"
    private val subscriptionTopic = "home/motion"
    var mqttAndroidClient: MqttAndroidClient = MqttAndroidClient(context, serverUri, clientId)

    init {
        connect()
    }

    private fun connect() {
        val options = MqttConnectOptions()
        options.isAutomaticReconnect = true
        options.isCleanSession = true

        try {
            mqttAndroidClient.connect(options, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Log.d("MQTT", "Connected")
                    subscribeToTopic()
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    Log.e("MQTT", "Failed to connect: ${exception?.message}")
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun subscribeToTopic() {
        try {
            mqttAndroidClient.subscribe(subscriptionTopic, 1)

            mqttAndroidClient.setCallback(object : MqttCallback {
                override fun connectionLost(cause: Throwable?) {
                    Log.e("MQTT", "Connection lost: ${cause?.message}")
                }

                override fun messageArrived(topic: String?, message: MqttMessage?) {
                    val payload = message.toString()
                    Log.d("MQTT", "Message received: $payload")

                    if (payload == "Motion detected!") {
                        onMotionDetected("Motion detected from Raspberry Pi!")
                    }
                }

                override fun deliveryComplete(token: IMqttDeliveryToken?) {}
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}