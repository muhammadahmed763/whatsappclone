package com.example.tablayout.signup.activity.modalclass

import com.example.tablayout.status.fragment.activity.Status

class RegisterModal{
    var image:String?=null
    var userName:String?=null
    var email:String?=null
    var uid:String?=null



    constructor(){}

    constructor(image:String?,userName:String?,email:String?,uid:String?){
        this.image=image
        this.userName=userName
        this.email=email
        this.uid=uid
    }


}
