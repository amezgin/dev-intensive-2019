package ru.skillbranch.devintensive.extensions

fun String.truncate(length: Int = 16): String {
    if (this.length > length) {
        this.trimEnd()
        if (this.substring(length - 1, length) == " ") {
            val truncatedStr = this.trimEnd()
            if (truncatedStr.length < length) {
                return truncatedStr
            }
            return this.substring(0, length - 1) + "..."
        } else {
            return this.substring(0, length) + "..."
        }
    } else {
        return this.trim()
    }
}