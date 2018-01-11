package com.example.ricardo.tickit2.data.model

import android.annotation.SuppressLint
import android.os.Parcelable
import com.example.ricardo.tickit2.data.dto.OrderDto
import kotlinx.android.parcel.Parcelize

/**
 * Created by Ricardo on 2017/12/30.
 */
@SuppressLint("ParceCreator")
@Parcelize
class Order(
        val id: String,
        val studentID: String,
        val actualPrice: Int,
        val status: Int,
        val createTime: String,
        val ticketTypeID: String

): Parcelable{
    constructor(dto: OrderDto):this(
            id = dto.pk,
            studentID = dto.fields.studentID,
            actualPrice = dto.fields.actualPrice,
            status = dto.fields.status,
            createTime = dto.fields.createTime,
            ticketTypeID = dto.fields.showID

    )
}