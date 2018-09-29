package org.crystaltreecode.pocask1.handlers

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.request.Predicates

trait SelfHandleCheck {

  def canHandle(input: HandlerInput): Boolean =
    input.matches(Predicates.intentName(this.getClass.getSimpleName))

}
