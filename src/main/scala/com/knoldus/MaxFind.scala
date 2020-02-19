package com.knoldus


import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


case class UserPostComment(user: User, postWithComments: List[PostWithComments])


object MaxFind {


  def maxFinder(key: String): Future[String] = {
    val userUrl = "https://jsonplaceholder.typicode.com/users"
    val postUrl = "https://jsonplaceholder.typicode.com/posts"
    val commentUrl = "https://jsonplaceholder.typicode.com/posts"

    if (key == "Post") userWithMostPost(userUrl, postUrl) else userWithMostComment(postUrl, commentUrl)

  }


  def userWithMostPost(userUrl: String, postUrl: String): Future[String] = {

    val temp = ClassListAggregator.userPostMaker(userUrl, postUrl)


    def maxPost(futureListUserPost: Future[List[UserWithPost]], max: Future[String]): Future[String] = {

      futureListUserPost.map {
        _ match {
          case _ :: Nil => max
          case first :: second :: rest => if (first.post.length > second.post.length) maxPost(Future {
            first :: rest
          }, Future {
            first.user.name
          }) else maxPost(Future {
            second :: rest
          }, Future {
            second.user.name
          })
        }
      }.flatten
    }

    maxPost(temp, Future {
      " "
    })
  }


  def userWithMostComment(postUrl: String, commentUrl: String): Future[String] = {
    val userUrl = "https://jsonplaceholder.typicode.com/users"
    val futureUsers = Parser.userParser(userUrl)
    val temp = ClassListAggregator.postCommentMaker(postUrl, commentUrl)


    def maxComment(futureListPostComment: Future[List[PostWithComments]], max: Future[Int]): Future[Int] = {

      futureListPostComment.map {
        _ match {
          case _ :: Nil => max
          case first :: second :: rest => if (first.comment.length > second.comment.length) maxComment(Future {
            first :: rest
          }, Future {
            first.post.userId
          }) else maxComment(Future {
            second :: rest
          }, Future {
            second.post.userId
          })
        }
      }.flatten

    }

    val futureMaxPostComment = maxComment(temp, Future {
      0
    })
    futureUsers.map(users => futureMaxPostComment.map(maxPostComment => users.filter(user => user.id == maxPostComment)(0).name)).flatten


    //    futureUsers.map(users=>futureMaxPostComment.map(maxPostComment=>users.filter(_.id==maxPostComment)(0)).)
    //      print(nameOfMaxComment)


  }
}
