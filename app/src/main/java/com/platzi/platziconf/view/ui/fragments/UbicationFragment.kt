package com.platzi.platziconf.view.ui.fragments

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.platzi.platziconf.R
import com.platzi.platziconf.model.Ubication

/**
 * A simple [Fragment] subclass.
 * Use the [UbicationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UbicationFragment : Fragment(), OnMapReadyCallback {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ubication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val ubication = Ubication()

        val zoom = 16f
        val centerMap = LatLng(ubication.latitud, ubication.longitud)

        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(centerMap, zoom))

        val centerMark = LatLng(ubication.latitud,ubication.longitud)
        val marketOptions = MarkerOptions()
        marketOptions.position(centerMark)
        marketOptions.title("Platzi Conf 2019")

        val bitmapDraw = context?.applicationContext?.let { ContextCompat.getDrawable(it, R.drawable.logo_platzi) } as BitmapDrawable
        val smallMaker = Bitmap.createScaledBitmap(bitmapDraw.bitmap, 150,150, false)

        marketOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMaker))

        googleMap.addMarker(marketOptions)
    }
}