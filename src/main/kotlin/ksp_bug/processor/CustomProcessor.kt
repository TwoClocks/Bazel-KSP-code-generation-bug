package ksp_bug.processor

import com.google.auto.service.AutoService
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.ksp.writeTo
import ksp_bug.annotation.CustomAnnotation


class CustomSymbolProcessor(
  private val codeGenerator: CodeGenerator,
  private val logger: KSPLogger,
  private val options: Map<String, String>,
) : SymbolProcessor {

  val annotationName = CustomAnnotation::class.qualifiedName!!

  override fun process(resolver: Resolver): List<KSAnnotated> {
    logger.info("KSP Called")
    val annoList =
      resolver.getSymbolsWithAnnotation(annotationName).filter { it.validate() }
        .filterIsInstance<KSFunctionDeclaration>().toList()

    if(annoList.isEmpty()) {
      return emptyList()
    }
    val theAnno = annoList.first()
    val pkgName = theAnno.packageName.asString()
    logger.info("package name: $pkgName")

    val fs = FileSpec.builder(pkgName, "GeneratedCode")
    val cs = TypeSpec.classBuilder("GeneratedClass")
      .addProperty(
        PropertySpec.builder("quote", String::class).initializer("%S","Discipline is the obedience to awareness").build()
      )

    fs.addType(cs.build())
    fs.build().writeTo(codeGenerator,false, emptyList())

    return emptyList()
}

override fun finish() {
  logger.info("KSP Finish")
}

override fun onError() {
  logger.error("KSP Error")
}
}

@AutoService(SymbolProcessorProvider::class)
class CustomSymbolProvider : SymbolProcessorProvider {
  override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
    environment.logger.info("Provider created")
    return CustomSymbolProcessor(
      environment.codeGenerator,
      environment.logger,
      environment.options,
    )
  }
}

