package org.crystaltreecode.pocask1.handlers

import java.util.Optional

import com.amazon.ask.dispatcher.request.handler.{HandlerInput, RequestHandler}
import com.amazon.ask.model.{IntentRequest, Response}
import com.amazon.ask.request.Predicates.intentName
import com.gu.scanamo.error.DynamoReadError


/**
  * serves the 'list the users' request
  *   doesn't keep the session open, so no need to re-prompt, don't set
  *
  */
class RosterListIntentHandler extends RequestHandler {

  def canHandle(input: HandlerInput): Boolean =
    input.matches(intentName("RosterListIntent"))

  def handle(input: HandlerInput): Optional[Response] = {

    val listUsersResult:List[Either[DynamoReadError, SoldiersDataSource.SoldierRecord]]
          = SoldiersDataSource.listRosterNames(input.getRequestEnvelope.getSession.getUser.getUserId)

    //TODO: process and log the errors properly

    val soldierNames:List[String] = listUsersResult.collect({
      case Right(soldier) => soldier.name
    })
    val errors:List[String] = listUsersResult.collect({
      case Left(error) => error.toString
    })

    val speechText = s"Your roster for today is: ${soldierNames.mkString(", ")}"

    val cardText:String = if (errors.isEmpty) speechText else errors.mkString(", ")

    input
      .getResponseBuilder
      .withSpeech(speechText)
      .withSimpleCard("DrillSergeant", cardText)
      .withShouldEndSession(false)
      .build
  }
}
