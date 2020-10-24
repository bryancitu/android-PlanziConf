package com.platzi.platziconf.view.adapter

import com.platzi.platziconf.model.Speaker

interface SpeakerListener {
    fun onSpeakerClicked(speaker: Speaker, position: Int)
}