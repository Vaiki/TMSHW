package com.example.tmshw.tasks

import android.annotation.SuppressLint
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.tmshw.R
import com.example.tmshw.databinding.FragmentSensorsBinding


class SensorFragment : Fragment(R.layout.fragment_sensors), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private lateinit var sensor: Sensor
    private var _binding: FragmentSensorsBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSensorsBinding.bind(view)
        sensorManager =
            (context as AppCompatActivity).getSystemService(SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        val deviceSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)
        println(deviceSensors.joinToString { "${it.stringType}\n" })

    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(
            this,
            sensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this, sensor)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("ResourceAsColor", "SetTextI18n")
    override fun onSensorChanged(p0: SensorEvent) {


        binding.tvSensorLight.text = p0.values[0].toInt().toString() + " Лк"

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

}