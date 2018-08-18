package org.crystaltreecode.pocask1.handlers

import java.util.Optional

import com.amazon.ask.dispatcher.request.handler.{HandlerInput, RequestHandler}
import com.amazon.ask.model.{Response, SessionEndedRequest}
import com.amazon.ask.request.Predicates.requestType

class SessionEndedRequestHandler extends RequestHandler {

  def canHandle(input: HandlerInput): Boolean =
    input.matches(requestType(classOf[SessionEndedRequest]))

  def handle(input: HandlerInput): Optional[Response] = {
    //any cleanup logic goes here
    input.getResponseBuilder.build
  }
}
