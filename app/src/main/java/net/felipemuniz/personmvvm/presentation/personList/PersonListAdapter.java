package net.felipemuniz.personmvvm.presentation.personList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.felipemuniz.personmvvm.R;
import net.felipemuniz.personmvvm.domain.entities.Person;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Muniz on 05/07/2017.
 */

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.RecyclerViewHolder> {

    private List<Person> personList;

    public PersonListAdapter(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        Person person = personList.get(position);

        holder.mTvPersonName.setText(person.getName());
        holder.mTvPersonSurname.setText(person.getSurname());
        holder.mTvPersonGender.setText(person.getGender());
        holder.mTvRegion.setText(person.getRegion());

        Glide.with(holder.mImvPersonPhoto.getContext()).load(person.getPhoto()).into(holder.mImvPersonPhoto);

        holder.itemView.setTag(person);

    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public void addItems(List<Person> personList) {
        this.personList = personList;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvPersonName) TextView mTvPersonName;
        @BindView(R.id.tvPersonSurname) TextView mTvPersonSurname;
        @BindView(R.id.imvPersonPhoto) ImageView mImvPersonPhoto;
        @BindView(R.id.tvPersonGender) TextView mTvPersonGender;
        @BindView(R.id.tvPersonRegion) TextView mTvRegion;

        RecyclerViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}