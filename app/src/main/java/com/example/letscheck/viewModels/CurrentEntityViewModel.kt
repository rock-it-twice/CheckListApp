package com.example.letscheck.viewModels

import android.app.Application
import android.content.Context
import android.media.MediaActionSound
import android.media.MediaPlayer
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
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.letscheck.data.classes.main.CheckBoxTitle
import com.example.letscheck.data.classes.output.JointEntity
import com.example.letscheck.repository.ChecklistRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CurrentEntityViewModel(
    private val vmScope: CoroutineScope,
    private val repository: ChecklistRepository,
    private val application: Application): ViewModel() {


    // Vibrator
    val vibratorManager = application.getSystemService(Context.VIBRATOR_MANAGER_SERVICE)
            as VibratorManager
    val vibrator = vibratorManager.defaultVibrator

    // Vibrator effects
    val shortVibrationEffect: VibrationEffect =
        VibrationEffect.createOneShot(400, VibrationEffect.DEFAULT_AMPLITUDE)
    val standardVibrationEffect: VibrationEffect =
        VibrationEffect.createOneShot(800, VibrationEffect.DEFAULT_AMPLITUDE)
    val longVibrationEffect: VibrationEffect =
        VibrationEffect.createOneShot(1200, VibrationEffect.DEFAULT_AMPLITUDE)


    // Current entity
    var currentJointEntity: JointEntity? by mutableStateOf( null )
        private set
    var entityId = 0L
        private set
    var entityName = ""
        private set
    var progressObserver = MutableLiveData<List<Boolean>>() as LiveData<List<Boolean>>
        private set

    //______________________________________________________________________________________________

    // Entity
    fun getEntityId(id:Long){ entityId = id }
    fun getJointEntityById() {
        vmScope.launch(Dispatchers.IO){
            currentJointEntity = repository.getJointEntityById(entityId)
            try {
                entityName = currentJointEntity!!.entity.entityName
            } catch (e: NullPointerException) {
                Log.e("NullPointerException:",
                    "Assigning of entityId and entityName from currentJointEntity failed."
                )
            }
        }
    }

    fun clearJointEntity() { currentJointEntity = null ; entityId = 0L ; entityName = "" }

    // CheckBoxes
    fun updateCheckBoxTitle(title: CheckBoxTitle) = vmScope.launch(Dispatchers.IO) {
        repository.updateCheckBoxTitle(title)
    }
    fun isChecked(id: Long): LiveData<Boolean> = repository.isChecked(id)
    fun getCheckedSubList(id: Long): LiveData<List<Boolean>> = repository.getCheckedSubList(id)
    fun getCheckedList() { progressObserver = repository.getCheckedList(entityId) }


    // Vibration call
    fun vibrate(vibrate: Boolean, effect: String){
        vmScope.launch {
            if (vibrate){
                when (effect.lowercase()) {
                    "long"     -> { vibrator.vibrate(longVibrationEffect) }
                    "standard" -> { vibrator.vibrate(standardVibrationEffect) }
                    "short"    -> { vibrator.vibrate(shortVibrationEffect) }
                    else       -> { Log.e("VIBRATE ERR",
                    "CurrentEntityViewModel.vibrate() doesn't have the vibration effect: $effect."
                    )}
                }
            }
        }
    }

    //Player
    fun playSound(sound: Int): MediaPlayer { return MediaPlayer.create(application, sound) }

    // Очистка переменных
    fun clear() = vmScope.launch { clearJointEntity() }

}