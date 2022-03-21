package digitalenvoy

import net.liftweb.json.{JValue, parse}

import java.io.{InputStream, OutputStream}
import scala.io.Source
import net.liftweb.json.Serialization.write

object JsonUtils {
  implicit val formats = net.liftweb.json.DefaultFormats

  def toJson(stream: InputStream): JValue = {
    parse(Source.fromInputStream(stream).mkString)
  }

  def toJson(stream: OutputStream): JValue = {
    parse(stream.toString())
  }

  def toOutputStream(records: List[OutputRecord], outputStream: OutputStream) = {
    outputStream.write( write(records).getBytes())
  }

  def toRecordList(stream: InputStream): List[InputRecord] = {
    toJson(stream).extract[List[InputRecord]]
  }

  def toRecordList(stream: OutputStream): List[OutputRecord] = {
    toJson(stream).extract[List[OutputRecord]]
  }

  def toJson(records: List[InputRecord]): JValue = {
    parse(write(records))
  }

  def toRecordList(jsonString: String) : List[InputRecord] = {
    parse(jsonString).extract[List[InputRecord]]
  }
}
