package digitalenvoy

import digitalenvoy.JsonUtils.{toOutputStream, toRecordList}

import java.io.InputStream
import java.io.OutputStream

object MainLambda {
  def run(is: InputStream, os: OutputStream): Unit = {
    val notBlackListed = toRecordList(is).filter(item => isNotBlackListed(item))
    val outputRecords = notBlackListed.flatMap(item => toOutputRecord(item))
    toOutputStream(outputRecords, os)
  }

  def isNotBlackListed(record: InputRecord) : Boolean = {
    !"blacklisted".equalsIgnoreCase(record.advertiserId)
  }

  def toOutputRecord(item: InputRecord) : List[OutputRecord]= {
    item.locations.map( location =>
      OutputRecord(item.advertiserId, item.platform,
        Location(location.locationAt, location.latitude, location.longitude)))
  }
}
