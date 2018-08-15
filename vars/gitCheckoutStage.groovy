/*
gitCheckoutStage

@param BranchName // Name of targeted branch
@param SubmoduleConfig // True or false
@param CredentialsID // Your credentials
@param GitRepository // Link to your repo

@version 1.0
@copyright 2018, EPAM systems, All Rights Reserved

*/

def call (Closure body) {
    stage ('GitCheckout') {
        def config = [:]
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body.delegate = config
        body()
        
        checkout([$class: 'GitSCM',
            branches: [[name: "${config.BranchName}"]],
            doGenerateSubmoduleConfigurations: config.SubmoduleConfig,
            extensions: [],
            submoduleCfg: [],
            userRemoteConfigs: [[
                credentialsId: "${config.CredentialsID}" , 
                url: "${config.GitRepository}" 
            ]]
        ])
        env.shortCommit = sh(returnStdout: true, script: "git log -n 1 --pretty=format:\'%h\'").trim()
    }
}
