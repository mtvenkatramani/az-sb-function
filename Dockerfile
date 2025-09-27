# Use official Azure Functions Java 11 base image
FROM mcr.microsoft.com/azure-functions/java:4-java8

# Set working directory
WORKDIR /home/site/wwwroot

# Copy the built JAR from the target folder
COPY target/azure-servicebus-function-1.0-SNAPSHOT.jar ./azure-servicebus-function.jar

RUN (ls -al)

# Set the entry point to the Azure Functions host
ENV AzureWebJobsScriptRoot=/home/site/wwwroot \
    FUNCTIONS_WORKER_RUNTIME=java

# Expose port 80
EXPOSE 80

# Start Azure Functions host
CMD ["java", "-jar", "azure-servicebus-function.jar"]
