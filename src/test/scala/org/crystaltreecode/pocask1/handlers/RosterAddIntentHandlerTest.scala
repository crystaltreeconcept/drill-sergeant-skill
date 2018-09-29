package org.crystaltreecode.pocask1.handlers

import java.util.Optional

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.model._
import org.crystaltreecode.pocask1.toSpeakTag
import org.scalamock.scalatest.MockFactory
import org.scalatest.FlatSpec
import org.scalatest.prop.Checkers


class RosterAddIntentHandlerTest extends FlatSpec
with Checkers
with MockFactory {

  val user: User = User.builder().withUserId("uniqueUserIdKindOf42").build()
  val session: Session = Session.builder().withUser(user).build()

  /*
  slots should be filled in
    val request = input.getRequestEnvelope.getRequest.asInstanceOf[IntentRequest]
    val slots: util.Map[String, Slot] = request.getIntent.getSlots
   */


  val request: Request = IntentRequest.builder()
    .withIntent(
      Intent.builder()
        .putSlotsItem(
          "RookieName"
          , Slot.builder()
            .withName("RookieName")
            .withValue("Dima")
            .build()
        )
      .build())
    .build()

  "RosterAddIntentHandler" should "handle happy path 1" in {

    val requestEnvelope = RequestEnvelope.builder()
      .withSession(session)
      .withRequest(request)
      .build()
    val handlerInput = HandlerInput.builder().withRequestEnvelope(requestEnvelope).build()
    //(handlerInputMock.isEmpty _).expects().returning(true)
    // ...

    val subj = new RosterAddIntentHandler()

    val result: Optional[Response] = subj.handle(handlerInput)

    assert(result.isPresent)
    assert(result.get.getOutputSpeech.toString.contains(
      toSpeakTag("Ok, I'll make sure we record this Dima")))

  }
}
