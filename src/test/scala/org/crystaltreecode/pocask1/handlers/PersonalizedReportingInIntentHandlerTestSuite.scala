package org.crystaltreecode.pocask1.handlers

import java.util.Optional

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.model.{RequestEnvelope, Response}
import org.crystaltreecode.pocask1._
import org.scalamock.scalatest.MockFactory
import org.scalatest.FlatSpec
import org.scalatest.prop.Checkers


class PersonalizedReportingInIntentHandlerTestSuite
  extends FlatSpec
    with Checkers
    with MockFactory {

  "PersonalizedReportingInIntentHandler" should "simple process with any rookie name" in {

    val requestEnvelope = RequestEnvelope.builder().build()
    val handlerInput = HandlerInput.builder().withRequestEnvelope(requestEnvelope).build()
    //(handlerInputMock.isEmpty _).expects().returning(true)
    // ...

    val subj = new PersonalizedReportingInIntentHandler()

    val result: Optional[Response] = subj.handle(handlerInput)

    assert(result.isPresent)
    assert(result.get.getOutputSpeech.toString.contains(
      toSpeakTag("still nothing new")))

  }

}
