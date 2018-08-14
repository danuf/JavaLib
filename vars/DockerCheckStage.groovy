def call (Closure body) {
    stage('Docker Check Stage') {
        def config = [:]
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body.delegate = config
        body()
        def response 
        timeout (time: config.TimeOutCheck, unit:'SECONDS') { 
            echo 'Check Successful docker container Up'
            sleep 10
            while(response!="HTTP/1.1 200") {
                def curl = "curl -I ${config.ApplicationIP}".execute().text
                response = curl[0..11]
		println response
            }    
        }
    }
return response
}
