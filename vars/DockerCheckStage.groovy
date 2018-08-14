def call (Closure body) {
    stage('Docker Check Stage') {
        def config = [:]
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body.delegate = config
        body()
        def Response
        timeout (time: "${config.TimeOutCheck}", unit:'SECONDS') { 
            echo 'Check Successful docker container Up'
            sleep 10
            while(Response!="HTTP/1.1 200") {
                def Curl = "curl -I ${config.ApplicationIP}".execute().text
                Response = Curl[0..11]
            }    
        }
    }
}