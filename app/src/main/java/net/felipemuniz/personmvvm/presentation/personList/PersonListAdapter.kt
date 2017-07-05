package net.felipemuniz.personmvvm.presentation.personList

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide

import net.felipemuniz.personmvvm.R
import net.felipemuniz.personmvvm.domain.entities.Person

import butterknife.BindView
import butterknife.ButterKnife

/**
 * Created by Muniz on 05/07/2017.
 */

class PersonListAdapter(private var personList: List<Person>?) : RecyclerView.Adapter<PersonListAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val person = personList!![position]

        holder.mTvPersonName!!.text = person.name
        holder.mTvPersonSurname!!.text = person.surname
        holder.mTvPersonGender!!.text = person.gender
        holder.mTvRegion!!.text = person.region

        Glide.with(holder.mImvPersonPhoto!!.context).load(person.photo).into(holder.mImvPersonPhoto!!)

        holder.itemView.tag = person

    }

    override fun getItemCount(): Int {
        return personList!!.size
    }

    fun addItems(personList: List<Person>) {
        this.personList = personList
        notifyDataSetChanged()
    }

    class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        @BindView(R.id.tvPersonName) var mTvPersonName: TextView? = null
        @BindView(R.id.tvPersonSurname) var mTvPersonSurname: TextView? = null
        @BindView(R.id.imvPersonPhoto) var mImvPersonPhoto: ImageView? = null
        @BindView(R.id.tvPersonGender) var mTvPersonGender: TextView? = null
        @BindView(R.id.tvPersonRegion) var mTvRegion: TextView? = null

        init {
            ButterKnife.bind(this, view)
        }

    }
}