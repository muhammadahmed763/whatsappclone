package com.example.tablayout.status.fragment.modalclass

class UploadStatusModal {

    var status:String?=null
    var uid:String?=null
    var day:Int?=null

    constructor(){}

    constructor(status:String?,uid:String?,day:Int){
        this.status=status
        this.uid=uid
        this.day=day
    }


}