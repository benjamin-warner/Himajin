package io.nihonkaeritai.himajin.Fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.nihonkaeritai.himajin.Helpers.GpsHelper
import io.nihonkaeritai.himajin.Helpers.PermissionHelper
import io.nihonkaeritai.himajin.R

class DashboardFragment : Fragment() {

    private lateinit var gpsHelper: GpsHelper
    private lateinit var locationTextView: TextView

    companion object {
        fun newInstance(): DashboardFragment {
            return DashboardFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_dashboard, container, false)
        locationTextView = rootView.findViewById(R.id.textView) as TextView
        locationTextView.text = "Locating..."
        return rootView
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        onContextAttached(activity)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        onContextAttached(context)
    }

    private fun onContextAttached(context: Context?) {
        if(context is Activity){
            PermissionHelper.RequestGpsPermission(context)
            gpsHelper = GpsHelper(context, 60000, 2000, { location ->
                if(activity != null){
                    activity!!.runOnUiThread( {
                        locationTextView.text = GpsHelper.GetCityName(context, location)
                    })
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        gpsHelper.resumeGpsUpdates()
    }

    override fun onPause() {
        super.onPause()
        gpsHelper.stopGpsUpdates()
    }
}