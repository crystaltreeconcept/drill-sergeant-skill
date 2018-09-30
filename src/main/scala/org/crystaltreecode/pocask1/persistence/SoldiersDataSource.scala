package org.crystaltreecode.pocask1.persistence

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.model.DeleteItemResult
import com.amazonaws.services.dynamodbv2.{AmazonDynamoDB, AmazonDynamoDBClientBuilder}
import com.gu.scanamo.error.DynamoReadError
import com.gu.scanamo.ops.ScanamoOps
import com.gu.scanamo.query.AttributeNotExists
import com.gu.scanamo.syntax._
import com.gu.scanamo.{Scanamo, Table}


/**
  * Basically a Users data provider: register new, list, retire
 */

// DynamoDB ops

object SoldiersDataSource {

  case class SoldierRecord(userId:String, soldierName:String)

  val rosterTable: Table[SoldierRecord] = Table[SoldierRecord]("Soldiers")

  val client:AmazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
    .withRegion(Regions.US_EAST_1)
    .withCredentials(new DefaultAWSCredentialsProviderChain())
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
    //def getAllOperation:ScanamoOps[Option[Either[DynamoReadError, SoldierRecord]]] = rosterTable.get('userId -> userId and 'soldierName -> "Dima")
    //def getAllOperation:ScanamoOps[List[Either[DynamoReadError, SoldierRecord]]] = rosterTable.scan
    def results:List[Either[DynamoReadError, SoldierRecord]] = Scanamo.exec(client)(getAllOperation).toList
    results
  }

  def registerNewSoldier(userId:String, soldierName:String): Option[Either[DynamoReadError, SoldierRecord]] = {
    def putOperation:ScanamoOps[Option[Either[DynamoReadError, SoldierRecord]]] = rosterTable.put(SoldierRecord(userId, soldierName))
    def results:Option[Either[DynamoReadError, SoldierRecord]] = Scanamo.exec(client)(putOperation)
    results
  }

  def retireSoldier(userId:String, soldierName:String): DeleteItemResult = {
    def putOperation:ScanamoOps[DeleteItemResult] = rosterTable.delete('userId -> userId and 'soldierName ->soldierName)
    def results:DeleteItemResult = Scanamo.exec(client)(putOperation)
    results
  }

}
