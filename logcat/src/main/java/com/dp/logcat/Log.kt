package com.dp.logcat

private var logCounter = 0

data class Log(val id: Int,
               val date: String,
               val time: String,
               val pid: String,
               val tid: String,
               val priority: String,
               val tag: String,
               val msg: String)

internal object LogFactory {
    fun createNewLog(metadata: String, msg: String): Log {
        val date: String
        val time: String
        val pid: String
        val tid: String
        val priority: String
        val tag: String

        val trimmed = metadata.substring(1, metadata.length - 1).trim()
        var startIndex = 0

        val skipSpaces = {
            while (trimmed[startIndex] == ' ') {
                startIndex++
            }
        }

        var index = trimmed.indexOf(' ', startIndex)
        date = trimmed.substring(startIndex, index)
        startIndex = index + 1

        index = trimmed.indexOf(' ', startIndex)
        time = trimmed.substring(startIndex, index)
        startIndex = index + 1

        skipSpaces()

        index = trimmed.indexOf(':', startIndex)
        pid = trimmed.substring(startIndex, index)
        startIndex = index + 1

        skipSpaces()

        index = trimmed.indexOf(' ', startIndex)
        tid = trimmed.substring(startIndex, index)
        startIndex = index + 1

        index = trimmed.indexOf('/', startIndex)
        priority = trimmed.substring(startIndex, index)
        startIndex = index + 1

        tag = trimmed.substring(startIndex, trimmed.length).trim()

        return Log(logCounter++, date, time, pid, tid, priority, tag, msg)
    }
}