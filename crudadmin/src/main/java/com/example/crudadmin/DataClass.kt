package com.example.crudadmin

class DataClass {
    var dataID: String? = null
    var dataTime: String? = null
    var dataImage: String? = null
    constructor(dataID: String?, dataTime: String?, dataImage: String?){
        this.dataID = dataID
        this.dataTime = dataTime
        this.dataImage = dataImage
    }
    constructor()
    {}
}