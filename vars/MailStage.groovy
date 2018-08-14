def call(Closure body) {
    stage ('Send Email') {
	def response = DockerCheckStage {}
        def config = [:]
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body.delegate = config
        body()
        
        if(response.equals("HTTP/1.1 200")) {
            emailext(subject: "${env.JOB_NAME} was " + "${BUILD_STATUS}", body: "Commit short hash " + "${shortCommit}", to: "${config.Recipient}", replyTo: '');
        }else {
            System.exit(1)
        }
    }
}
