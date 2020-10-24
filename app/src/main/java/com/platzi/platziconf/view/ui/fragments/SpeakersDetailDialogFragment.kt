package com.platzi.platziconf.view.ui.fragments

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.platzi.platziconf.R
import com.platzi.platziconf.model.Conference
import com.platzi.platziconf.model.Speaker
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.*
import kotlinx.android.synthetic.main.fragment_speakers_detail_dialog.*
import java.text.SimpleDateFormat

/**
 * A simple [Fragment] subclass.
 * Use the [SpeakersDetailDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SpeakersDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_speakers_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolBarSpeaker.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close)
        toolBarSpeaker.setNavigationOnClickListener {
            dismiss()
        }

        val  speaker = arguments?.getSerializable("speaker") as Speaker
        toolBarSpeaker.title = speaker.name
        toolBarSpeaker.setTitleTextColor(Color.WHITE)
        tvDetailSpeakerUsername.text = speaker.name
        tvDetailJobTitle.text = speaker.jobtitle
        tvDetailTrabajo.text = speaker.workplace

        Glide.with(ivDetailSpeakerImage.context)
            .load(speaker.image)
            .apply(RequestOptions.circleCropTransform())
            .into(ivDetailSpeakerImage)

        ivDetailSpeakerTwitter.setOnClickListener {
            var intent: Intent

            try {
                context?.packageManager?.getPackageInfo("com.twitter.android", 0)
                intent = Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=${speaker.twitter}"))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            } catch (e: Exception) {
                intent = Intent(
                    Intent.ACTION_VIEW, Uri.parse("https://twitter.com/${speaker.twitter}")
                )
            }
            startActivity(intent)
        }

        tvDetailSpeakerBiography.text = speaker.biography
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

}