
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