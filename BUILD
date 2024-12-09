load("@rules_kotlin//kotlin:core.bzl", "define_kt_toolchain", "kt_javac_options", "kt_kotlinc_options")
load("@rules_kotlin//kotlin:lint.bzl", "ktlint_config", "ktlint_fix", "ktlint_test")
load("@rules_kotlin//kotlin:jvm.bzl", "kt_jvm_binary", "kt_jvm_library", "kt_jvm_test")
load("@rules_kotlin//kotlin:core.bzl", "kt_ksp_plugin")

kt_kotlinc_options(
	name = "kotlinc_options",
	warn = "error",
)
define_kt_toolchain(
	name = "ktools",
	api_version = "2.0",  # "1.1", "1.2", "1.3", "1.4", "1.5" "1.6", "1.7", "1.8", or "1.9"
	jvm_target = "21",  # "1.6", "1.8", "9", "10", "11", "12", "13", "15", "16", "17", "18", "19", "20" or "21"
	language_version = "2.0",  # "1.1", "1.2", "1.3", "1.4", "1.5" "1.6", "1.7", "1.8", or "1.9"
	kotlinc_options = ":kotlinc_options",
	#    visibility = ["//visibility:public"],
)

kt_jvm_library(
    name = "annotation",
    srcs = ["src/main/kotlin/ksp_bug/annotation/CustomAnnotation.kt"],
)

kt_jvm_binary(
	name = "Main",
	srcs = ["src/main/kotlin/ksp_bug/app/Main.kt"],
	main_class = "ksp_bug.app.MainKt",
	deps = [":annotation"],
	plugins = [":custom_processor"],
)

kt_jvm_library(
    name = "processor_lib",
    srcs = ["src/main/kotlin/ksp_bug/processor/CustomProcessor.kt"],
    plugins = [":autoservice"],
    deps = [
        "@maven//:com_google_auto_service_auto_service_annotations",
        "@maven//:com_google_devtools_ksp_symbol_processing",
        "@maven//:com_squareup_kotlinpoet_jvm",
        "@maven//:com_squareup_kotlinpoet_ksp",
        "@maven//:org_jetbrains_kotlin_kotlin_reflect",
        ":annotation"
    ],
)

kt_ksp_plugin(
    name = "custom_processor",
    processor_class = "ksp_bug.processor.CustomSymbolProvider",
    # target_embedded_compiler = True,
    deps = [
        ":processor_lib",
    ],
)

kt_ksp_plugin(
    name = "autoservice",
    processor_class = "dev.zacsweers.autoservice.ksp.AutoServiceSymbolProcessor$Provider",
    deps = [
        "@maven//:com_google_auto_service_auto_service_annotations",
        "@maven//:dev_zacsweers_autoservice_auto_service_ksp",
    ],
)



