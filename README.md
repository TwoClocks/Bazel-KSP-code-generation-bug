# Bazel-KSP-code-generation-bug

Simple bazel project that generates a snippit of code via KSP. It uses `rules_kotlin` and the `kt_ksp_plugin` target type.

The code runs, both in IntelliJ and on the cmdline using `bazel run //:Main`.

The generated code code ends up in `bazel-bin/Main-ksp-kt-gensrc.jar`.

IntelliJ can not seem to find the generated code and include it in the project. So the `Main.kt` file has unresolved references to the generated code.

This is true both with google's bazel plugin, and JetBrain's new Bazel plugin that currently EAP.

