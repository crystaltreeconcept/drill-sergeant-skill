package org.crystaltreecode.pocask1.handlers

import java.util.Optional

import com.amazon.ask.dispatcher.request.handler.{HandlerInput, RequestHandler}
import com.amazon.ask.model.Response
import com.amazon.ask.request.Predicates.intentName

class FallbackIntentHandler extends RequestHandler {

  def canHandle(input: HandlerInput): Boolean =
    input.matches(intentName("AMAZON.FallbackIntent"))

  def handle(input: HandlerInput): Optional[Response] = {
    val speechText = "Sorry, I don't know that. You can say try saying help!"
    input
      .getResponseBuilder
      .withSpeech(speechText)
      .withSimpleCard("DrillSergeant", speechText)
      .withReprompt(speechText)
      .build
  }
}
