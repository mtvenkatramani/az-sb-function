# Stage 1: Build the Java Function App
FROM mcr.microsoft.com/azure-functions/java:4-java8-build AS builder

WORKDIR /src

# Copy the Maven project files
COPY pom.xml .
COPY host.json .

# Copy the function source code
COPY src ./src

# Package the function app
RUN mvn clean package

# Stage 2: Create the final runtime image
FROM mcr.microsoft.com/azure-functions/java:4-java8

ENV AzureWebJobsScriptRoot=/home/site/wwwroot \
    AzureFunctionsJobHost__Logging__Console__IsEnabled=true

# Copy the packaged function app from the builder stage
COPY --from=builder /src/target/azure-functions/az-sb-function /home/site/wwwroot