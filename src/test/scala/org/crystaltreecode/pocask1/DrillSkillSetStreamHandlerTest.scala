package org.crystaltreecode.pocask1

import java.io.{InputStream, OutputStream}

import com.amazonaws.services.lambda.runtime.Context
import org.scalatest.FunSuite

class DrillSkillSetStreamHandlerTest extends FunSuite {

//  val input: InputStream = ???
//
//  val output: OutputStream = ???
//
//  val context: Context = ???


  test("DrillSkillSetStreamHandler can initialize - check run-time dependencies") {
    val subj = new DrillSkillSetStreamHandler
    val skills = skillSetup.skills

//    subj.handleRequest(input, output, context);

  }
}
