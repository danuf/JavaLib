/*
stageDockerize

Execute docker comands (build and run) with custom parameters

@param dockerName // Settings (Global Tool Config)
@param dockerImageName // Your image's name
@param dockerOutbound // Port on your host machine
@param dockerInbound // Port on your container
@param dockerContainerName // Your container's name

@version 1.0
@copyright 2018, EPAM systems, All Rights Reserved
*/

def call (Closure body) {
    stage('Dockerize Stage') {
        def config = [:]
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body.delegate = config
        body()

        docker.withTool("${config.dockerName}") {
            docker.build("${config.dockerImageName}${env.BUILD_ID}","-f Dockerfile ./")
            docker.image("${config.dockerImageName}${env.BUILD_ID}").run(" -p ${config.dockerPortOutbound}:${config.dockerPortInbound} --name ${config.dockerContainerName}")
        }
    }
}
