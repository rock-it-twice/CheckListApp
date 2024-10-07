package com.example.letscheck.viewModels

import android.app.Application
import android.content.Context
import android.os.Build
import android.os.CombinedVibration
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.letscheck.data.classes.main.CheckBoxTitle
import com.example.letscheck.data.classes.output.JointEntity
import com.example.letscheck.repository.ChecklistRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrentEntityViewModel(
    private val vmScope: CoroutineScope,
    private val repository: ChecklistRepository,
    application: Application): ViewModel() {

    // Vibrator
    val vibratorManager = application.getSystemService(Context.VIBRATOR_MANAGER_SERVICE)
            as VibratorManager
    val vibrator = vibratorManager.defaultVibrator

    // Vibrator effects
    val shortVibrationEffect: VibrationEffect =
        VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE)
    val standardVibrationEffect: VibrationEffect =
        VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE)
    val longVibrationEffect: VibrationEffect =
        VibrationEffect.createOneShot(2000, VibrationEffect.DEFAULT_AMPLITUDE)


    // Current entity holder
    var currentJointEntity: JointEntity? by mutableStateOf( null )


    //______________________________________________________________________________________________

    // Entity
    fun getJointEntityById(entityId: Long) {
        vmScope.launch(Dispatchers.IO){
            currentJointEntity = repository.getJointEntityById(entityId)
        }
    }

    fun clearJointEntity() { currentJointEntity = null }

    // CheckBoxes
    fun updateCheckBoxTitle(title: CheckBoxTitle) = vmScope.launch(Dispatchers.IO) {
        repository.updateCheckBoxTitle(title)
    }
    fun isChecked(id: Long): LiveData<Boolean> = repository.isChecked(id)
    fun getCheckedSubList(id: Long): LiveData<List<Boolean>> = repository.getCheckedSubList(id)
    fun getCheckedList(id: Long): LiveData<List<Boolean>> = repository.getCheckedList(id)

    // Очистка переменных
    fun clear() = vmScope.launch { clearJointEntity() }

}