package org.crystaltreecode.pocask1.handlers

import java.util.Optional

import com.amazon.ask.dispatcher.request.handler.{HandlerInput, RequestHandler}
import com.amazon.ask.model.{LaunchRequest, Response}
import com.amazon.ask.request.Predicates

class LaunchRequestHandler extends RequestHandler {

  def canHandle(input: HandlerInput): Boolean =
    input.matches(Predicates.requestType(classOf[LaunchRequest]))

  def handle(input: HandlerInput): Optional[Response] = {
    val speechText = "Drill Sergeant, at your service"
    input
      .getResponseBuilder
      .withSpeech(speechText)
      .withSimpleCard("DrillSergeant", speechText)
      .withReprompt(speechText)
      .build
  }
}
