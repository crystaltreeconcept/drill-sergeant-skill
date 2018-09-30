package org.crystaltreecode.pocask1.handlers

import java.util.Optional

import com.amazon.ask.dispatcher.request.handler.{HandlerInput, RequestHandler}
import com.amazon.ask.model.{IntentRequest, Response}
import com.amazon.ask.request.Predicates.intentName
import com.gu.scanamo.error.DynamoReadError
import org.apache.logging.log4j.{LogManager, Logger}
import org.crystaltreecode.pocask1.persistence.SoldiersDataSource


/**
  * serves the 'list the users' request
  *   doesn't keep the session open, so no need to re-prompt, don't set
  *
  */
class RosterListIntentHandler extends RequestHandler {

  val logger: Logger = LogManager.getLogger(classOf[RosterListIntentHandler])

  def canHandle(input: HandlerInput): Boolean =
    input.matches(intentName("RosterListIntent"))

  def handle(input: HandlerInput): Optional[Response] = {

    logger.info("going to handle " + input.toString + " for user : " + input.getRequestEnvelope.getSession.getUser.getUserId)

    val listUsersResult:List[Either[DynamoReadError, SoldiersDataSource.SoldierRecord]]
          = SoldiersDataSource.listRosterNames(input.getRequestEnvelope.getSession.getUser.getUserId)

    logger.info(s"listUsersResult is <$listUsersResult>")

    //TODO: process and log the errors properly

    val soldierNames:List[String] = listUsersResult.collect({
      case Right(soldier) => {
        logger.info(s"found soldier: <${soldier.soldierName}>")
        soldier.soldierName
      }
    })
    val errors:List[String] = listUsersResult.collect({
      case Left(error) => {
        logger.info(s"found error: <${error}>")
        error.toString
      }
    })

    val speechText = s"Your roster for today is: ${soldierNames.mkString(", ")}"
    val cardText:String = if (errors.isEmpty) speechText else errors.mkString(", ")
    logger.info(s"readying speech: <${speechText}>, with card text: <${cardText}>")

    val result = input
      .getResponseBuilder
      .withSpeech(speechText)
      .withSimpleCard("DrillSergeant", cardText)
      .withShouldEndSession(false)
      .build

    logger.info(s"OK, and the winner is: <${result}>")

    result
  }
}
