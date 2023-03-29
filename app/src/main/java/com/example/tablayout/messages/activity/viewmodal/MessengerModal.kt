package com.example.tablayout.messages.activity.viewmodal


class MessengerModal{

    var message:String?=null
    var senderId:String?=null
    var year:String?=null
    var month:String?=null
    var day:String?=null
    var currentTime:String?=null
    var seen:Boolean?=false
    var receiverUid:String?=null
    var receiverRoom:String?=null
    var senderRoom:String?=null
    var startDTime:String?=null
    constructor(){}
    constructor(message:String?,senderId:String?,year:String?,month:String?,day:String?,currentTime:String?
                ,seen:Boolean?,receiverUid:String?,receiverRoom:String?,senderRoom:String?,
    ){
        this.message=message
        this.senderId=senderId
        this.year=year
        this.month=month
        this.day=day
        this.currentTime=currentTime
        this.seen=seen
        this.receiverUid=receiverUid
        this.receiverRoom=receiverRoom
        this.senderRoom=senderRoom

    }
    constructor(startDTime: String?,day: String?,month: String?,currentTime: String?,year: String?){
        this.startDTime=startDTime
        this.year=year
        this.month=month
        this.day=day
        this.currentTime=currentTime
    }
}
