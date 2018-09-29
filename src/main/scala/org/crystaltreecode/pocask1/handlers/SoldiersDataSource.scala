package org.crystaltreecode.pocask1.handlers

import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.model.DeleteItemResult
import com.amazonaws.services.dynamodbv2.{AmazonDynamoDB, AmazonDynamoDBClientBuilder}
import com.gu.scanamo.{Table, _}
import com.gu.scanamo.error.DynamoReadError
import com.gu.scanamo.ops.ScanamoOps
import com.gu.scanamo.syntax._

/**
  * Basically a Users data provider: register new, list, retire
 */



// DynamoDB ops

object SoldiersDataSource {

  case class SoldierRecord(userId:String, name:String)
  val rosterTable = Table[SoldierRecord]("Roster")

  val client:AmazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
    .withRegion(Regions.US_EAST_1)
    .withCredentials(new ProfileCredentialsProvider("app-1-development"))
    .build()
//  val client:AmazonDynamoDBClient = new AmazonDynamoDBClient(
//    new AWSCredentialsProviderChain(
//
//      // First we'll check for EC2 instance profile credentials.
//      new InstanceProfileCredentialsProvider(),
//
//      // If we're not on an EC2 instance, fall back to checking for
//      // credentials in the local credentials profile file.
//      new ProfileCredentialsProvider("app-1-development"))

  def listRosterNames(userId:String): List[Either[DynamoReadError, SoldierRecord]] = {
    def getAllOperation:ScanamoOps[List[Either[DynamoReadError, SoldierRecord]]] = rosterTable.query('userId -> userId)
    def results:List[Either[DynamoReadError, SoldierRecord]] = Scanamo.exec(client)(getAllOperation)
    results
  }

  def registerNewSoldier(userId:String, soldierName:String): Option[Either[DynamoReadError, SoldierRecord]] = {
    def putOperation:ScanamoOps[Option[Either[DynamoReadError, SoldierRecord]]] = rosterTable.put(new SoldierRecord(userId, soldierName))
    def results:Option[Either[DynamoReadError, SoldierRecord]] = Scanamo.exec(client)(putOperation)
    results
  }

  def retireSoldier(userId:String, soldierName:String): DeleteItemResult = {
    def putOperation:ScanamoOps[DeleteItemResult] = rosterTable.delete('userId -> userId)
    def results:DeleteItemResult = Scanamo.exec(client)(putOperation)
    results
  }

}
