package com.example.tablayout.chat.fragment.modalclass

import java.time.Year

class UserChatModal {

    var name:String?=null
    var email:String?=null
    var image:String?=null
    var uid:String?=null
    var time:String?=null
    var lastmessage:String?=null
    var checkmessage:String?=null
    var UserOnlineType:String?=null

    constructor() {}

    constructor(name: String?,email:String?,image:String?,uid:String?,time:String?
    ,lastmessage:String?,checkmessage:String?,UserOnlineType:String) {
        this.name=name
        this.email=email
        this.image=image
        this.uid=uid
        this.time=time
        this.lastmessage=lastmessage
        this.checkmessage=checkmessage
        this.UserOnlineType=UserOnlineType

    }



}