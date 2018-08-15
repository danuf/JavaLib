import com.lab.build.Colorizer

/*
stageMavenBuild
 
Execute Maven commands (build, upload, etc) with custom parameters
 
@param jdkVersion
@param mavenVersion
@param jvmOptions
@param mavenCommand
 
@version 1.0
@copyright 2018, EPAM systems, All Rights Reserved
*/

def call(Closure body) {
    stage ('Execute Maven') {
        def config = [:]
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body.delegate = config
        body()

        withMaven(
            jdk: "${config.jdkVersion}",
            maven: "${config.mavenVersion}",
            mavenOpts: "${config.jvmOptions}"
        ){
            sh "${config.mavenCommand}"
        }
    }
}
