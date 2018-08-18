package org.crystaltreecode.pocask1.handlers

import java.util.Optional

import com.amazon.ask.dispatcher.request.handler.{HandlerInput, RequestHandler}
import com.amazon.ask.model.Response
import com.amazon.ask.request.Predicates.intentName

class CancelAndStopIntentHandler extends RequestHandler {

  def canHandle(input: HandlerInput): Boolean =
    input.matches(intentName("AMAZON.StopIntent").or(intentName("AMAZON.CancelIntent")))

  def handle(input: HandlerInput): Optional[Response] = {
    val speechText = "Keep up the good work... Over"
    input
      .getResponseBuilder
      .withSpeech(speechText)
      .withSimpleCard("DrillSergeant", speechText)
      .withReprompt(speechText)
      .build
  }
}
