# Travel Layla Platform

Travel Layla Platform is a microservices system for a travel booking application. The project is built with **Java 21** and **Spring Boot 3.5.0** following Maven multi-module architecture.

## 📋 Project Structure

```
travel-layla-platform/
├── platform-bom/           # Bill of Materials - Dependency version management
├── platform-common/        # Common utilities - Shared library for all modules
└── README.md               # Documentation
```

## 🛠️ Technologies Used

- **Java**: 21
- **Spring Boot**: 3.5.0
- **Spring Framework**: 6.1.13
- **Apache Kafka**: 3.3.5
- **gRPC**: 1.73.0
- **Build Tool**: Maven 3.x

## 📦 Modules Overview

### 1. [platform-bom](./platform-bom/README.md)
Bill of Materials module - Centralized dependency version management for the entire project.

**Managed dependencies:**
- Spring Boot framework
- Apache Kafka
- gRPC
- Other libraries

### 2. [platform-common](./platform-common/README.md)
Common utilities module - Provides shared components for other microservices.

**Main components:**
- Exception handling (BaseException, BusinessException, ForbiddenException, etc.)
- Constants (Error codes, Error messages, Headers, Trace)
- Error handling utilities

## ⚙️ Installation and Setup

### Prerequisites
- Java 21 or higher
- Maven 3.6.0 or higher
- Git

### Build the Project

```bash
# Clone repository
git clone https://github.com/Travel-Layla/travel-layla-platform.git
cd travel-layla-platform

# Build entire project
mvn clean install

# Build specific module
mvn clean install -pl platform-common
```

### Maven Structure

The project uses a multi-module structure:
- **Root POM** (`pom.xml`): Defines common configuration for all modules
- **Module POMs**: Each submodule has its own `pom.xml` inheriting from root

## 📝 Code Conventions

- **Package names**: `com.travel.layla.platform.*`
- **Class names**: PascalCase
- **Method names**: camelCase
- **Constants**: UPPER_SNAKE_CASE
- **Encoding**: UTF-8

## 🔧 IDE Setup

### IntelliJ IDEA
1. Open project: `File > Open > select travel-layla-platform folder`
2. IDE will automatically detect Maven project
3. Maven dependencies will be downloaded automatically

### Visual Studio Code
1. Install Extensions:
   - Extension Pack for Java
   - Maven for Java

2. Open project folder

## 📚 Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Apache Maven Documentation](https://maven.apache.org/guides/index.html)
- [gRPC Java Documentation](https://grpc.io/docs/languages/java/)

## 🤝 Contributing

Please create a new branch for each feature/bugfix and submit a pull request with detailed description.

## 📄 License

This project is licensed under MIT License.

## 👥 Contact

Contact the development team for more detailed information.
