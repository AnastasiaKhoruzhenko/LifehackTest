package com.coursework.lifehacktest.model

class Company{
    var id: Int?=null
    var name: String?=null
    var img: String?=null

    constructor(id: Int, name: String, img: String) {
        this.id = id
        this.name = name
        this.img = img
    }
}