task_branch = "${TEST_BRANCH_NAME}" //переменная, которая задается из дженкинса (имя ветки)
tag = "${TAG}" //тег для запуска тестов, задается из дженкинса
stand = "${STAND}" //название стенда для запуска тестов, задается из дженкинса
def branch_cutted = task_branch.contains("origin") ? task_branch.split('/')[1] : task_branch.trim()
//если ветка, которую мы вписали содержит origin, то разделяем слешем и берем часть после слеша, если щкшпшт не содержит то берем как есть
currentBuild.displayName = "$branch_cutted" // то что отображается в дженкинсе в качестве активной джобы, (передаем название ветки)
base_git_url = "https://github.com/KalininaKa/MyRestAssuredProject.git" //откуда проекит

node { // исполнение пайплайна и что в нем должно содержаться
    withEnv(["branch=${branch_cutted}", "base_url=${base_git_url}"])// передаем переменные которые хотим использовать
            {
        stage("Checkout Branch") //Получение кода из репы. Выбираем ветку, если ветка не мастер, то скачиваем проект
                {
            if (!"$branch_cutted".contains("master")) {
                try {
                    getProject("$base_git_url", "$branch_cutted")
                } catch (err) {
                    echo "Failed get branch $branch_cutted" // если скачать не получилось - показать ошибку
                    throw ("${err}")
                }
            } else {
                echo "Current branch is master" //если ветка мастер
            }
        }

        stage("Build") {
            def mvnHome = tool 'maven jenkins'
            echo "${mvnHome}"
            sh "${mvnHome}/bin/mvn clean package"
        } //сборка проекта с помощью maven

        try {
            stage("Run tests") {
                runTestWithTag("${stand}") //запуск тестов с тегом
            }

        } finally {
            stage("Allure") {
                generateAllure() //генерация отчета Allure
            }
        }
    }
}

def runTestWithTag(String stand) {
    try {
        def mvnHome = tool 'maven jenkins'
        //echo "${tag}"
        echo "${stand}"
        sh "${mvnHome}/bin/mvn test -Dbase.host=${stand}"
    } finally {
        echo "some failed tests"
    }
}//запуск тестов с помощью Maven по тегу


def getProject(String repo, String branch) {
    cleanWs()
    checkout scm: [
            $class           : 'GitSCM', branches: [[name: branch]],
            userRemoteConfigs: [[
                                        url: repo
                                ]]
    ]
} //скачиваем проект

def generateAllure() {
    allure([
            includeProperties: true,
            jdk              : '',
            properties       : [],
            reportBuildPolicy: 'ALWAYS',
            results          : [[path: 'target/allure-results']]
    ])
} //генерация отчета