# 3. First UI Activity

Date: 2025-05-21

## Status

Accepted

## Context

Application cannot start (or be installed on device) if it does not contain an main Activity.

## Decision

Compose Main/Home activity with Compose UI.

## Consequences

- added lifecycle dependencies: livedata, viewmode, runtime
- added constraint layout
- added compose BOM:
  - https://mvnrepository.com/artifact/androidx.compose/compose-bom/2025.05.00
- following the architecture recommendations: 
  - https://developer.android.com/courses/pathways/android-architecture