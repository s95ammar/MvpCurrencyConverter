package com.s95ammar.mvpcurrencyconverter

object Constants {
    const val USD = "USD"
    const val HISTORY_DAYS_COUNT = 10
    const val CHART_LINE_WIDTH = 4f
    const val CHART_CIRCLE_RADIUS = 5f
    const val CHART_VALUE_TEXT_SIZE = 10f
    const val CHART_CIRCLE_HOLE_RADIUS = 3f
    const val DATE_PATTERN_DD_MM = "dd.MM"
    const val DATE_PATTERN_FULL = "dd.MM.yyyy"
}

object Errors {
    const val UNKNOWN_ERROR = 0
    const val CONNECTION_ERROR = 1
    const val COUNTRY_UNAVAILABLE_ERROR = 2
    const val INTERNAL_SERVER_ERROR = 3
}
