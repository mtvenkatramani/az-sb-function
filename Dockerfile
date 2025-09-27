# Final runtime image
FROM mcr.microsoft.com/azure-functions/java:4-java11

# Set environment variables for Functions runtime
ENV AzureWebJobsScriptRoot=/home/site/wwwroot \
    AzureFunctionsJobHost__Logging__Console__IsEnabled=true \
    FUNCTIONS_WORKER_RUNTIME=java

WORKDIR /home/site/wwwroot

# Copy everything from the built function app
COPY target/azure-functions/az-sb-function/ .

# (Optional) check contents during build
RUN ls -R /home/site/wwwroot
