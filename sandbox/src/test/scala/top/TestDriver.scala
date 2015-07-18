package top

import org.specs2.mutable._

class TestDriver extends Specification {

  "Driver" should {
    "test" in {
      Driver.showHello mustEqual "Hello, World!"
    }
  }
}
