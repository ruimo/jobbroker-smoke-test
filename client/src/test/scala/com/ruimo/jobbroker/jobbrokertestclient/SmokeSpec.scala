package com.ruimo.jobbroker.jobbrokertestclient

import scala.annotation.tailrec
import org.specs2.mutable.Specification
import com.ruimo.jobbroker.client.ClientContext
import com.ruimo.jobbroker.dao.{AccountId, ApplicationId, Migration, Request, JobStatus}

class SmokeSpec extends Specification {
  "Smoke test" should {
    "Show login required if no one logged in" in {
      Class.forName("net.sf.log4jdbc.DriverSpy")
      ClientContext().withClient { client =>
        client.doDbMigration()
        client.setAutoCommit(true)
        val req = client.submitJobWithBytes(
          AccountId("acc01"),
          ApplicationId("app01"),
          "Ruimo".getBytes("utf-8"),
        )

        val startTime = System.currentTimeMillis

        @tailrec def getResult: String = {
          val (resultReq: Request, resultBytes: Option[Array[Byte]]) = client.retrieveJobResultWithBytes(req.id)
          if (resultReq.jobStatus != JobStatus.JobEnded) {
            if (System.currentTimeMillis - startTime > 30 * 1000) throw new Error("timeout")
            Thread.sleep(3000)
            getResult
          } else {
            (new String(resultBytes.get, "utf-8"))
          }
        }

        getResult === "Hello, Ruimo"
      }
    }
  }
}

