package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?) : Pair<String?, String?> {
        val parts: List<String>? = fullName?.trim()?.replace("\\s+".toRegex(), " ")?.split(" ")
        return when(parts?.size) {
            null, 0 -> null to null
            1 -> (if (parts.get(0).isEmpty()) null else parts.get(0)) to null
            2 -> parts.get(0) to parts.get(1)
            else -> (if (parts.get(0).isEmpty()) null else parts.get(0)) to (if (parts.get(1).isEmpty()) null else parts.get(1))
        }
    }
}