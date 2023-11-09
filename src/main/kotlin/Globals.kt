
import kotlinx.serialization.json.Json

val jsonParser = Json {
    ignoreUnknownKeys = true
}

/**
 * Input JSON
 */
val logs = booleanArrayOf(
    false
)

var data_path: String = "./data"