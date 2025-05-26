# 8. Saved Project Repository

Date: 2025-05-26

## Status

Accepted

## Context

For saving project started by user we should use Repository abstraction. 
It will be responsible for saving/loading project from local disk (application folder).
User also may specify a custom location for project saving.

## Decision

Functionality:
- create new project with unique ID;
- create new project from provided template;
- save project to application folder;
- save project to custom location;
- delete project from application folder;
- load all projects information from specified folder, empty path - application folder;
- open project from custom location;
- save project cover image;
- save project thumbnail image;
- support Flow stream for project loading;

Define interface that Repository will provide.

```kotlin
interface SavedProjectsRepository {
    suspend fun newProject(id: String): TemplateDocument
    
    suspend fun newProjectFromTemplate(template: TemplateDocument): TemplateDocument?
    
    suspend fun saveProject(project: TemplateDocument, overwrite: Boolean = false)

    suspend fun saveProjectAs(project: TemplateDocument, path: String, overwrite: Boolean = false)

    suspend fun deleteProject(project: TemplateDocument)

    suspend fun loadAllProjects(dirPath: String = ""): Flow<TemplateDocument>

    suspend fun openProject(path: String): TemplateDocument?
    
    /* more methods for updating project metadata, images and etc */
}
```

Use Dependency injection to provide implementation.

## Consequences

- we may need to  request access permissions to Downloads folder
- project saved into sub-folder
- project (de-)serialized from/to JSON with Moshi help
- repository may support atomic modifications, like add/remove image to project folder (local cache)
- Optional. For sharing we may need to pack project folder to ZIP archive, to reduce it size.
- Optional. We may need re-pack project to reduce it size (change images format for WEBP)