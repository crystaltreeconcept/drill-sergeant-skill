package org.crystaltreecode.pocask1.handlers

import java.util
import java.util.Optional

import com.amazon.ask.dispatcher.request.handler.{HandlerInput, RequestHandler}
import com.amazon.ask.model.{IntentRequest, Response, Slot}
import com.amazon.ask.request.Predicates.intentName
import com.gu.scanamo.error.DynamoReadError

import scala.util.{Failure, Success, Try}


/**
  * serves the 'list the users' request
  *   doesn't keep the session open, so no need to re-prompt, don't set
  *
  */
class RosterAddIntentHandler extends RequestHandler {

  def canHandle(input: HandlerInput): Boolean =
    input.matches(intentName("RosterAddIntent"))

  def handle(input: HandlerInput): Optional[Response] = {
    //this is not a Rookie name, just the household user to strip users by
    val userId = input.getRequestEnvelope.getSession.getUser.getUserId

    val request = input.getRequestEnvelope.getRequest.asInstanceOf[IntentRequest]
    val slots: util.Map[String, Slot] = request.getIntent.getSlots


    def makeError(string:String) = input.getResponseBuilder
      .withSpeech("Sorry didn't get what's your name, rookie")
      .withSimpleCard("DrillSergeant", "Sorry didn't get what's your name, rookie, due to: " + string)
      .withShouldEndSession(true)
      .build

    Try {
      slots.get("RookieName") //TODO: find nice constants place
    } map { slot:Slot =>

      val maybeInsertedRecord:Option[Either[DynamoReadError, SoldiersDataSource.SoldierRecord]]
        = SoldiersDataSource.registerNewSoldier(input.getRequestEnvelope.getSession.getUser.getUserId, slot.getValue)

      //TODO: re-factor this piece, we'll need single routine of multiple mapping ideally
      maybeInsertedRecord match {
        case Some(Right(_)) =>
          input.getResponseBuilder
            .withSpeech("Ok, I'll make sure we record this " + slot.getValue)
            .withSimpleCard("DrillSergeant", "Ok, I'll make sure we record this" + slot.getValue)
            .withShouldEndSession(true)
            .build
        case _ => makeError("Some error")
      }
    } getOrElse(
      makeError("No slot name <RookieName>?")
    )

  }


//  case Failure(errorString) => input
//    .getResponseBuilder()
//    .withSpeech("Sorry didn't get what's your name, rookie")
//    .withSimpleCard("DrillSergeant", errorString.getMessage)
//    .withShouldEndSession(false)
//    .build
//
//    val listUsersResult:List[Either[DynamoReadError, SoldiersDataSource.Soldier]]
//          = SoldiersDataSource.registerNewSoldier()
//
//    //TODO: process and log the errors properly
//    //TODO: do smart check on SOUNDING LIKE existing users, we don't want to promote confusion
//
//    val soldierNames:List[String] = listUsersResult.collect({
//      case Right(soldier) => soldier.name
//    })
//    val errors:List[String] = listUsersResult.collect({
//      case Left(error) => error.toString
//    })
//
//    val speechText = s"Your roster for today is: ${soldierNames.mkString(", ")}"
//
//    val cardText:String = if (errors.isEmpty) speechText else errors.mkString(", ")
//
//    input
//      .getResponseBuilder
//      .withSpeech(speechText)
//      .withSimpleCard("DrillSergeant", cardText)
//      .withShouldEndSession(false)
//      .build

}
