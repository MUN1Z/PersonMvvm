package net.felipemuniz.personmvvm.presentation.personList

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast

import net.felipemuniz.personmvvm.R
import net.felipemuniz.personmvvm.data.remote.Service
import net.felipemuniz.personmvvm.data.remote.ServiceGenerator
import net.felipemuniz.personmvvm.domain.entities.Person
import net.felipemuniz.personmvvm.presentation.personDetails.PersonDetailsActivity
import net.felipemuniz.personmvvm.shared.base.BaseLifecycleActivity
import net.felipemuniz.personmvvm.shared.helpers.ConnectionDetector
import net.felipemuniz.personmvvm.shared.helpers.RecyclerTouchListener

import org.parceler.Parcels

import java.util.ArrayList

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Muniz on 05/07/2017.
 */

class PersonListActivity : BaseLifecycleActivity() {

    var viewModel: PersonListViewModel? = null

    var recyclerViewAdapter: PersonListAdapter? = null

    @BindView(R.id.recyclerView) internal var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_list)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        ButterKnife.bind(this)

        recyclerViewAdapter = PersonListAdapter(ArrayList<Person>())
        recyclerView?.layoutManager = LinearLayoutManager(this)

        recyclerView?.adapter = recyclerViewAdapter

        viewModel = ViewModelProviders.of(this).get(PersonListViewModel::class.java)

        viewModel!!.allPerson.observe(this, Observer<List<Person>> { persons ->
            if (persons != null) {
                recyclerViewAdapter!!.addItems(persons)
            }
        })

        recyclerView!!.addOnItemTouchListener(
                RecyclerTouchListener(baseContext, recyclerView!!,
                        object : RecyclerTouchListener.OnTouchActionListener {
                            override fun onClick(view: View, position: Int) {
                                //person praise = recyclerViewAdapter..get(position);
                                val person = view.tag as Person
                                //Toast.makeText(getBaseContext(), person.getName(), Toast.LENGTH_SHORT).show();
                                startActivity(Intent(baseContext, PersonDetailsActivity::class.java).putExtra("person", Parcels.wrap(person)))
                            }

                            override fun onRightSwipe(view: View, position: Int) {
                                val person = view.tag as Person
                                viewModel!!.deletePerson(person)
                            }

                            override fun onLeftSwipe(view: View, position: Int) {
                                val person = view.tag as Person
                                viewModel!!.deletePerson(person)
                            }

                        }))

    }

    fun getPersons() {

        val service = ServiceGenerator.createService(Service::class.java)

        val person = service.GetPerson()

        person.enqueue(object : Callback<Person> {
            override fun onResponse(call: Call<Person>, response: Response<Person>) {

                val person = response.body()

                viewModel!!.addPerson(person!!)
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {
                Toast.makeText(applicationContext, "Error: " + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    @OnClick(R.id.fab)
    fun mFabOnClick(view: View) {
        if (ConnectionDetector(this).isConnectingToInternet)
            getPersons()
        else
            Toast.makeText(applicationContext, getString(R.string.internet_message), Toast.LENGTH_SHORT).show()
    }

}
