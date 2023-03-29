package com.example.tablayout

class TimeModal {

    var year:String?=null
    var month:String?=null
    var day:String?=null
    var hour:String?=null
    var minute:String?=null

    constructor(){}

    constructor(year:String?,month:String?,day:String?,hour:String?,minute:String?){
        this.year=year
        this.month=month
        this.day=day
        this.hour=hour
        this.minute=minute
    }
}