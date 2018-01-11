package com.example.ricardo.tickit2.data

/**
 * Created by Ricardo on 2017/12/25.
 */



val USER_NOT_EXIT_CODE = "HTTP 111 Unknown Status Code"
val USER_NOT_EXIT = "账户不存在"

val PASSWORK_ERROR_CODE = "HTTP 101 Switching Protocols"
val PASSWORK_ERROR = "密码错误"

val MOBILE_TAKEN_CODE = "HTTP 123 Unknown Status Code"
val MOBILE_TAKEN = "手机号已被注册"

val TICKET_RESTRICTION_REACHED_CODE = "HTTP 152 Unknown Status Code"
val TICKET_REACHED = "已到达限购次数"


val ODNR_NUMBER = 0
val ACTI_NUMBER = 1
val PWXQR_NUMBER = 2


val ODNR = "普通票"
val ACTIVITY = "活动票"
val PWXQR = "票务新奇日"

val SHOW_ARG = "SHOW_KEY"
val BANNER_ARG = "BANNER_KEY"

val BANNER_INTENT = "MAIN_BANNER"
val SHOW_INTENT = "MAIN_SHOW"
val ORDER_INTENT = "MAIN_ORDER"
val ADD_INTEENT = "MAIN_ADD"

//买了票，未取票
val ORDER_OPEN_UNFETCHED = 0
val ORDER_OPEN_UNFETCHED_MESSAGE = "未取票"
//买了票，取了票
val ORDER_SUCCESS = 1
val ORDER_SUCCESS_MESSAGE = "已取票"
//退票
val ORDER_CANCELED_BY_USER = 2
val ORDER_CANCELED_BY_USER_MESSAGE = "已退票"
//过期票
val ORDER_EXPIRED_UNFETCHED = 3
val ORDER_EXPIRED_UNFETCHED_MESSAGE = "已过期"
val DEFAULT_TIME = "000000000000"
