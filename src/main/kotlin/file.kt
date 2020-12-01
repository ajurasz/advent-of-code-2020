import java.io.File

fun parse(file: String) = File({}::class.java.classLoader.getResource(file).toURI()).readLines().map { it.toInt() }.toHashSet()