package com.knoldus

import net.liftweb.json._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

case class Address(street: String, suite: String, city: String, zipcode: String, geo: Geo)

case class Geo(lat: String, lng: String)

case class Company(name: String, catchPhrase: String, bs: String)

case class User(id: Int, name: String, username: String, email: String, address: Address, phone: String, website: String, company: Company)

case class Post(userId: Int, id: Int, title: String, body: String)

case class Comment(postId: Int, id: Int, name: String, email: String, body: String)

object Parser {
  implicit val formats = DefaultFormats

  def userParser(userUrl: String): Future[List[User]] = {
    val user = parse(Extractor.userExtractor(userUrl))
    Future(user.children.map(_.extract[User]))
  }

  def postParser(postUrl: String): Future[List[Post]] = {
    val post = parse(Extractor.postExtractor(postUrl))
    Future(post.children.map(_.extract[Post]))
  }

  def commentParser(commentUrl: String): Future[List[Comment]] = {
    val comment = parse(Extractor.commentExtractor(commentUrl))
    Future(comment.children.map(_.extract[Comment]))
  }


}

