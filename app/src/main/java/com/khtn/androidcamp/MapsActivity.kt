package com.khtn.androidcamp

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.*
import android.content.Intent
import android.net.Uri


@SuppressLint("SetTextI18n")


class MapsActivity : AppCompatActivity(),
    OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
    GoogleMap.OnInfoWindowClickListener,
    GoogleMap.OnMapClickListener,
    GoogleMap.OnPolylineClickListener,
    GoogleMap.OnInfoWindowLongClickListener {
    lateinit var mMap: GoogleMap
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        // Hàm được gọi khi Bản đổ đã sẵn sàng
        this.mMap = googleMap

        setupMap()
        setupMaker()
    }

    @SuppressLint("MissingPermission")
    private fun setupMap() {

        if (PermissionUtils.isHasLocationPermission(this)) {
            mMap.isMyLocationEnabled = true
        } else {
            PermissionUtils.requestLocationPermission(this)
        }


        mMap.setOnMarkerClickListener(this)
        mMap.setOnInfoWindowClickListener(this)
        mMap.setOnInfoWindowLongClickListener(this)
        mMap.setOnMapClickListener(this)
        mMap.setOnPolylineClickListener(this)
    }

    private fun setupMaker() {
        val khtn = LatLng(10.756586, 106.683936); // tọa độ DH KHTN

        val markerOptions = MarkerOptions()
        markerOptions.position(khtn)
            .title("Truờng Đại Học Khoa Học Tự Nhiên")
            .snippet("227 Đường Nguyễn Văn Cừ, Phường 4, Quận 5")
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
            .alpha(1.0f)
             // Setup thông số Maker

        val marker = mMap.addMarker(markerOptions)

        marker.showInfoWindow() // Hiển thị InfoWindow

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(khtn, 15f)); // Move di tuyển tới vị trí KHTN trên map
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mMap.isMyLocationEnabled = true // set current Location button
                } else {
                }
            }
        }

    }

    override fun onMapClick(p0: LatLng?) {
        Toast.makeText(this, "onMapClick", Toast.LENGTH_LONG).show()

    }

    override fun onMarkerClick(maker: Marker?): Boolean {
        Toast.makeText(this, "onMarkerClick", Toast.LENGTH_LONG).show()

        maker?.setIcon(BitmapDescriptorFactory.fromResource(getRandomMarkerIcon()))



        maker?.showInfoWindow()
        return true
    }

    private fun getRandomMarkerIcon(): Int {
        val iconColors = mutableListOf<Int>().also {
            it.add(R.drawable.marker)
            it.add(R.drawable.marker2)
            it.add(R.drawable.marker3)
            it.add(R.drawable.marker4)
            it.add(R.drawable.marker5)
            it.add(R.drawable.marker6)
            it.add(R.drawable.marker7)
        }
        return iconColors.random()
    }

    override fun onInfoWindowClick(maker: Marker?) {
        Toast.makeText(this, "onInfoWindowClick", Toast.LENGTH_LONG).show()
        addPolyLine()
    }

    override fun onInfoWindowLongClick(maker: Marker?) {
        addPolygon()
    }

    override fun onPolylineClick(polylyne: Polyline?) {
        Toast.makeText(this, "onPolylineClicked", Toast.LENGTH_LONG).show()
        openGoogleMap()
    }

    /**
     * Open google map with Lat-long
     */
    private fun openGoogleMap() {
        val gmmIntentUri = Uri.parse("geo:10.762503, 106.682816")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        if (mapIntent.resolveActivity(packageManager) != null) {
            startActivity(mapIntent)
        }
    }


    /**
     * Add Polyline
     */
    private fun addPolyLine() {
        val latLng1 = LatLng(mMap.cameraPosition.target.latitude, mMap.cameraPosition.target.longitude)
        val latLng2 = LatLng(
            mMap.cameraPosition.target.latitude + 0.00211, mMap.cameraPosition.target.longitude + 0.0022
        )
        val latLng3 = LatLng(
            mMap.cameraPosition.target.latitude + 0.00311, mMap.cameraPosition.target.longitude + 0.0012
        )

        mMap.addPolyline(
            PolylineOptions()
                .clickable(true)
                .add(latLng1, latLng2, latLng3)
        )
    }

    /**
     * Add Polygon
     */
    private fun addPolygon() {
        val latLng1 = LatLng(mMap.cameraPosition.target.latitude, mMap.cameraPosition.target.longitude)
        val latLng2 = LatLng(
            mMap.cameraPosition.target.latitude + 0.00211, mMap.cameraPosition.target.longitude + 0.0022
        )
        val latLng3 = LatLng(
            mMap.cameraPosition.target.latitude + 0.00311, mMap.cameraPosition.target.longitude + 0.0012
        )

        val polygonOptions = PolygonOptions()

        polygonOptions
            .clickable(true)
            .add(latLng1, latLng2, latLng3)

        mMap.addPolygon(polygonOptions)
    }


}
