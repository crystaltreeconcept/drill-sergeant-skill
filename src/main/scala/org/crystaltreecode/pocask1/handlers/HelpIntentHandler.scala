package org.crystaltreecode.pocask1.handlers

import java.util.Optional

import com.amazon.ask.dispatcher.request.handler.{HandlerInput, RequestHandler}
import com.amazon.ask.model.Response
import com.amazon.ask.request.Predicates.intentName

class HelpIntentHandler extends RequestHandler {

  def canHandle(input: HandlerInput): Boolean =
    input.matches(intentName("AMAZON.HelpIntent"))

  def handle(input: HandlerInput): Optional[Response] = {
    val speechText = "You can say hello to me!"
    input
      .getResponseBuilder
      .withSpeech(speechText)
      .withSimpleCard("DrillSergeant", speechText)
      .withReprompt(speechText)
      .build
  }
}
