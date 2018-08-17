/*
stageGitCheckout

@param branchName // Name of targeted branch
@param submoduleConfig // True or false
@param credentialsID // Your credentials
@param gitRepository // Link to your repo

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
            branches: [[name: "${config.branchName}"]],
            doGenerateSubmoduleConfigurations: false,
            extensions: [[$class: 'RelativeTargetDirectory',
                relativeTargetDir: "${config.RemoteTargetDir}"]],
            submoduleCfg: [],
            userRemoteConfigs: [[
                credentialsId: "${config.credentialsID}" , 
                url: "${config.gitRepository}" 
            ]]
        ])
        env.shortCommit = sh(returnStdout: true, script: "git log -n 1 --pretty=format:\'%h\'").trim()
    }
}
