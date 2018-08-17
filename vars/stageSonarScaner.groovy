/*
stageSonarScaner

@param sonarName //SonarQube Server installation (Jenkins Config System)
@param sonarJome // SonarScanner installation (Jenkins Global Tool Config )
@param sonarKey //Project key on SonarQube Server
@param sonarProj //Project name on SonarQube Server
@param sonarbinaries //path to binaries
@param sonarSourse //path to src/

@version 1.0
@copyright 2018, EPAM systems, All Rights Reserved
*/

def call (Closure body) {
    stage ('SonarQube Scan') {
        def config = [:]
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body.delegate = config
        body()
    
        withSonarQubeEnv("${config.sonarName}") {
            sh "${config.sonarHome}/bin/sonar-scanner -Dsonar.projectKey=${config.sonarKey} -Dsonar.projectName=${config.sonarProj} -Dsonar.java.binaries=${config.sonarbinaries} -Dsonar.sources=${config.sonarSource}"
        }
    }
}
