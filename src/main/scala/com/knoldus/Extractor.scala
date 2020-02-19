package com.knoldus


import org.apache.commons.io.IOUtils
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder

object Extractor {
  def common(url: String) = {
    val request = new HttpGet(url)
    val client = HttpClientBuilder.create().build()
    val response = client.execute(request)
    IOUtils.toString(response.getEntity().getContent())

  }

  def userExtractor(userUrl: String): String = {
    common(userUrl)
  }

  def commentExtractor(commentUrl: String): String = {

    common(commentUrl)
  }

  def postExtractor(postUrl: String): String = {
    common(postUrl)
  }

}
