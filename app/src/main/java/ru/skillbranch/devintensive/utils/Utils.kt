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

    fun toInitials(firstName: String?, lastName: String?): String? {
        return when {
            firstName?.trim()?.replace("\\s+".toRegex(), " ").isNullOrEmpty()
                    && lastName?.trim()?.replace("\\s+".toRegex(), " ").isNullOrEmpty() -> null
            !firstName?.trim()?.replace("\\s+".toRegex(), " ").isNullOrEmpty()
                    && lastName?.trim()?.replace("\\s+".toRegex(), " ").isNullOrEmpty() -> firstName?.substring(0, 1)?.toUpperCase()
            firstName?.trim()?.replace("\\s+".toRegex(), " ").isNullOrEmpty()
                    && !lastName?.trim()?.replace("\\s+".toRegex(), " ").isNullOrEmpty() -> lastName?.substring(0, 1)?.toUpperCase()
            else -> firstName?.trim()?.replace("\\s+".toRegex(), " ")?.substring(0, 1)?.toUpperCase() + lastName?.trim()?.replace("\\s+".toRegex(), " ")?.substring(0, 1)?.toUpperCase()
        }
    }
}