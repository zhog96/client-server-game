package com.game.multiplayer.processor

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.*
import java.io.OutputStream
import kotlin.properties.Delegates

internal class SynchronizedDtoProcessor(
    val codeGenerator: CodeGenerator,
    val logger: KSPLogger
) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation(checkNotNull(Synchronized::class.qualifiedName))
        symbols.filter {
            (it as? KSClassDeclaration)?.classKind == ClassKind.INTERFACE
        }.forEach { it.accept(NetworkConfigVisitor(), Unit) }

        return emptyList()
    }

    inner class NetworkConfigVisitor : KSVisitorVoid() {
        private var file by Delegates.notNull<OutputStream>()

        override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
            val properties = classDeclaration.getAllProperties()
            val packageName = classDeclaration.containingFile!!.packageName.asString()
            val className = "Client${classDeclaration.simpleName.asString()}"

            file = codeGenerator.createNewFile(
                Dependencies(
                    false,
                    classDeclaration.containingFile!!
                ),
                packageName,
                className
            )

            file.appendText("package $packageName\n\n")
            file.appendText("class $className() {\n")
            file.appendText("}")
            file.close()
        }
    }
}

internal class SynchronizedDtoProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return SynchronizedDtoProcessor(environment.codeGenerator, environment.logger)
    }
}