# MongoDB Security Configuration Guide

## Overview
This application is configured to use different MongoDB instances for development and production environments with proper security practices.

## Environment Configuration

### Development Environment
- Uses local MongoDB instance
- Relaxed security settings for easier development
- Debug logging enabled
- All actuator endpoints exposed

### Production Environment
- Uses MongoDB Atlas cluster with credentials from environment variables
- Secure authentication and authorization
- Minimal logging and actuator endpoints
- All sensitive data externalized

## Setup Instructions

### 1. Development Setup
For local development, ensure MongoDB is running locally:
```bash
# Install MongoDB locally or use Docker
docker run -d --name mongodb -p 27017:27017 mongo:latest
```

The application will automatically use the development profile and connect to local MongoDB.

### 2. Production Setup

#### Environment Variables
Set the following environment variables in your production environment:

```bash
export SPRING_PROFILES_ACTIVE=production
export MONGODB_URI="mongodb+srv://username:password@cluster0.mongodb.net/onetouch_desks?retryWrites=true&w=majority"
export MONGODB_DATABASE="onetouch_desks"
export SECURITY_USERNAME="your_admin_username"
export SECURITY_PASSWORD="your_secure_password"
export SERVER_PORT=8080
```

#### MongoDB Atlas Security Checklist
1. **Network Security**: Configure IP whitelist in MongoDB Atlas
2. **Database Authentication**: Use strong username/password combinations
3. **Connection Security**: Always use SSL/TLS (mongodb+srv://)
4. **Database User Roles**: Create users with minimal required permissions
5. **Connection String**: Never commit connection strings to version control

#### Recommended MongoDB Atlas User Permissions
Create a database user with these roles:
- `readWrite` on your application database
- `dbAdmin` on your application database (if schema changes are needed)

### 3. Running the Application

#### Development
```bash
mvn spring-boot:run
# or
mvn spring-boot:run -Dspring-boot.run.profiles=development
```

#### Production
```bash
export SPRING_PROFILES_ACTIVE=production
# Set other environment variables...
java -jar target/desk_service-0.0.1-SNAPSHOT.jar
```

#### Using Docker
```bash
docker run -e SPRING_PROFILES_ACTIVE=production \
           -e MONGODB_URI="your_mongodb_uri" \
           -e SECURITY_PASSWORD="your_password" \
           -p 8080:8080 \
           your-app-image
```

## Security Best Practices Implemented

1. **Environment Separation**: Different configurations for dev/prod
2. **Credential Externalization**: No hardcoded credentials in code
3. **Secure Defaults**: Production defaults to secure settings
4. **Minimal Exposure**: Limited actuator endpoints in production
5. **Version Control Safety**: Sensitive files in .gitignore
6. **Connection Security**: MongoDB Atlas uses TLS by default

## Configuration Files Structure

- `application.properties`: Base configuration, no sensitive data
- `application-development.properties`: Development-specific settings
- `application-production.properties`: Production-specific settings (uses env vars)
- `.env.example`: Template for required environment variables
- `.gitignore`: Prevents committing sensitive files

## Monitoring and Health Checks

### Development
- Full actuator endpoints available at `/actuator`
- Detailed health information exposed

### Production
- Limited to `/actuator/health` and `/actuator/info`
- Health details only shown to authorized users

## Troubleshooting

### Common Issues
1. **Connection Timeout**: Check MongoDB Atlas IP whitelist
2. **Authentication Failed**: Verify username/password in environment variables
3. **SSL Connection Issues**: Ensure using `mongodb+srv://` protocol
4. **Profile Not Loading**: Verify `SPRING_PROFILES_ACTIVE` environment variable

### Logging
Enable MongoDB connection logging in development:
```properties
logging.level.org.springframework.data.mongodb=DEBUG
```
