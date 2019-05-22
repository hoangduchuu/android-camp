package com.khtn.androidcamp.chat

/**
 * Created by nampham on 4/12/18.
 */
class Message {
    var text: String? = null
    var timestampe: Long = 0

    constructor() {}

    constructor(text: String, timestampe: Long) {
        this.text = text
        this.timestampe = timestampe
    }

    override fun toString(): String {
        return "Message{" +
                "text='" + text + '\''.toString() +
                ", timestampe=" + timestampe +
                '}'.toString()
    }
}
