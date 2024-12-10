package ksp_bug.app

import ksp_bug.annotation.CustomAnnotation

@CustomAnnotation
fun main(args: Array<String>) {
  println("Hello world")

  // IntelliJ and Bazel can run this code fine,
  // but IntelliJ can not find this class or the source
  // code for it. It shows up as a red squiggly line.
  // "Unresolved reference: GeneratedClass"
  val genCode = GeneratedClass()
  println("quote: ${genCode.quote}")
}
