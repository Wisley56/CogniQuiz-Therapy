package org.wfs.cogniquiztherapy.model

import android.os.Parcel
import android.os.Parcelable


class User(var image: String? = null, var name: String = "", var answers: List<String> = emptyList(), var questions: List<String> = emptyList()) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString().toString(),
        parcel.createStringArrayList() ?: emptyList()
    )

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }

        fun createUser(name: String, image: String, answers: List<String>, questions: List<String>): User {
            return User(image, name, answers, questions)
        }

        fun createEmptyUser(): User {
            return User()
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image)
        parcel.writeString(name)
        parcel.writeStringList(answers)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Name: $name"
    }
}