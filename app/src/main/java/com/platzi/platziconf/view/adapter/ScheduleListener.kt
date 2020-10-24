package com.platzi.platziconf.view.adapter

import com.platzi.platziconf.model.Conference

interface ScheduleListener {
    fun onConferenceClicked(conference: Conference, position: Int)
}