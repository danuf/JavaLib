/*
stageDockerize

Execute docker comands (build and run) with custom parameters

@param DockerName // Settings (Global Tool Config)
@param DockerImageName // Your image's name
@param DockerOutbound // Port on your host machine
@param DockerInbound // Port on your container
@param DockerContainerName // Your container's name

@version 1.0
@copyright 2018, EPAM systems, All Rights Reserved
*/

def call (Closure body) {
    stage('Dockerize Stage') {
        def config = [:]
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body.delegate = config
        body()

        docker.withTool("${config.DockerName}") {
            docker.build("${config.DockerImageName}${env.BUILD_ID}","-f Dockerfile ./")
            docker.image("${config.DockerImageName}${env.BUILD_ID}").run(" -p ${config.DockerPortOutbound}:${config.DockerPortInbound} --name ${config.DockerContainerName}")
        }
    }
}
