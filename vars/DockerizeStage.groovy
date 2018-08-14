def call (Closure body) {
    stage('Dockerize Stage') {
        def config = [:]
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body.delegate = config
        body()

        docker.withTool("${config.DockerName}") {
            docker.build("${config.DockerImageName}${env.BUILD_ID}","-f Dockerfile ./")
            docker.image("${config.DockerImageName}${env.BUILD_ID}").run(" -p ${config.DockerTo}:${config.DockerFrom} --name ${config.DockerContainerName}")
        }
    }
}