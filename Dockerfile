FROM eclipse-temurin:21-jdk as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM eclipse-temurin:21-jre
VOLUME /tmp

# Create non-root user for security
RUN groupadd -r spring && useradd -r -g spring spring

# Set up application directory
WORKDIR /app
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# Change ownership to non-root user
RUN chown -R spring:spring /app

# Switch to non-root user
USER spring

# Expose port (configurable via environment)
EXPOSE ${SERVER_PORT:-8080}

# Environment variables with secure defaults
ENV SPRING_PROFILES_ACTIVE=production
ENV SERVER_PORT=8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:${SERVER_PORT}/actuator/health || exit 1

ENTRYPOINT ["java","-cp","app:app/lib/*","sofrecom.onetouch.desk_service.DeskserviceApplication"]
