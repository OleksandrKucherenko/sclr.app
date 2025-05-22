# 5. Overlays Endpoint

Date: 2025-05-22

## Status

Accepted

## Context

You can fetch our test overlays from the [`https://appostropheanalytics.herokuapp.com/scrl/test/overlays`](https://appostropheanalytics.herokuapp.com/scrl/test/overlays) endpoint.

Endpoint:
- https://appostropheanalytics.herokuapp.com/scrl/test/overlays

Schema sample:

```json
[
  {
    "title": "Stickers",
    "id": 36,
    "items": [
      {
        "id": 268,
        "overlay_name": "s1",
        "created_at": "2024-02-11T16:46:18.712Z",
        "category_id": 36,
        "source_url": "https://scrl-addtext.b-cdn.net/1707669886150-s1.png",
        "is_premium": false,
        "includes_scrl_branding": false,
        "premium_uses_last_48hrs": 61,
        "max_canvas_size": 600
      }
    ]
  }
]
```

## Decision

Compose Classes required for parsing JSON, quick typescript schema:

```typescript
export type Root = OverlayCategory[]

export interface OverlayCategory {
  title: string
  id: number
  items: Overlay[]
  thumbnail_url: string
}

export interface Overlay {
  id: number
  overlay_name: string
  created_at: string
  category_id: number
  source_url: string
  is_premium: boolean
  includes_scrl_branding: boolean
  premium_uses_last_48hrs: number
  max_canvas_size: number
}
```

## Consequences

- Configure Moshi, Retrofit to access the endpoint data
- Convert field names from `max_canvas_size` (snake_case) to `maxCanvasSize` (camel-case)

## References

- https://transform.tools/json-to-typescript