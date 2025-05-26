package com.ab.sclr.data

import com.ab.sclr.domain.TemplateDocument
import kotlinx.coroutines.flow.Flow
import java.io.File

interface SavedProjectsRepository {
    /**
     * Creates a new blank project with the given ID.
     * The ID will be used as the directory name for the project.
     */
    suspend fun newProject(id: String): TemplateDocument
    /**
     * Creates a new project based on a provided template document.
     * A new unique ID will be generated for this project.
     */
    suspend fun newProjectFromTemplate(template: TemplateDocument): TemplateDocument
    /**
     * Saves the project to the application's dedicated project folder.
     * The project's `id` field is used as the sub-folder name.
     * @param project The project document to save.
     * @param overwrite If true, existing project with the same ID will be overwritten.
     * @throws IOException if saving fails.
     */
    suspend fun saveProject(project: TemplateDocument, overwrite: Boolean = false)
    /**
     * Saves the project to a custom file system path.
     * The project will be saved in a sub-folder named after its `id` within the given `dirPath`.
     * Note: This requires appropriate file system permissions.
     * @param project The project document to save.
     * @param dirPath The absolute path to the directory where the project folder should be created.
     * @param overwrite If true, existing project at the target location will be overwritten.
     * @throws IOException if saving fails or permissions are insufficient.
     */
    suspend fun saveProjectAs(project: TemplateDocument, dirPath: String, overwrite: Boolean = false)
    /**
     * Deletes the project from the application's dedicated project folder.
     * @param project The project to delete.
     */
    suspend fun deleteProject(project: TemplateDocument)

    /**
     * Loads all projects from the specified directory.
     * If dirPath is empty, it loads from the application's dedicated project folder.
     * Emits TemplateDocument objects as they are found and deserialized.
     * Note: For custom dirPath, requires appropriate file system permissions.
     */
    suspend fun loadAllProjects(dirPath: String = ""): Flow<TemplateDocument>
    /**
     * Opens a project from a specific project folder path.
     * @param projectPath The absolute path to the project's folder.
     * @return The loaded TemplateDocument or null if not found or failed to load.
     */
    suspend fun openProject(projectPath: String): TemplateDocument?
}