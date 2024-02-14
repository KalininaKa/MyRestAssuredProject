task_branch = "${TEST_BRANCH_NAME}"
tag = "${TAG}"
def branch_cutted = task_branch.contains("origin") ? task_branch.split('/')[1] : task_branch.trim()
currentBuild.displayName = "$branch_cutted"
base_git_url = "https://github.com/KalininaKa/MyMavenProject.git"


node {
    withEnv(["branch=${branch_cutted}", "base_url=${base_git_url}"]) {
        stage("Checkout Branch") {
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

        def mvnHome = tool 'maven jenkins'

       /* stage("Build") {
                // Сборка проекта с использованием Maven
                echo "${mvnHome}"
                sh "${mvnHome}/bin/mvn clean package"
        }*/

        try {
            stage("Run tests") {
                echo "${tag}"
                sh "${mavenHome}/bin/mvn clean test -D groups=${tag}"
            }
        } finally {
            stage("Allure") {
                generateAllure()
            }
        }



    }//withEnv
}//node


/*def runTestWithTag(String tag) {
    try {
        echo "${tag}"
        sh "${mavenHome}/bin/mvn clean test -D groups=${tag}"
    } finally {
        echo "some failed tests"
    }
}*/

def getProject(String repo, String branch) {
    cleanWs()
    checkout scm: [
            $class           : 'GitSCM', branches: [[name: branch]],
            userRemoteConfigs: [[
                                        url: repo
                                ]]
    ]
}

def generateAllure() {
    allure([
            includeProperties: true,
            jdk              : '',
            properties       : [],
            reportBuildPolicy: 'ALWAYS',
            results          : [[path: 'target/allure-results']]
    ])
}