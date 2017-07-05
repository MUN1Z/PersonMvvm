package net.felipemuniz.personmvvm.presentation.personDetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.felipemuniz.personmvvm.R;
import net.felipemuniz.personmvvm.domain.entities.Person;

import org.parceler.Parcels;

/**
 * Created by Muniz on 05/07/2017.
 */

public class PersonDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);

        Person praise = Parcels.unwrap(getIntent().getParcelableExtra("person"));

        if (savedInstanceState == null) {
            PersonDetailsActivityFragment pf = PersonDetailsActivityFragment.newInstance(praise);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, pf, "person")
                    .commit();
        }

    }

}
