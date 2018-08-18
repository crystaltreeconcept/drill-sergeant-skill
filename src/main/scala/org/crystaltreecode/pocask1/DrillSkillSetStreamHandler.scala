package org.crystaltreecode.pocask1

import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.{Skill, SkillStreamHandler, Skills}
import org.crystaltreecode.pocask1.handlers._

object skillSetup {

  val handlers: List[RequestHandler] = List(
    new CancelAndStopIntentHandler
    , new DrillSergeantIntentHandler
    , new FallbackIntentHandler
    , new HelpIntentHandler
    , new LaunchRequestHandler
    , new SessionEndedRequestHandler
    , new PersonalizedReportingInIntentHandler
  )

  def skills: Skill = {
    println("Building up the skills")
    Skills.standard.addRequestHandlers(handlers:_*).build
  }
}

class DrillSkillSetStreamHandler (skill: Skill) extends SkillStreamHandler(skill) {
  def this() = this (skillSetup.skills)
}
