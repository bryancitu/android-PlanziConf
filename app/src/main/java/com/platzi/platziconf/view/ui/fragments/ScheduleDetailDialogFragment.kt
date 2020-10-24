package com.platzi.platziconf.view.ui.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.platzi.platziconf.R
import com.platzi.platziconf.model.Conference
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.*
import java.text.SimpleDateFormat

/**
 * A simple [Fragment] subclass.
 * Use the [ScheduleDetailDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScheduleDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolBarConference.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close)
        toolBarConference.setTitleTextColor(Color.WHITE)
        toolBarConference.setNavigationOnClickListener {
            dismiss()
        }

        val  conference = arguments?.getSerializable("conference") as Conference
        toolBarConference.title = conference.title

        tvItemScheduleTituloConferencia.text = conference.title

        val pattern = "dd/MM/yyy hh:mm a"
        val simpleDF = SimpleDateFormat(pattern)
        val date = simpleDF.format(conference.datetime)
        tvDetailConferenceHour.text = date

        tvDetailConferenceHour.text = date
        tvDetailConferenceSpeaker.text = conference.speaker
        tvDetailConferenceTag.text = conference.tag
        tvDetailConferenceDescription.text = conference.description
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }
}