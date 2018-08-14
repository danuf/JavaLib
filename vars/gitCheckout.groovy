// describe parameters here

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
