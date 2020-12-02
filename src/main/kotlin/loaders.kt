import java.io.File

inline fun <reified T> load(file: String): List<T> = File({}::class.java.classLoader.getResource(file).toURI()).readLines().map { it as T }
