package renderer.json

import kotlinx.serialization.Serializable

@Serializable
class jAssets (
    val modelsDir: String,
    val texturesDir: String,
    val models: Array<jModel>,
    val textures: Array<jTexture>
)