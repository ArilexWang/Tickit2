package com.example.ricardo.tickit2.data.model

import android.annotation.SuppressLint
import android.os.Parcelable
import com.example.ricardo.tickit2.data.dto.OrderDto
import com.example.ricardo.tickit2.data.dto.TicketDto
import kotlinx.android.parcel.Parcelize

/**
 * Created by Ricardo on 2018/1/3.
 */
@SuppressLint("ParceCreator")
@Parcelize
class Ticket(
        val id: String,
        val studentID: String,
        val actualPrice: Int,
        val isOpen: Boolean,
        val createTime: String,
        val ticketTypeID: String,
        val showName: String,
        val showAvatar: String,
        val showDescription: String

): Parcelable {
    constructor(dto: TicketDto):this(
            id = dto.pk,
            studentID = dto.fields.studentID,
            actualPrice = dto.fields.actualPrice,
            isOpen = dto.fields.isOpen,
            createTime = dto.fields.createTime,
            ticketTypeID = dto.fields.ticketTypeID,
            showName = dto.fields.showName,
            showAvatar = dto.fields.showAvatar,
            showDescription = dto.fields.showDescription
    )
}