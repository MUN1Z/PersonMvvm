package net.felipemuniz.personmvvm.presentation.personDetails

import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity

import net.felipemuniz.personmvvm.R
import net.felipemuniz.personmvvm.domain.entities.Person

import org.parceler.Parcels

/**
 * Created by Muniz on 05/07/2017.
 */

class PersonDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_details)

        val praise = Parcels.unwrap<Person>(intent.getParcelableExtra<Parcelable>("person"))

        if (savedInstanceState == null) {
            val pf = PersonDetailsActivityFragment.newInstance(praise)

            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, pf, "person")
                    .commit()
        }

    }

}
