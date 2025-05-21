# 1. Functionality

Date: 2025-05-21

## Status

Accepted

## Context

Implement SCRL application with limited functionality

## Decision

1. Creating a scrollable canvas view.
2. Having a “+”-button that presents a sheet with overlays from the endpoint.
3. Adding a selected overlay to the canvas.
4. Selecting items by tapping on them. Deselecting by tapping outside the item when it’s selected.
5. Moving items around in the canvas by panning while it’s selected.
6. While moving items around in the canvas, we want the items to snap (round off its position to some appropriate but arbitrary precision) when the item is near the top, left, right or bottom part of the canvas or other items on the canvas. 
	It should also show yellow lines highlighting when snapping occurs. (We have attached an example video from SCRL to show how it’s working in our editor, please try it yourself in the app to see more precisely).

## Scope

The scope of the assignment is to create a minimal but functional implementation of these features. 
The focus should be on implementing the basic functionality in a robust but simplified way, and not on design or polish. 
Think of it as a prototype or proof-of-concept of a new feature. We suggest spending between 4 and 8 hours on programming the assignment.

## Consequences

Implement Android Native application in Kotlin, with Gradle KTS build system.
