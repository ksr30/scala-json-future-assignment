package com.knoldus

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

case class UserWithPost(user: User, post: List[Post])

case class PostWithComments(post: Post, comment: List[Comment])

object ClassListAggregator {

  def userPostMaker(userUrl: String, postUrl: String): Future[List[UserWithPost]] = {
    val futureUsers = Parser.userParser(userUrl)
    val futurePosts = Parser.postParser(postUrl)

    futureUsers.map(users => futurePosts.map(posts => users.map(user => UserWithPost(user, posts.filter(user.id == _.userId))))).flatten
  }

  def postCommentMaker(postUrl: String, commentUrl: String): Future[List[PostWithComments]] = {
    val futurePosts = Parser.postParser(postUrl)
    val futureComments = Parser.commentParser(commentUrl)
    futurePosts.map(posts => futureComments.map(comments => posts.map(post => PostWithComments(post, comments.filter(post.id == _.postId))))).flatten
  }


}


//((user=>UserWithPost(user,posts.filter(user.id==_.userId)))