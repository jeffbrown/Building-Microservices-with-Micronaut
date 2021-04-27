package com.packtpub.alexa.test;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.SessionEndedRequest;
import com.packtpub.alexa.SessionEndedRequestHandler;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
public class SessionEndedRequestIntentHandlerTest {

    @Inject
    SessionEndedRequestHandler handler;

    @Test
    void testSessionEndedRequestIntentHandler() {
        SessionEndedRequest request = SessionEndedRequest.builder().build();
        HandlerInput input = HandlerInput.builder()
                .withRequestEnvelope(RequestEnvelope.builder()
                        .withRequest(request)
                        .build()
                ).build();
        assertTrue(handler.canHandle(input));
        Optional<Response> responseOptional = handler.handle(input);
        assertTrue(responseOptional.isPresent());
    }
}