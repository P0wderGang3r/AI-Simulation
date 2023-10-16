package renderer.sceneInitializers

import org.lwjgl.opengl.GL20
import renderer.SceneGlobals
import renderer.SceneMemory

object bindTextures {
    fun bind() {
        GL20.glActiveTexture(GL20.GL_TEXTURE0)
        SceneGlobals.oglTextures = IntArray(SceneMemory.textures!!.size)
        for (index in SceneMemory.textures!!.indices) {
            //GL20.glGenTextures(1, SceneGlobals.oglTextures, index);
            GL20.glBindTexture(GL20.GL_TEXTURE_2D, SceneGlobals.oglTextures[index.toInt()])
            GL20.glTexParameteri(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_WRAP_S, GL20.GL_CLAMP_TO_EDGE)
            GL20.glTexParameteri(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_WRAP_T, GL20.GL_CLAMP_TO_EDGE)

            //GL_LINEAR
            GL20.glTexParameteri(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_MIN_FILTER, GL20.GL_NEAREST)
            GL20.glTexParameteri(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_MAG_FILTER, GL20.GL_NEAREST)

            //GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, SceneMemory.getTextures().get(index).getBitmap(), 0);
            SceneMemory.textures!![index.toInt()].numInMemory = index.toInt()
            GL20.glBindTexture(GL20.GL_TEXTURE_2D, 0)
        }
    }
}
