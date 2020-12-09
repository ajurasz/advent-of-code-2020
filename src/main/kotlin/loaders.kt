import java.io.File

inline fun <reified T> load(file: String): List<T> =
    File(Unit::class.java.classLoader.getResource(file).toURI()).readLines()
        .map {
            when (T::class) {
                Long::class -> it.toLong()
                else -> it
            } as T
        }
