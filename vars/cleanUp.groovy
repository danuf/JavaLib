/*
cleanUp

@param DockerContainerName 
@param DockerImageName

@version 1.0
@copyright 2018, EPAM systems, All Rights Reserved

*/
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
