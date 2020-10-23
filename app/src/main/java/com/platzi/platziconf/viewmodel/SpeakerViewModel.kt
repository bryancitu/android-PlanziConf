package com.platzi.platziconf.viewmodel

import androidx.lifecycle.MutableLiveData
import com.platzi.platziconf.model.Conference
import com.platzi.platziconf.model.Speaker
import com.platzi.platziconf.network.Callback
import com.platzi.platziconf.network.FirestoreService
import java.lang.Exception

class SpeakerViewModel {
    val firestoreService = FirestoreService()
    var listSpeaker: MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading =  MutableLiveData<Boolean>()

    fun refresh() {
        getScheduleFromFirebase()
    }

    fun getScheduleFromFirebase() {
        firestoreService.getSpeakers(object: Callback<List<Speaker>> {
            override fun onSuccess(result: List<Speaker>?) {
                listSpeaker.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }

    fun processFinished(){
        isLoading.value = true
    }
}