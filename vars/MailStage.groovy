def call(Closure body) {
    stage ('Send Email') {
        def config = [:]
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body.delegate = config
        body()
        if(config.Check.equals("HTTP/1.1 200")) {
            emailext(subject: "${env.JOB_NAME} was " + "${BUILD_STATUS}", body: "Commit short hash " + "${shortCommit}", to: "${config.Recipient}", replyTo: '');
        }else {
            System.exit(1)
        }
    }
}
