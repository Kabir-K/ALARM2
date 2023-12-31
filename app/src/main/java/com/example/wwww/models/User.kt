package com.example.wwww.models

import android.os.Parcel
import android.os.Parcelable

data class User(
    val id:String="",
    val name:String="",
    val email:String="",
    val image:String="",
    val mobile:Long=0,
    val fcmtoken:String="",
    val connecteduserone:String="",
    val connectedusertwo:String="",
    val connecteduserthree:String="",
    val connecteduserfour:String="",
    val connecteduserfive:String=""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(image)
        parcel.writeLong(mobile)
        parcel.writeString(fcmtoken)
        parcel.writeString(connecteduserone)
        parcel.writeString(connectedusertwo)
        parcel.writeString(connecteduserthree)
        parcel.writeString(connecteduserfour)
        parcel.writeString(connecteduserfive)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}