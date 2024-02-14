task_branch = "${TEST_BRANCH_NAME}"
tag = "${TAG}"
def branch_cutted = task_branch.contains("origin") ? task_branch.split('/')[1] : task_branch.trim()
currentBuild.displayName = "$branch_cutted"
base_git_url = "https://github.com/KalininaKa/MyRestAssuredProject.git"

node {def mvnHome = tool 'maven_home'
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
            }
        }
                    try {
                        stage("Run tests") {
                            echo "${mvnHome}"
                            runTestWithTag("${tag}")
                        }
                    } finally {
                        stage("Allure") {
                            generateAllure()
                        }
                    }
        }
    }

       /* try {
            parallel getTestStages(["apiTests", "uiTests"])
        } finally {
            stage ("Allure") {
                generateAllure()
            }
        }*/

//        try {
//            stage("Run tests") {
//                parallel(
//                        'Api Tests': {
//                            runTestWithTag("apiTests")
//                        },
//                        'Ui Tests': {
//                            runTestWithTag("uiTests")
//                        }
//                )
//            }
//        } finally {
//            stage("Allure") {
//                generateAllure()
//            }
//        }
 /*       stage('Build'){
            steps {
                sh "mvn package -DskipTests"
            }
        }

    }
}


/* def getTestStages(testTags) {
    def stages = [:]
    testTags.each { tag ->
        stages["${tag}"] = {
            runTestWithTag(tag)
        }
    }
    return stages
} */


def runTestWithTag(String tag) {
    try {
        labelledShell(label: "Run ${tag}", script: "mvn test -D groups=${tag}")
    } finally {
        echo "some failed tests"
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

def generateAllure() {
    allure([
            includeProperties: true,
            jdk              : '',
            properties       : [],
            reportBuildPolicy: 'ALWAYS',
            results          : [[path: 'target/allure-results']]
    ])
}
