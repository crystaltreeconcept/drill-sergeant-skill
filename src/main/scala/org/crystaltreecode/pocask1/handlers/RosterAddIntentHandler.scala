package org.crystaltreecode.pocask1.handlers

import java.util
import java.util.Optional

import com.amazon.ask.dispatcher.request.handler.{HandlerInput, RequestHandler}
import com.amazon.ask.model.{IntentRequest, Response, Slot}
import com.amazon.ask.request.Predicates.intentName
import com.gu.scanamo.error.DynamoReadError
import org.crystaltreecode.pocask1.persistence.SoldiersDataSource

import scala.util.Try


/**
  * serves the 'list the users' request
  *   doesn't keep the session open, so no need to re-prompt, don't set
  *
  */
class RosterAddIntentHandler extends RequestHandler {

  import org.apache.logging.log4j.{LogManager, Logger}

  val logger: Logger = LogManager.getLogger(classOf[RosterAddIntentHandler])

  def canHandle(input: HandlerInput): Boolean =
    input.matches(intentName("RosterAddIntent"))

  def handle(input: HandlerInput): Optional[Response] = {
    logger.info("going to handle " + input.toString)
    //this is not a Rookie name, just the household user to strip users by
    val userId = input.getRequestEnvelope.getSession.getUser.getUserId
    logger.info(s"userId is <$userId>")

    val request = input.getRequestEnvelope.getRequest.asInstanceOf[IntentRequest]
    val slots: util.Map[String, Slot] = request.getIntent.getSlots

    def makeError(string:String) = input.getResponseBuilder
      .withSpeech("Sorry didn't get what's your name, rookie")
      .withSimpleCard("DrillSergeant", "Sorry didn't get what's your name, rookie, due to: " + string)
      .withShouldEndSession(true)
      .build

    val result = Try {
      slots.get("RookieName") //TODO: find nice constants place
    } map { slot:Slot =>
      logger.info(s"found slot: <$slot>")

      val maybeInsertedRecord:Option[Either[DynamoReadError, SoldiersDataSource.SoldierRecord]]
        = SoldiersDataSource.registerNewSoldier(input.getRequestEnvelope.getSession.getUser.getUserId, slot.getValue)

      logger.info(s"inserted Record: <$maybeInsertedRecord>")

      //TODO: re-factor this piece, we'll need single routine of multiple mapping ideally
      maybeInsertedRecord match {
        case None | Some(Right(_)) =>
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

    logger.info(s"the result is: <$result>")

    result

  }

}
