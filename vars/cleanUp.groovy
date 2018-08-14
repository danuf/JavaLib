def call (Closure body) {
    stage('cleanup Stage') {
        def config = [:]
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body.delegate = config
        body()
                
		sh "docker rm -f ${config.DockerContainerName}"
                sh "docker rmi ${config.DockerImageName}${env.BUILD_ID} > /dev/null 2>&1"
                sh "git clean -ffdx"
    }
}
