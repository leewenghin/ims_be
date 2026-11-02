# Backend Docs

Spring Boot 4.1 / Java 25 / Gradle / H2. See [../../docs/overall-plan.md](../../docs/overall-plan.md) for the full design.

| Version | Guide | You will learn |
|---|---|---|
| v0.1 | [Project structure](v0.1-project-structure.md) | Gradle dependencies, `application.yml`, layered packages |
| v0.2 | [Products CRUD](v0.2-products-crud.md) | Entity, repository, service, controller, DTO records, validation, error handling, paging |
| v0.3 | [Branches and seed data](v0.3-branches-and-seed-data.md) | Repeating the CRUD pattern, `ApplicationRunner`, reading JSON with Jackson |
| v0.4 | [Inventory and stock movements](v0.4-inventory-and-stock-movements.md) | Relationships, transactions, optimistic locking, ledgers, Specifications |
| v0.5 | [Auth (JWT) and RBAC](v0.5-auth-jwt-and-rbac.md) | Spring Security, password hashing, JWT, method security, branch scoping |
| v0.6 | [User management](v0.6-user-management.md) | Authorization rules in services, safe DTOs |
| v0.7 | [Audit log](v0.7-audit-log.md) | Cross-cutting concerns, transaction propagation, JSON columns |
| v0.8 | [Barcode, import, integration](v0.8-barcode-import-integration.md) | Multipart upload, CSV/Excel parsing, batch validation, API-key auth |
| v1.0 | [Testing and hardening](v1.0-testing-and-hardening.md) | Unit tests, slice tests, MockMvc + security tests, production readiness |

## Final package layout

```
backend/src/main/java/com/example/ims/
  ImsApplication.java
  config/        DataSeeder, OpenApiConfig
  controller/    REST endpoints (DTO in, DTO out)
  dto/           Java records: *Request, *Response, PageResponse
  entity/        JPA entities and enums
  exception/     GlobalExceptionHandler and custom exceptions
  repository/    Spring Data JPA interfaces
  security/      SecurityConfig, JwtService, CurrentUser, BranchAccessGuard, ApiKeyAuthFilter
  service/       business rules and transactions
backend/src/main/resources/
  application.yml
  seed/          branches.json, products.json, opening-stock.json, users.json, api-clients.json
backend/src/test/java/com/example/ims/
  controller/    integration tests (MockMvc)
  repository/    @DataJpaTest slice tests
  service/       unit tests (Mockito)
```

## Layer rules
- A **controller** never touches a repository or an entity. It calls a service and returns DTOs.
- A **service** owns `@Transactional` and all business rules. It maps entities to DTOs.
- A **repository** contains only queries. No logic.
- An **entity** is never serialized to JSON.

## Handy commands (PowerShell)
```powershell
.\gradlew.bat bootRun        # run
.\gradlew.bat test           # tests
.\gradlew.bat clean build    # full build
```
