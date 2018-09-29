package org.crystaltreecode.pocask1.handlers

import java.util.Optional
import com.amazon.ask.dispatcher.request.handler.{HandlerInput, RequestHandler}
import com.amazon.ask.model.Response
import com.amazon.ask.request.Predicates
import com.amazon.ask.model.Intent
import com.amazon.ask.model.IntentRequest


class GivenPersonHasTheNewTaskIntentHandler extends RequestHandler {

  def canHandle(input: HandlerInput): Boolean =
    input.matches(Predicates.intentName("GivenPersonHasTheNewTaskIntentHandler"))

  def handle(input: HandlerInput): Optional[Response] = {
    val request = input.getRequestEnvelope.getRequest
    val intentRequest = request.asInstanceOf[IntentRequest]
    val intent = intentRequest.getIntent
    intent.getSlots

    val speechText = "still nothing new"
    input
      .getResponseBuilder
      .withSpeech(speechText)
      .withSimpleCard("DrillSergeant", speechText)
      .withReprompt(speechText)
      .build
  }
}
