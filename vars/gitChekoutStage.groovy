def call (Closure body) {
    stage ('GitCheckout') {
        def config = [:]
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body.delegate = config
        body()
    }
}
    checkout([$class: 'GitSCM',
        branches: [[name: "${config.BranchName}"]],
        doGenerateSubmoduleConfigurations: "${config.SubmoduleConfig}",
        extensions: [],
        submoduleCfg: [],
        userRemoteConfigs: [[credentialsId: "${config.CredentialsID}" , url: "${config.GitRepository}" ]]])
pintln "${config.BranchName}"
