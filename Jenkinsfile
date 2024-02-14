task_branch = "${TEST_BRANCH_NAME}"
tag = "${TAG}"
def branch_cutted = task_branch.contains("origin") ? task_branch.split('/')[1] : task_branch.trim()
currentBuild.displayName = "$branch_cutted"
base_git_url = "https://github.com/KalininaKa/MyRestAssuredProject.git"

pipeline {
 agent any
 // Означает, что будет выполняется на любом агенте

 tools {
   // Необходимые инструменты
   maven 'maven_home'

 }

 stages {
        stage("Checkout Branch") {
            branch="${branch_cutted}"
            base_url="${base_git_url}"
               if (!"$branch_cutted".contains("master")) {
                   try {
                       getProject("$base_git_url", "$branch_cutted")
                   } catch (err) {
                       echo "Failed get branch $branch_cutted"
                       throw ("${err}")
                   }
               } else {
                   echo "Current branch is master"
                   echo "${base_git_url}"
                   echo "${branch_cutted}"
               }
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

def getProject(String repo, String branch) {
    cleanWs()
    checkout scm: [
            $class           : 'GitSCM', branches: [[name: branch]],
            userRemoteConfigs: [[
                                        url: repo
                                ]]
    ]
}
}