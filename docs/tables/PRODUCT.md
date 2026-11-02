# Product

A product is a catalog item identified by SKU. Stock is not stored on this table. Quantity lives on `stock_level` and changes only through `stock_movement`.

Table: `product`. Entity `Product` extends `BaseEntity` (`id`, `created_at`, `updated_at`).

## Columns

| Column          | Data type                | Constraint                           | Description                                                                 |
| --------------- | ------------------------ | ------------------------------------ | --------------------------------------------------------------------------- |
| `id`            | BIGINT                   | primary key, identity, not null      | Surrogate key                                                               |
| `sku`           | VARCHAR(50)              | not null, unique                     | Business key. Stored uppercased                                             |
| `barcode`       | VARCHAR(64)              | nullable, unique                     | Scannable code. Absent for items that have no barcode                       |
| `name`          | VARCHAR(150)             | not null                             | Display name                                                                |
| `description`   | VARCHAR(1000)            | nullable                             | Longer text shown on the product                                            |
| `category`      | VARCHAR(80)              | nullable                             | Grouping used by search, for example Stationery                             |
| `unit`          | VARCHAR(20)              | not null                             | How the item is counted, for example `pcs`, `box`, `kg`                     |
| `reorder_level` | INTEGER                  | not null, `>= 0`                     | Quantity at or below which the item is low stock                            |
| `active`        | BOOLEAN                  | not null                             | `false` means soft-deleted. The row stays so movement history remains valid |
| `created_at`    | TIMESTAMP WITH TIME ZONE | not null, set on insert, not updated | When the row was inserted                                                   |
| `updated_at`    | TIMESTAMP WITH TIME ZONE | not null, set on insert and update   | When the row was last changed                                               |

## Relationships

`product` has no foreign keys. Other tables point at it:

- `stock_level.product_id` references `product.id`. Many stock levels per product, one per branch. Required (`optional = false`).
- `stock_movement.product_id` references `product.id`. Many movements per product. Required (`optional = false`).

## Indexes

| Name                   | Columns    | Kind        | Why                                                     |
| ---------------------- | ---------- | ----------- | ------------------------------------------------------- |
| `pk_product`           | `id`       | primary key | Row identity                                            |
| `uk_product_sku`       | `sku`      | unique      | One row per SKU                                         |
| `uk_product_barcode`   | `barcode`  | unique      | One row per barcode. Multiple null barcodes are allowed |
| `idx_product_name`     | `name`     | non-unique  | Name search                                             |
| `idx_product_category` | `category` | non-unique  | Category filter                                         |

## Rules

- Create and update reject a duplicate SKU.
- Delete sets `active = false`. The row stays, because movements keep history.
- Search lists only active rows.
