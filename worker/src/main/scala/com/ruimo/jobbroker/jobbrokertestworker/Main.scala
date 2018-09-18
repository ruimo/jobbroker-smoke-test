package com.ruimo.jobbroker.jobbrokertestworker

import anorm._

import com.ruimo.jobbroker.client.ClientContext
import com.ruimo.jobbroker.dao.{AccountId, ApplicationId, Migration, Request}

object Main {
  def main(args: Array[String]) {
    ClientContext().withClient { client =>
      Thread.sleep(5000)
      Class.forName("net.sf.log4jdbc.DriverSpy")
      client.doDbMigration()
      client.setAutoCommit(true)
      println("Worker waiting request...")

      val handle = client.retrieveJobWithBytes(
        onJobObtained = (req, bytes) => {
          val name = new String(bytes, "utf-8")
          println("Worker received request '" + name + "'")
          client.storeJobResultWithBytes(
            req.id, ("Hello, " + name).getBytes("utf-8")
          )
          println("Worker stored result.")
        },
        onCancel = () => {
          println("Unexpected cancel request.")
        },
        onError = (jobId, exception) => {
          exception.printStackTrace()
        }
      )

      while (true)
        Thread.sleep(1000)
    }
  }
}
