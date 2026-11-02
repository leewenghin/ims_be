# Products

Base path: `/api/products`. JSON uses camelCase.

## GET /api/products

### Request

| Name | In | Required | Description |
| --- | --- | --- | --- |
| `page` | query | yes | Page number |
| `size` | query | no | Page size. Null returns every row |
| `sort_by` | query | no | Sort field. Default `occurred_at` |
| `sort_direction` | query | no | `asc` or `desc`. Default `desc` |

### Response

`200`. Only active products.

```json
{
  "content": [
    {
      "id": 1,
      "sku": "SKU-001",
      "barcode": "9555012345678",
      "name": "A4 Paper 80gsm",
      "description": "500 sheets",
      "category": "Stationery",
      "unit": "ream",
      "reorderLevel": 10,
      "active": true,
      "createdAt": "2026-10-29T07:00:00Z",
      "updatedAt": "2026-10-29T07:00:00Z"
    }
  ],
  "page": 0,
  "size": 20,
  "totalElements": 1,
  "totalPages": 1
}
```

## GET /api/products/{id}

### Request

| Name | In | Required | Description |
| --- | --- | --- | --- |
| `id` | path | yes | Product id |

### Response

`200`

```json
{
  "id": 1,
  "sku": "SKU-001",
  "barcode": "9555012345678",
  "name": "A4 Paper 80gsm",
  "description": "500 sheets",
  "category": "Stationery",
  "unit": "ream",
  "reorderLevel": 10,
  "active": true,
  "createdAt": "2026-10-29T07:00:00Z",
  "updatedAt": "2026-10-29T07:00:00Z"
}
```

`404` when the id does not exist.

```json
{ "type": "about:blank", "title": "Not Found", "status": 404, "detail": "Product 999 not found" }
```

## POST /api/products

### Request

| Name | In | Required | Description |
| --- | --- | --- | --- |
| `sku` | query | yes | Max 50. Stored uppercased |
| `barcode` | query | no | Max 64 |
| `name` | query | yes | Max 150 |
| `description` | query | no | Max 1000 |
| `category` | query | no | Max 80 |
| `unit` | query | yes | Max 20. For example `pcs`, `box`, `kg` |
| `reorder_level` | query | yes | Integer, `>= 0` |

### Response

`201`. Header `Location: /api/products/{id}`. `active` is true.

```json
{
  "id": 1,
  "sku": "SKU-001",
  "barcode": "9555012345678",
  "name": "A4 Paper 80gsm",
  "description": "500 sheets",
  "category": "Stationery",
  "unit": "ream",
  "reorderLevel": 10,
  "active": true,
  "createdAt": "2026-10-29T07:00:00Z",
  "updatedAt": "2026-10-29T07:00:00Z"
}
```

`400` when validation fails.

```json
{
  "title": "Bad Request",
  "status": 400,
  "detail": "Validation failed",
  "errors": { "name": "must not be blank", "reorderLevel": "must be greater than or equal to 0" }
}
```

`409` when the SKU already exists.

```json
{ "title": "Conflict", "status": 409, "detail": "SKU already exists" }
```

## PUT /api/products/{id}

### Request

| Name | In | Required | Description |
| --- | --- | --- | --- |
| `id` | path | yes | Product id |
| `sku` | query | yes | Max 50. Stored uppercased. Uniqueness ignores this id |
| `barcode` | query | no | Max 64 |
| `name` | query | yes | Max 150 |
| `description` | query | no | Max 1000 |
| `category` | query | no | Max 80 |
| `unit` | query | yes | Max 20 |
| `reorder_level` | query | yes | Integer, `>= 0` |

### Response

`200`

```json
{
  "id": 1,
  "sku": "SKU-001",
  "barcode": "9555012345678",
  "name": "A4 Paper 80gsm",
  "description": "500 sheets",
  "category": "Stationery",
  "unit": "ream",
  "reorderLevel": 10,
  "active": true,
  "createdAt": "2026-10-29T07:00:00Z",
  "updatedAt": "2026-10-29T07:00:00Z"
}
```

`400` when validation fails.

```json
{
  "title": "Bad Request",
  "status": 400,
  "detail": "Validation failed",
  "errors": { "name": "must not be blank", "reorderLevel": "must be greater than or equal to 0" }
}
```

`404` when the id does not exist.

```json
{ "type": "about:blank", "title": "Not Found", "status": 404, "detail": "Product 999 not found" }
```

`409` when the SKU already exists.

```json
{ "title": "Conflict", "status": 409, "detail": "SKU already exists" }
```

## DELETE /api/products/{id}

### Request

| Name | In | Required | Description |
| --- | --- | --- | --- |
| `id` | path | yes | Product id |

### Response

`204` with an empty body. Sets `active` to false and keeps the row.

`404` when the id does not exist.

```json
{ "type": "about:blank", "title": "Not Found", "status": 404, "detail": "Product 999 not found" }
```
