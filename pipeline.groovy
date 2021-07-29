pipeline {
    agent any

    stages {
        stage('Checkout'){
            steps{
                git branch: 'main', url: 'https://github.com/jonasrin/jgsu-spring-petclinic.git'
            }
        }
        stage('Build') {
            steps {
                
                sh "./mvnw -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
