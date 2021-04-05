package testCommon.utils

import testCommon.di.moshi
import java.io.File

val JsonMockFile.json: String
    get() {
        val uri = this.javaClass.classLoader?.getResource("$path$fileName") ?: return ""
        val file = File(uri.path)
        return String(file.readBytes())
    }

inline fun <reified T> JsonMockFile.fromJson(): T =
    moshi.adapter(T::class.java).fromJson(this.json)!!