package com.ab.sclr.data

import android.content.Context
import com.ab.sclr.domain.TemplateDocument
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException
import java.util.UUID

class SavedProjectsRepositoryImpl(
    private val context: Context,
    private val moshi: Moshi
) : SavedProjectsRepository {

    companion object {
        const val ProjectsDirName = "saved_projects"
        const val ProjectFileName = "project.json"
    }

    private fun getAppProjectsDirectory(): File {
        val dir = File(context.filesDir, ProjectsDirName)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        return dir
    }

    private fun getProjectDirectory(projectId: String): File {
        return File(getAppProjectsDirectory(), projectId)
    }

    private fun getProjectFile(projectDir: File): File {
        return File(projectDir, ProjectFileName)
    }

    override suspend fun newProject(id: String): TemplateDocument {
        // TODO (olku): initialize with default values and nested objects

        return TemplateDocument(
            id = UUID.randomUUID().toString(),
            name = ""
        )
    }

    override suspend fun newProjectFromTemplate(template: TemplateDocument): TemplateDocument {
        return template.copy(id = UUID.randomUUID().toString())
    }

    override suspend fun saveProject(project: TemplateDocument, overwrite: Boolean) {
        withContext(Dispatchers.IO) {
            val projectDir = getProjectDirectory(project.id)
            if (projectDir.exists() && !overwrite) {
                throw IOException("Project with ID ${project.id} already exists. Set overwrite to true to replace it.")
            }
            if (!projectDir.exists()) {
                projectDir.mkdirs()
            }

            val projectFile = getProjectFile(projectDir)
            val jsonAdapter = moshi.adapter(TemplateDocument::class.java).indent("  ")
            val json = jsonAdapter.toJson(project)
            projectFile.writeText(json)
        }
    }

    override suspend fun saveProjectAs(
        project: TemplateDocument,
        dirPath: String,
        overwrite: Boolean
    ) {
        withContext(Dispatchers.IO) {
            TODO("Not Implemented yet!")
        }
    }

    override suspend fun deleteProject(project: TemplateDocument) {
        withContext(Dispatchers.IO) {
            val projectDir = getProjectDirectory(project.id)
            if (projectDir.exists()) {
                projectDir.deleteRecursively() // Deletes the folder and its contents
            }
            // Consider also deleting from custom locations if your app supports it.
        }
    }

    override suspend fun loadAllProjects(dirPath: String): Flow<TemplateDocument> = flow {
        withContext(Dispatchers.IO) {
            val rootDir = if (dirPath.isEmpty()) {
                getAppProjectsDirectory()
            } else {
                // WARNING: Reading from arbitrary dirPath requires careful permission handling.
                // In a real app, use Storage Access Framework (SAF).
                File(dirPath)
            }

            if (!rootDir.exists() || !rootDir.isDirectory) {
                // Emit nothing or an error state if using Result wrapper
                return@withContext
            }

            rootDir.listFiles()?.forEach { projectDir ->
                if (projectDir.isDirectory) {
                    val projectFile = getProjectFile(projectDir)
                    if (projectFile.exists()) {
                        try {
                            val json = projectFile.readText()
                            val jsonAdapter = moshi.adapter(TemplateDocument::class.java)
                            jsonAdapter.fromJson(json)?.let { document ->
                                emit(document) // Emit each successfully loaded project
                            }
                        } catch (e: Exception) {
                            // Log error, skip this project, or emit an error state
                            println("Error loading project from ${projectDir.name}: ${e.message}")
                        }
                    }
                }
            }
        }
    }.flowOn(Dispatchers.IO) // Ensure file operations are off the main thread

    override suspend fun openProject(projectPath: String): TemplateDocument? {
        return withContext(Dispatchers.IO) {
            // projectPath here is expected to be the path to the specific project's folder
            val projectDir = File(projectPath)
            if (!projectDir.exists() || !projectDir.isDirectory) {
                return@withContext null
            }

            val projectFile = getProjectFile(projectDir)
            if (projectFile.exists()) {
                try {
                    val json = projectFile.readText()
                    val jsonAdapter = moshi.adapter(TemplateDocument::class.java)
                    jsonAdapter.fromJson(json)
                } catch (e: Exception) {
                    println("Error opening project at $projectPath: ${e.message}")
                    null
                }
            } else {
                null
            }
        }
    }
}