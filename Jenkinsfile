pipeline {
    agent any

    environment {
        DOCKER_CREDENTIALS_ID = 'dockerhub-credentials' // Change this to match your Jenkins Docker Hub credentials
        IMAGE_NAME = 'my-spring-boot-app' // Customize the image name
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/your-repo/spring-boot-app.git' // Change this URL
            }
        }

        stage('Build') {
            steps {
                script {
                    // Run Maven build inside a Docker container
                    docker.image('maven:3.8.1-jdk-11').inside {
                        sh 'mvn clean package'
                    }
                }
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKER_CREDENTIALS_ID) {
                        def app = docker.build("${IMAGE_NAME}:latest")
                        app.push('latest')
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    docker.image("${IMAGE_NAME}:latest").run("-p 8081:8081")
                }
            }
        }
    }

    post {
        always {
            cleanWs() // Clean the workspace after the build
        }
    }
}
