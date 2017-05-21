apply {
    plugin("org.jetbrains.kotlin.jvm")
    plugin("org.jetbrains.kotlin.plugin.spring")
    plugin("org.jetbrains.kotlin.plugin.noarg")
//    plugin("org.jetbrains.kotlin.plugin.jpa")
    plugin("io.spring.dependency-management")
}

//noArg {
//    annotation("com.my.Annotation")
//}

dependencies {
    compile(kotlinModule("stdlib-jre8"))
    compile(kotlinModule("reflect"))
}
