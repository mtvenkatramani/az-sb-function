# Stage 2: Create the final runtime image
FROM mcr.microsoft.com/azure-functions/java:4-java11

ENV AzureWebJobsScriptRoot=/home/site/wwwroot \
    AzureFunctionsJobHost__Logging__Console__IsEnabled=true

RUN (ls -al)

# Copy the packaged function app from the builder stage
COPY /src/target/azure-functions/az-sb-function/* /home/site/wwwroot