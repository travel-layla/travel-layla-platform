# Platform Common

Platform Common is a module that provides shared components, utilities, and exception handling for all microservices in the Travel Layla Platform. It is the centralized location for reusable logic to avoid code duplication.

## 📖 Purpose

The Platform Common module provides:
- **Exception Handling**: Consistent error handling across the entire project
- **Constants**: Shared constant definitions
- **Base Classes**: Reusable base classes/interfaces
- **Utilities**: Helper functions and other utilities

## 🏗️ Module Structure

```
platform-common/
├── src/
│   ├── main/java/com/travel/layla/platform/common/
│   │   ├── constant/              # Constants definitions
│   │   │   ├── CommonErrorCodes.java
│   │   │   ├── CommonErrorMessages.java
│   │   │   ├── HeaderConstants.java
│   │   │   └── TraceConstants.java
│   │   ├── error/                 # Error code mapping
│   │   │   ├── CommonErrorCode.java
│   │   │   └── ErrorCode.java
│   │   └── exception/             # Custom exceptions
│   │       ├── BaseException.java
│   │       ├── BusinessException.java
│   │       ├── ConflictException.java
│   │       ├── ForbiddenException.java
│   │       ├── ResourceNotFoundException.java
│   │       ├── UnauthorizedException.java
│   │       └── ValidationException.java
│   └── resources/
└── pom.xml
```

## 🎯 Main Components

### 1. Exception Classes

#### BaseException
Base class for all custom exceptions. Provides:
- Error code
- Error message
- Error details

```java
throw new BaseException("ERR_001", "Something went wrong");
```

#### BusinessException
Exception for business logic violations:
```java
throw new BusinessException("ERR_BIZ_001", "Invalid booking data");
```

#### ForbiddenException
Exception when user lacks access permissions:
```java
throw new ForbiddenException("ERR_FORBIDDEN", "Access denied");
```

#### UnauthorizedException
Exception when user is not authenticated:
```java
throw new UnauthorizedException("ERR_UNAUTH", "Authentication required");
```

#### ResourceNotFoundException
Exception when resource is not found:
```java
throw new ResourceNotFoundException("ERR_NOT_FOUND", "Tour not found");
```

#### ValidationException
Exception when data is invalid:
```java
throw new ValidationException("ERR_VALIDATION", "Invalid email format");
```

#### ConflictException
Exception when there is a conflict (e.g., duplicate):
```java
throw new ConflictException("ERR_CONFLICT", "Email already exists");
```

### 2. Error Codes

#### CommonErrorCodes
Defines all error code constants:
```java
public class CommonErrorCodes {
    public static final String ERR_001 = "ERR_001";
    public static final String ERR_BIZ_001 = "ERR_BIZ_001";
    // ... more codes
}
```

#### CommonErrorMessages
Defines corresponding error messages:
```java
public class CommonErrorMessages {
    public static final String MSG_INVALID_INPUT = "Invalid input provided";
    public static final String MSG_NOT_FOUND = "Resource not found";
    // ... more messages
}
```

### 3. Constants

#### HeaderConstants
HTTP header constants:
```java
public class HeaderConstants {
    public static final String AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "Content-Type";
    // ... more headers
}
```

#### TraceConstants
Constants for distributed tracing:
```java
public class TraceConstants {
    public static final String TRACE_ID = "X-Trace-Id";
    public static final String SPAN_ID = "X-Span-Id";
    // ... more constants
}
```

## 💡 How to Use

### Using Exceptions

```java
import com.travel.layla.platform.common.exception.*;
import com.travel.layla.platform.common.constant.*;

public class BookingService {
    public void bookTour(String tourId, String userId) {
        // Validation
        if (tourId == null || tourId.isEmpty()) {
            throw new ValidationException(
                CommonErrorCodes.ERR_VALIDATION,
                CommonErrorMessages.MSG_INVALID_INPUT
            );
        }
        
        // Check authorization
        if (!userHasPermission(userId)) {
            throw new ForbiddenException(
                CommonErrorCodes.ERR_FORBIDDEN,
                "User does not have permission to book"
            );
        }
        
        // Check resource exists
        if (!tourExists(tourId)) {
            throw new ResourceNotFoundException(
                CommonErrorCodes.ERR_NOT_FOUND,
                "Tour not found"
            );
        }
        
        // Check duplicate booking
        if (alreadyBooked(tourId, userId)) {
            throw new ConflictException(
                CommonErrorCodes.ERR_CONFLICT,
                "Already booked this tour"
            );
        }
        
        // Perform booking
        // ...
    }
}
```

### Using Constants

```java
import com.travel.layla.platform.common.constant.*;

public class ApiController {
    public ResponseEntity<?> getTourDetails(@RequestHeader(HeaderConstants.AUTHORIZATION) String token) {
        // Use trace ID from header
        String traceId = extractTraceId(HeaderConstants.TRACE_ID);
        
        // Process request
        // ...
    }
}
```

## 🔄 Global Exception Handler

To handle exceptions consistently, each service should implement a `GlobalExceptionHandler`:

```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException e) {
        ErrorResponse response = ErrorResponse.builder()
            .code(e.getCode())
            .message(e.getMessage())
            .timestamp(LocalDateTime.now())
            .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException e) {
        // Return 401 Unauthorized
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(...);
    }
    
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<?> handleForbiddenException(ForbiddenException e) {
        // Return 403 Forbidden
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(...);
    }
    
    // ... handle other exceptions
}
```

## 🏢 Adding New Components

When adding a new exception or constant:

1. **Add Exception Class** (if needed):
   ```java
   // src/main/java/com/travel/layla/platform/common/exception/
   public class YourException extends BaseException {
       public YourException(String code, String message) {
           super(code, message);
       }
   }
   ```

2. **Add Error Code** (if needed):
   ```java
   // Add to CommonErrorCodes.java
   public static final String ERR_YOUR_CODE = "ERR_YOUR_CODE";
   ```

3. **Add Error Message** (if needed):
   ```java
   // Add to CommonErrorMessages.java
   public static final String MSG_YOUR_MESSAGE = "Your error message";
   ```

## 🧪 Build

```bash
# Build platform-common module
cd platform-common
mvn clean install

# Or build from root folder
mvn clean install -pl platform-common
```

## 📦 Dependencies

Platform Common only depends on:
- Java Standard Library
- Spring Framework (if using Spring annotations)

## ✅ Best Practices

1. **Use specific exceptions**: Choose the appropriate exception class instead of generic Exception
2. **Provide error code**: Always provide an error code for client-side handling
3. **Clear messages**: Error messages should be clear and help with debugging
4. **Reuse constants**: Avoid hardcoding strings, use constants
5. **Document code**: Add JavaDoc for new exceptions and constants

## 🔗 References

- [Exception Handling Best Practices](https://www.oracle.com/java/technologies/javase/javadesignpatterns-exceptions.html)
- [Spring Exception Handling](https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc)

## 📞 Support

If you need to add new exceptions or constants, please contact the development team.
