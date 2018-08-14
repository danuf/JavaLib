/*
@param SonarName //SonarQube Server installation (Jenkins Config System)
@param SonarJome // SonarScanner installation (Jenkins Global Tool Config )
@param SonarKey //Project key on SonarQube Server
@param SonarProj //Project name on SonarQube Server
@param Sonarbinaries //path to binaries
@param SonarSourse //path to src/
*/

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
