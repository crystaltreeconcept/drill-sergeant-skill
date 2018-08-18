package org.crystaltreecode.pocask1.handlers

import java.util.Optional

import com.amazon.ask.dispatcher.request.handler.{HandlerInput, RequestHandler}
import com.amazon.ask.model.Response
import com.amazon.ask.request.Predicates

class DrillSergeantIntentHandler extends RequestHandler {

  def canHandle(input: HandlerInput): Boolean =
    input.matches(Predicates.intentName("DrillSergeantIntent"))

  def handle(input: HandlerInput): Optional[Response] = {
    val speechText = "Report in, soldier"
    input
      .getResponseBuilder
      .withSpeech(speechText)
      .withSimpleCard("DrillSergeant", speechText)
      .withReprompt(speechText)
      .build
  }
}
