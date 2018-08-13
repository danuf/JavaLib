def call (Map pipelineParams) {
    node {
        checkout([$class: 'GitSCM', branches: [[name: pipelineParams.BranchName]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: pipelineParams.CredentialsId , url: pipelineParams.GitRepository ]]])
    }
}
