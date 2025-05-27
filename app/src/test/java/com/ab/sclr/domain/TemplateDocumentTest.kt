package com.ab.sclr.domain

import com.ab.sclr.domain.images.ImageSource
import com.ab.sclr.domain.layers.Layer
import com.ab.sclr.domain.layers.LayerType
import com.ab.sclr.domain.metadata.KnownMetadataKeys
import com.ab.sclr.domain.metadata.author
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [35])
class TemplateDocumentTest {

    private fun Layer.hierarchy(): String {
        val output = StringBuilder()

        output.append("Layer(id=$id, type=$type, zLevel=$zLevel, position=$position, size=$size, rotationDegrees=$rotationDegrees, opacity=$opacity, isVisible=$isVisible, ")

        return output.toString()
    }

    private fun Slide.hierarchy(): String {
        val output = StringBuilder()

        output.append("Slide(id=$id, layers=[")
        output.append(layers.joinToString(", ") { it.hierarchy() })
        output.append("], widthRatio=$widthRatio, background=$background)")

        return output.toString()
    }

    private fun ImageSource.hierarchy(): String {
        val output = StringBuilder()

        output.append("ImageSource(id=$id, url=$originalUri, metadata=$metadata)")

        return output.toString()
    }

    private fun TemplateDocument.hierarchy(): String {
        val output = StringBuilder()

        output.append("TemplateDocument(id=$id, name=$name, slides=[")
        output.append(slides.joinToString(", ") { it.hierarchy() })
        output.append("], images=[")
        output.append(images.joinToString(", ") { it.hierarchy() })
        output.append("], background=$background, ratio=$ratio, metadata=$metadata)")

        return output.toString()
    }

    @Test
    fun `modify Template Document collections`() {
        // GIVEN: original document
        val slideId = UUID.randomUUID().toString()
        val layerId = UUID.randomUUID().toString()
        val original = TemplateDocument(id = "1", name = "Original")

        // WHEN: modify layers

        // during constructing the document
        val updated1 = original.addSlide(
            Slide(
                id = slideId, layers = listOf(
                    Layer(id = layerId, type = LayerType.IMAGE),
                    Layer(id = UUID.randomUUID().toString(), type = LayerType.TEXT),
                    Layer(id = UUID.randomUUID().toString(), type = LayerType.GRID)
                )
            )
        )

        // modify existing document
        val updated2 = original.addSlide(
            Slide(id = slideId)
                .addLayer(Layer(id = layerId, type = LayerType.IMAGE))
                .addLayer(Layer(id = UUID.randomUUID().toString(), type = LayerType.TEXT))
                .addLayer(Layer(id = UUID.randomUUID().toString(), type = LayerType.GRID))
        ).withSlide(slideId) {
            it.addLayer(Layer(id = UUID.randomUUID().toString(), type = LayerType.IMAGE))
        }

        // THEN: print hierarchy of objects to STDOUT
        println(updated1.hierarchy())
        println(updated2.hierarchy())

        // AND: confirm that objects mutated and has different REFs
        assertNotEquals(updated1, updated2)
    }

    @Test
    fun `add metadata`() {
        // GIVEN: original document
        val original = TemplateDocument(id = "1", name = "Original")

        // WHEN: modify metadata
        val updated = original.withMetadata(KnownMetadataKeys.AUTHOR) { "My Copyright" }

        // THEN: print hierarchy of objects to STDOUT
        println(updated.hierarchy())

        // AND: check metadata values
        assertEquals(original.author, null)
        assertEquals(updated.author, "My Copyright")
    }
}