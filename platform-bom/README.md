# Platform BOM (Bill of Materials)

Platform BOM is a Maven module that manages dependency versions for the entire Travel Layla Platform project. It is the centralized place to define all external libraries that other modules will use.

## 📖 Purpose

The BOM module helps:
- **Centralized version management**: All submodules don't need to specify versions, they inherit from BOM
- **Avoid version conflicts**: Ensures the entire project uses consistent dependency versions
- **Simplified maintenance**: When upgrading versions, only update in one place

## 📦 Managed Dependencies

### 1. Spring Boot Framework
```xml
<groupId>org.springframework.boot</groupId>
<version>3.5.0</version>
```
- Spring Boot dependencies (includes Spring Framework 6.1.13)
- Provides the foundation for microservices

### 2. Apache Kafka
```xml
<groupId>org.springframework.kafka</groupId>
<version>3.3.5</version>
```
- Spring Kafka integration
- Handles message queues and streaming

### 3. gRPC
```xml
<groupId>io.grpc</groupId>
<version>1.73.0</version>
```
- gRPC Netty Shaded
- gRPC Protobuf
- gRPC Stub
- Used for RPC communication between microservices

### 4. Other Dependencies
- Database drivers (MySQL, PostgreSQL, etc.)
- Testing frameworks
- Logging libraries
- Utility libraries

## 🔍 How to Use

### In Submodules

Submodules (e.g., `platform-common`) inherit from BOM:

```xml
<parent>
    <groupId>com.travel-layla</groupId>
    <artifactId>platform</artifactId>
    <version>1.0.0</version>
</parent>
```

When adding dependencies, **no need to specify version**:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <!-- Version will be inherited from BOM -->
</dependency>
```

### Adding New Dependencies

1. Add to `<dependencyManagement>` section in `pom.xml`
2. Choose a stable and compatible version
3. Run `mvn clean install` to synchronize

Example adding Redis:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
    <version>3.5.0</version>
</dependency>
```

## 🧪 Build

```bash
# Build BOM module
cd platform-bom
mvn clean install

# Or build from root folder
mvn clean install -pl platform-bom
```

## 📋 Conventions

- **Independent**: BOM module contains no Java source code, only version management
- **Naming**: Artifact name is `platform-bom` (following Maven standard)
- **Packaging**: `pom` (this is a POM-only module)

## ✅ Best Practices

1. **Keep versions consistent**: All submodules must use versions from BOM
2. **Avoid overrides**: Don't override versions in submodules
3. **Test compatibility**: When upgrading major versions, test the entire project
4. **Document changes**: Record notes when changing dependency versions

## 🔗 References

- [Maven BOM Documentation](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html#Dependency_Management)
- [Spring Boot Parent POM](https://spring.io/projects/spring-boot)

## 📞 Support

If you encounter issues with dependencies or versions, please contact the development team.
