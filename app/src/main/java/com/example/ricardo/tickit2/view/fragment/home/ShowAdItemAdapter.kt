package com.example.ricardo.tickit2.view.fragment.home

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.data.model.Show
import com.example.ricardo.tickit2.data.model.Ticket
import com.example.ricardo.tickit2.extensions.bindView
import com.example.ricardo.tickit2.view.common.ItemAdapter
import com.example.ricardo.tickit2.view.myTicket.TicketItemAdapter
import java.text.SimpleDateFormat
import java.util.*


class ShowAdItemAdapter(val show: Show, val clicked: (Show) -> Unit){}