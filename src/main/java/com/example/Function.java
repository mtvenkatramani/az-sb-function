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
}
