pipeline {
    agent any
    stages {
    stage('git repo & clean') {
    steps {
        bat "rmdir /s /q student-management"
        bat "git clone https://github.com/mahesht11/student-management.git"
        bat "mvn clean -f student-management"
        }
    }
    stage('install'){
        steps {
        bat "mvn install -f student-management"
        }
    }
    stage('test'){
        steps{
        bat "mvn test -f student-management"
        }
    }
 stage('Sonarqube') {
    steps {
        withSonarQubeEnv('sonarqube') {
            sh "mvn clean package sonar:sonar"
        }
        timeout(time: 10, unit: 'MINUTES') {
            waitForQualityGate abortPipeline: true
        }
    }
}
    stage('package'){
    steps{
        bat "mvn package -f student-management"
        }
    }

    }

}
