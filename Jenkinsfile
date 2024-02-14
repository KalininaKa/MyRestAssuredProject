pipeline {
 agent any
 // Означает, что будет выполняется на любом агенте

 tools {
   // Необходимые инструменты
   maven 'maven jenkins'

 }

 stages {
   stage('Checkout') {
     steps {
       // Получение кода из репозитория
       checkout scm
     }
   }

   stage('Build') {
     steps {
       // Сборка проекта с использованием Maven
       script {
         def mavenHome = tool 'maven jenkins'
         // 'maven jenkins' - это идентификатор конфигурации Maven в Jenkins

         sh "${mavenHome}/bin/mvn clean package"
       }
     }
   }

   stage('Test') {
     steps {
       // Запуск теста
       script {
         taf = "${TAG}"
         def mavenHome = tool 'maven jenkins'
         //Запуск тестов с помощью Maven.
         sh "${mavenHome}/bin/mvn test -D groups=${tag}"
       }
     }
   }

   stage('Allure Report') {
    steps {
               // Публикация отчетов Allure
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
