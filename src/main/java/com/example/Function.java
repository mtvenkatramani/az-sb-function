package com.example;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import java.util.logging.Logger;


public class Function {
    @FunctionName("ProcessServiceBusTopicMessage")
    public void run(
        @ServiceBusTopicTrigger(
            name = "message",
            topicName = "mtv_topic",
            subscriptionName = "mtv_subscription",
            connection = "ServiceBusConnection"
        ) String message,
        final ExecutionContext context) {
        
        Logger logger = context.getLogger();
        logger.info("Java Service Bus Topic trigger function processed a message: " + message);

        // Example: process the message
        try {
            // Add business logic here
            logger.info("Message length: " + message.length());
        } catch (Exception ex) {
            logger.severe("Error processing message: " + ex.getMessage());
            throw ex; // re-throw to abandon message
        }
    }
    /** Blob trigger */
    @FunctionName("BlobTriggerFunction")
    public void run(
        @BlobTrigger(name = "blob", 
                     path = "mtv-docs/{name}", 
                     dataType = "binary", 
                     connection = "AzureWebJobsStorage") byte[] content,
        @BindingName("name") String blobName,
        final ExecutionContext context) {
        
        Logger logger = context.getLogger();
        logger.info("Blob trigger function processed blob: " + blobName);
        logger.info("Size: " + content.length + " bytes");
    }
    /** Event Hubs Trigger */
    @FunctionName("EventHubTriggerFunction")
    public void run(
        @EventHubTrigger(name = "messages",
                         eventHubName = "mtv-event-hub",
                         connection = "EventHubConnection",
                         consumerGroup = "$Default") String[] messages,
        final ExecutionContext context) {
        
        Logger logger = context.getLogger();
        for (String message : messages) {
            logger.info("Event Hub message: " + message);
        }
    }
}
