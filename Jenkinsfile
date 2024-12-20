pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'votre-utilisateur-docker/nom-image:latest'  // Définissez votre image Docker ici
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-credentials') // Utilisez vos identifiants DockerHub configurés dans Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                // Récupérer le code depuis le repository Git
                git 'https://github.com/yassir5236/ITLens'
            }
        }

        stage('Build') {
            steps {
                // Compiler le projet avec Maven
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                // Lancer les tests unitaires avec Maven
                sh 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    // Analyser la qualité du code avec SonarQube
                    withSonarQubeEnv('SonarQube') {
                        sh 'mvn sonar:sonar'
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                // Créer une image Docker
                script {
                    sh 'docker build -t $DOCKER_IMAGE .'
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                // Pousser l'image Docker vers Docker Hub
                script {
                    docker.withRegistry('', 'DOCKERHUB_CREDENTIALS') {
                        sh 'docker push $DOCKER_IMAGE'
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline terminé avec succès.'
        }
        failure {
            echo 'Pipeline échoué.'
        }
    }
}
