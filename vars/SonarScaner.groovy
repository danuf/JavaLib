def call (Closure body) {
    stage ('SonarQube Scan') {
        def config = [:]
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body.delegate = config
        body()
    
        withSonarQubeEnv("${config.SonarName}") {
            sh "${config.SonarHome}/bin/sonar-scanner ${config.SonarKey} ${config.SonarProj} ${config.Sonarbinaries} ${config.SonarSource}"
        }

    }
}
