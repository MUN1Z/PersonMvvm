package net.felipemuniz.personmvvm.presentation.personList;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import net.felipemuniz.personmvvm.R;
import net.felipemuniz.personmvvm.data.remote.Service;
import net.felipemuniz.personmvvm.data.remote.ServiceGenerator;
import net.felipemuniz.personmvvm.domain.entities.Person;
import net.felipemuniz.personmvvm.presentation.personDetails.PersonDetailsActivity;
import net.felipemuniz.personmvvm.shared.base.BaseLifecycleActivity;
import net.felipemuniz.personmvvm.shared.helpers.ConnectionDetector;
import net.felipemuniz.personmvvm.shared.helpers.RecyclerTouchListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Muniz on 05/07/2017.
 */

public class PersonListActivity extends BaseLifecycleActivity {

    private PersonListViewModel viewModel;
    private PersonListAdapter recyclerViewAdapter;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        recyclerViewAdapter = new PersonListAdapter(new ArrayList<Person>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(recyclerViewAdapter);

        viewModel = ViewModelProviders.of(this).get(PersonListViewModel.class);

        viewModel.getAllPerson().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(@Nullable List<Person> persons) {
                recyclerViewAdapter.addItems(persons);
            }
        });

        recyclerView.addOnItemTouchListener(
                new RecyclerTouchListener(getBaseContext(), recyclerView,
                        new RecyclerTouchListener.OnTouchActionListener() {
                            public void onClick(View view, int position) {
                                //person praise = recyclerViewAdapter..get(position);
                                Person person = (Person) view.getTag();
                                //Toast.makeText(getBaseContext(), person.getName(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getBaseContext(), PersonDetailsActivity.class).putExtra("person", Parcels.wrap(person)));
                            }

                            @Override
                            public void onRightSwipe(View view, int position) {
                                Person person = (Person) view.getTag();
                                viewModel.deletePerson(person);
                            }
                            @Override
                            public void onLeftSwipe(View view, int position) {
                                Person person = (Person) view.getTag();
                                viewModel.deletePerson(person);
                            }

                        }));

    }

    public void getPersons(){

        Service service = ServiceGenerator.createService(Service.class);

        final Call<Person> person = service.GetPerson();

        person.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {

                Person person = response.body();

                viewModel.addPerson(person);
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.fab)
    public void mFabOnClick(View view){
        if(new ConnectionDetector(this).isConnectingToInternet())
            getPersons();
        else
            Toast.makeText(getApplicationContext(), getString(R.string.internet_message), Toast.LENGTH_SHORT).show();
    }

}
