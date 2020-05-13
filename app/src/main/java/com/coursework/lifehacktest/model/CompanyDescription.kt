package com.coursework.lifehacktest.model

class CompanyDescription{
    var id: Int?=null
    var name: String?=null
    var img: String?=null
    var description: String?=null
    var lat: String?=null
    var lon: String?=null
    var www: String?=null
    var phone: String?=null

    constructor(
        id: Int?,
        name: String?,
        img: String?,
        description: String?,
        lat: String?,
        lon: String?,
        www: String?,
        phone: String?
    ) {
        this.id = id
        this.name = name
        this.img = img
        this.description = description
        this.lat = lat
        this.lon = lon
        this.www = www
        this.phone = phone
    }
}