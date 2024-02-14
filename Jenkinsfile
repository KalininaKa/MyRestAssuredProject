tag = "${TAG}"
pipeline {
 agent any
 // Означает, что будет выполняется на любом агенте

 tools {
   // Необходимые инструменты
   maven 'maven_home'

 }

 stages {
        stage('Checkout Branch') {
               checkout scm
            }


        stage('Build') {
            steps {
            // Сборка проекта с использованием Maven
                script {
                    def mvnHome = tool 'maven_home'
                    sh "${mvnHome}/bin/mvn clean package"
                }
            }
        }
        stage("Run tests") {
            steps{
                script{
                    def mvnHome = tool 'maven_home'
                    sh "${mavenHome}/bin/mvn test -D groups=${tag}"
                }
            }
            runTestWithTag("${tag}")
        }

        stage("Allure") {
            steps{
                allure([
                            includeProperties: true,
                            jdk              : '',
                            properties       : [],
                            reportBuildPolicy: 'ALWAYS',
                            results          : [[path: 'target/allure-results']]
                    ])
            }
        }
 }
}