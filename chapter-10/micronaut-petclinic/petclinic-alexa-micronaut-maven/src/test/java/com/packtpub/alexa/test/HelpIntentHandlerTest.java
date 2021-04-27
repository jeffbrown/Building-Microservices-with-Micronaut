package com.packtpub.alexa.test;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.ui.SimpleCard;
import com.amazon.ask.model.ui.SsmlOutputSpeech;
import com.packtpub.alexa.HelpIntentHandler;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

    @MicronautTest
    class HelpIntentHandlerTest {

        @Inject
        private HelpIntentHandler handler;

        @Test
        void testHelpIntentHandler() {
            HandlerInput input = HandlerInput.builder()
                    .withRequestEnvelope(RequestEnvelope.builder()
                            .withRequest(IntentRequest.builder()
                                    .withIntent(Intent.builder()
                                            .withName("AMAZON.HelpIntent")
                                            .build())
                                    .build())
                            .build()
                    ).build();
            assertTrue(handler.canHandle(input));
            Optional<Response> responseOptional = handler.handle(input);
            assertTrue(responseOptional.isPresent());
            Response response = responseOptional.get();
            assertTrue(response.getOutputSpeech() instanceof SsmlOutputSpeech);
            String speechText = "I am here to help you to find nearby pet clinic";

            String expectedSsml = "<speak>" + speechText + "</speak>";
            assertEquals(expectedSsml, ((SsmlOutputSpeech) response.getOutputSpeech()).getSsml());

            assertNotNull(response.getReprompt().getOutputSpeech());
            assertTrue(response.getReprompt().getOutputSpeech() instanceof SsmlOutputSpeech);
            assertEquals(expectedSsml,((SsmlOutputSpeech) response.getReprompt().getOutputSpeech()).getSsml());
            assertTrue(response.getCard() instanceof SimpleCard);
            assertEquals("PetClinic", ((SimpleCard) response.getCard()).getTitle());
            assertEquals(speechText, ((SimpleCard) response.getCard()).getContent());
            assertFalse(response.getShouldEndSession());
        }
}
