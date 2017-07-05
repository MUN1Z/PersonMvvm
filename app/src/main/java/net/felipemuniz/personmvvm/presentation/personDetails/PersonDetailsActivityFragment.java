package net.felipemuniz.personmvvm.presentation.personDetails;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.felipemuniz.personmvvm.R;
import net.felipemuniz.personmvvm.domain.entities.Person;
import net.felipemuniz.personmvvm.shared.helpers.ConnectionDetector;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Muniz on 05/07/2017.
 */

public class PersonDetailsActivityFragment extends Fragment {

    @BindView(R.id.fragmentPersonDetailsTblName)
    CollapsingToolbarLayout mTblName;

    @BindView(R.id.fragmentPersonDetailsImvPhoto)
    ImageView mImvPhoto;

    @BindView(R.id.fragmentPersonDetailsTvGender)
    TextView mTvGender;

    @BindView(R.id.fragmentPersonDetailsTvName)
    TextView mTvName;

    @BindView(R.id.fragmentPersonDetailsTvPhone)
    TextView mTvPhone;

    @BindView(R.id.fragmentPersonDetailsTvRegion)
    TextView mTvRegion;

    private Person mPerson;

    public PersonDetailsActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_person_details, container, false);
        ButterKnife.bind(this, view);

        if(mImvPhoto.getDrawable() != null)
            return view;

        mPerson = Parcels.unwrap(getArguments().getParcelable("person"));

        mTblName.setTitle(mPerson.getFullName());
        mTvGender.setText(mPerson.getGender());
        mTvName.setText(mPerson.getName());
        mTvPhone.setText(mPerson.getPhone());
        mTvRegion.setText(mPerson.getRegion());

        if(new ConnectionDetector(getContext()).isConnectingToInternet())
            Glide.with(getContext()).load(mPerson.getPhoto()).into(mImvPhoto);
        else
            Toast.makeText(getContext(), getString(R.string.internet_message), Toast.LENGTH_SHORT).show();

        return view;
    }

    public static PersonDetailsActivityFragment newInstance(Person person) {
        Bundle params = new Bundle();
        params.putParcelable("person", Parcels.wrap(person));

        PersonDetailsActivityFragment fragment = new PersonDetailsActivityFragment();
        fragment.setArguments(params);

        return fragment;
    }
}
