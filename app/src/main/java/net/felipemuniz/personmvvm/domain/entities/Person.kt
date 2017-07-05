/**
 * Copyright 2016 Erik Jhordan Rey.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.felipemuniz.personmvvm.domain.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

import com.google.gson.annotations.SerializedName

import org.parceler.Parcel

/**
 * Created by Muniz on 05/07/2017.
 */


@Entity
@Parcel
class Person {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("surname")
    var surname: String? = null

    @SerializedName("gender")
    var gender: String? = null

    @SerializedName("region")
    var region: String? = null

    @SerializedName("age")
    var age: String? = null

    @SerializedName("phone")
    var phone: String? = null

    @SerializedName("photo")
    var photo: String? = null

    val fullName: String
        get() = name + " " + surname
}
