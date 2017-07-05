package net.felipemuniz.personmvvm.presentation.personDetails

import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.app.Fragment
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.bumptech.glide.Glide

import net.felipemuniz.personmvvm.R
import net.felipemuniz.personmvvm.domain.entities.Person
import net.felipemuniz.personmvvm.shared.helpers.ConnectionDetector

import org.parceler.Parcels

import butterknife.BindView
import butterknife.ButterKnife

/**
 * Created by Muniz on 05/07/2017.
 */

class PersonDetailsActivityFragment : Fragment() {

    @BindView(R.id.fragmentPersonDetailsTblName)
    internal var mTblName: CollapsingToolbarLayout? = null

    @BindView(R.id.fragmentPersonDetailsImvPhoto)
    internal var mImvPhoto: ImageView? = null

    @BindView(R.id.fragmentPersonDetailsTvGender)
    internal var mTvGender: TextView? = null

    @BindView(R.id.fragmentPersonDetailsTvName)
    internal var mTvName: TextView? = null

    @BindView(R.id.fragmentPersonDetailsTvPhone)
    internal var mTvPhone: TextView? = null

    @BindView(R.id.fragmentPersonDetailsTvRegion)
    internal var mTvRegion: TextView? = null

    private var mPerson: Person? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = LayoutInflater.from(container!!.context).inflate(R.layout.fragment_person_details, container, false)
        ButterKnife.bind(this, view)

        if (mImvPhoto?.drawable != null)
            return view

        mPerson = Parcels.unwrap<Person>(arguments.getParcelable<Parcelable>("person"))

        mTblName?.title = mPerson?.fullName
        mTvGender?.text = mPerson?.gender
        mTvName?.text = mPerson?.name
        mTvPhone?.text = mPerson?.phone
        mTvRegion?.text = mPerson?.region

        if (ConnectionDetector(context).isConnectingToInternet)
            Glide.with(context).load(mPerson?.photo).into(mImvPhoto!!)
        else
            Toast.makeText(context, getString(R.string.internet_message), Toast.LENGTH_SHORT).show()

        return view
    }

    companion object {

        fun newInstance(person: Person): PersonDetailsActivityFragment {
            val params = Bundle()
            params.putParcelable("person", Parcels.wrap(person))

            val fragment = PersonDetailsActivityFragment()
            fragment.arguments = params

            return fragment
        }
    }
}
