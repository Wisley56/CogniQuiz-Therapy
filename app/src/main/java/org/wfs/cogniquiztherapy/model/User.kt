package org.wfs.cogniquiztherapy.model

class User(var image: String, var name: String) {
    fun getImage() : String {return image}
    fun setUrl(picture: String) {this.image = picture}
    fun getName() : String {return name}
    fun setName(name: String) {this.name = name}
}