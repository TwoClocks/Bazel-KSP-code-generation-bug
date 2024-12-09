
package ksp_bug.app
import ksp_bug.annotation.CustomAnnotation

@CustomAnnotation
fun main(args: Array<String>) {
  println("Hello world")

  val genCode = GeneratedClass()
  println("quote: ${genCode.quote}")
}
