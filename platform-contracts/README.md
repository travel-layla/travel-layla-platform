# Platform Contracts

## Overview

The **Platform Contracts** module defines shared communication contracts for the Travel Layla platform using **Protocol Buffers (protobuf)**. This module contains gRPC service definitions, event contracts, and common data structures that are used across multiple microservices.

## Purpose

- **Service Contracts**: Define gRPC service interfaces for inter-service communication
- **Event Contracts**: Define event schemas for event-driven architecture and event sourcing
- **Common Types**: Define shared data structures and error responses used across services
- **API Contracts**: Provide language-agnostic contracts ensuring consistency and compatibility between services

## Directory Structure

```
platform-contracts/
├── src/main/proto/
│   ├── common/              # Shared, reusable contracts
│   │   └── v1/
│   │       ├── error.proto
│   │       └── pagination.proto
│   ├── events/              # Event sourcing contracts
│   │   └── user/
│   │       └── v1/
│   │           └── user-created-event.proto
│   └── grpc/                # gRPC service definitions
│       └── user/
│           └── v1/
│               └── user.proto
└── src/test/java/           # Tests for contract changes
```

## Naming Convention

### Proto File Structure

#### 1. **Directory Organization**
```
src/main/proto/{category}/{domain}/{version}/
```

- **Category**: Classifies the contract type:
  - `common/` - Shared, reusable contracts (error types, pagination, etc.)
  - `events/` - Event sourcing contracts for asynchronous communication
  - `grpc/` - gRPC service definitions for synchronous RPC communication

- **Domain**: Business domain name (e.g., `user`, `order`, `payment`)
- **Version**: API version in format `v{number}` (e.g., `v1`, `v2`)

#### 2. **File Naming**
- **Format**: `kebab-case.proto`
- **Examples**:
  - `user.proto` - Main user service contracts
  - `user-created-event.proto` - Event published when user is created
  - `error.proto` - Error response definitions
  - `pagination.proto` - Pagination utilities

#### 3. **Package Naming**
- **Format**: `layla.{domain}.{version}`
- **Examples**:
  - `layla.common.v1`
  - `layla.user.v1`
  - `layla.events.user.v1`

#### 4. **Java Package Generation**
- **Format**: `com.layla.contracts.{domain}.{version}`
- **Examples**:
  - `com.layla.contracts.common.v1`
  - `com.layla.contracts.user.v1`

### Proto File Template

```protobuf
syntax = "proto3";

package layla.{domain}.v1;

option java_multiple_files = true;
option java_package = "com.layla.contracts.{domain}.v1";
option java_outer_classname = "{DomainCapitalized}Proto";

// Define messages and services here
```

### Key Naming Rules

1. **Message Names**: Use `PascalCase` (e.g., `GetUserRequest`, `UserResponse`, `UserCreatedEvent`)
2. **Field Names**: Use `snake_case` (e.g., `user_id`, `full_name`, `created_at`)
3. **Service Names**: Use `PascalCase` with `Service` suffix (e.g., `UserService`)
4. **RPC Method Names**: Use `PascalCase` (e.g., `GetUser`, `CreateUser`, `ListUsers`)
5. **Enum Names**: Use `SCREAMING_SNAKE_CASE` (e.g., `USER_STATUS_ACTIVE`, `ORDER_STATE_PENDING`)

## Building and Generating Code

### Compile Proto Files
```bash
# From travel-layla-platform root
mvn clean compile

# Or from platform-contracts directory
cd platform-contracts
mvn clean compile
```

### Generated Java Classes
After compilation, Java classes are generated in:
```
target/generated-sources/
```

Generated files follow the proto package structure:
```
com/layla/contracts/{domain}/{version}/*.java
```

## Proto File Guidelines

### Best Practices

1. **Versioning**: Always include version (`v1`, `v2`) to enable backward compatibility
   - Create new version directory when making breaking changes
   - Keep old versions for clients using them

2. **Imports**: Use relative imports for proto files
   ```protobuf
   import "common/v1/pagination.proto";
   import "common/v1/error.proto";
   ```

3. **Field Numbers**: Never reuse field numbers; always increment
   ```protobuf
   message User {
     string id = 1;           // ✓ First field
     string email = 2;        // ✓ Second field
     string phone = 3;        // ✗ Don't reuse 1 or 2
   }
   ```

4. **One Message Per Use Case**: Keep messages focused and single-responsibility
   ```protobuf
   // ✓ Good: Specific request type
   message GetUserRequest {
     string user_id = 1;
   }
   
   // ✗ Avoid: Mixing multiple operations
   message UserRequest {
     string user_id = 1;
     string email = 2;
     // ... many fields
   }
   ```

5. **Comment and Document**: Add comments for complex messages and fields
   ```protobuf
   // Represents a travel user account
   message User {
     // Unique identifier (UUID)
     string id = 1;
     // Email address (must be validated)
     string email = 2;
   }
   ```

## Integration with Microservices

Each microservice that consumes contracts should:

1. Add dependency in `pom.xml`:
   ```xml
   <dependency>
     <groupId>com.travel-layla</groupId>
     <artifactId>platform-contracts</artifactId>
     <version>1.0.0</version>
   </dependency>
   ```

2. Import generated classes:
   ```java
   import com.layla.contracts.user.v1.*;
   import com.layla.contracts.common.v1.ErrorResponse;
   ```

## Versioning Strategy

When introducing breaking changes:

1. Create new version directory (e.g., `v2/`)
2. Copy and modify contracts
3. Update dependent services gradually
4. Remove old version after deprecation period

Example:
```
proto/grpc/user/
├── v1/
│   └── user.proto       # Legacy version
└── v2/
    └── user.proto       # New version with breaking changes
```

## Related Documentation

- [Protocol Buffers Documentation](https://developers.google.com/protocol-buffers)
- [gRPC Best Practices](https://grpc.io/docs/guides/performance-best-practices/)
- [Event Sourcing Pattern](https://martinfowler.com/eaaDev/EventSourcing.html)

## Contributing

When adding or modifying contracts:

1. Follow naming conventions strictly
2. Increment field numbers properly
3. Add documentation comments
4. Test proto compilation: `mvn clean compile`
5. Ensure backward compatibility or create new version
6. Update this README if adding new categories or domains
