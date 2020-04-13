package com.example.demosqs.service;

import com.example.demosqs.config.AppProperties;
import com.example.demosqs.exception.MessageProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;

@SpringBootTest
public class QueueReadingServiceTest {

    @InjectMocks
    private QueueReadingService readingService;

    @Mock
    private AppProperties properties;

    @Mock
    private QueueSendingService sendingService;

    @BeforeEach
    void setMockOutput() {
        Mockito.when(properties.getDurationSimulating()).thenReturn(1000);
    }

    @Test
    public void successMessage() throws InterruptedException {
        String message = "message";
        readingService.receiveRequest(message);

        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
        Mockito.verify(sendingService, times(1)).sendResult(argument.capture());
        assertEquals("Received " + message, argument.getValue());
    }

    @Test
    public void errorMessage() {
        String message = "error";

        Exception exception = assertThrows(MessageProcessingException.class, () -> {
            readingService.receiveRequest(message);
        });

        String expectedMessage = "Message cannot processed";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}