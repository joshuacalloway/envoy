package digitalenvoy

import org.scalatest.funsuite.AnyFunSuite

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, InputStream, OutputStream}
import digitalenvoy.JsonUtils.{toRecordList}

class MainLambdaTests extends AnyFunSuite {

  // test 1
  test("that we can blacklist an item") {

    val outputStream: OutputStream = new ByteArrayOutputStream()
    MainLambda.run(toInputStream(jsonString), outputStream)
    val recordsFromOutput = toRecordList(outputStream)
    val blacklisted = recordsFromOutput.find(item => item.advertiserId == "blacklisted")
    val notBlacklisted = recordsFromOutput.find(item => item.advertiserId != "blacklisted")

    assert(blacklisted == None)
    assert(notBlacklisted != None)
  }

  test("that we get the right records back") {
    val outputStream: OutputStream = new ByteArrayOutputStream()
    MainLambda.run(toInputStream(jsonString), outputStream)
    val recordsFromOutput = toRecordList(outputStream)
    val notBlacklisted = recordsFromOutput.filter(item => item.advertiserId != "blacklisted")

    assert(notBlacklisted.size == 2)
    val first = notBlacklisted.head
    val second = notBlacklisted.last
    assert(first.advertiserId == "abc")
    assert(first.location.equals(Location("2021-07-01 15:30:00", "40.9594975", "77.3902906")))
    assert(second.location.equals(Location("2021-07-01 15:35:00", "40.8410823", "77.3815885")))

  }

  def toInputStream(strValue: String): InputStream = {
    new ByteArrayInputStream(strValue.getBytes)
  }


  // simulate a json string
  val jsonString = """
  [{
    "advertiserId": "abc",
    "platform": "ios",
    "locations": [
      {"locationAt": "2021-07-01 15:30:00", "latitude" : 40.9594975, "longitude" : 77.3902906 }
      {"locationAt": "2021-07-01 15:35:00", "latitude" : 40.8410823, "longitude" : 77.3815885 }

    ]
  },
  {
    "advertiserId": "blacklisted",
    "platform": "android",
    "locations": [
    ]
  }
  ]
  """

}