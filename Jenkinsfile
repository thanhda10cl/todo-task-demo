pipeline {

    agent any

    tools {
        maven 'nd-maven'
    }
    environment {
        MYSQL_ROOT_LOGIN = credentials('mysql-root')
    }
    stages {

        stage('Build with Maven') {
            steps {
                sh 'mvn --version'
                sh 'java -version'
                sh 'mvn clean package -Dmaven.test.failure.ignore=true'
            }
        }

        stage('Packaging/Pushing imagae') {

            steps {
                withDockerRegistry(credentialsId: 'dockerhub', url: 'https://index.docker.io/v1/') {
                    sh 'docker build -t thanhda10cl/springboot .'
                    sh 'docker push thanhda10cl/springboot'
                }
            }
        }

        stage('Deploy MySQL to DEV') {
            steps {
                echo 'Deploying and cleaning'
                sh 'docker image pull mysql:8.0'
                sh 'docker network create dev || echo "this network exists"'
                sh 'docker container stop khalid-mysql || echo "this container does not exist" '
                sh 'echo y | docker container prune '
                sh 'docker volume rm thanhda10cl-mysql-data || echo "no volume"'

                sh "docker run --name thanhda10cl-mysql --rm --network dev -v khalid-mysql-data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_LOGIN_PSW} -e MYSQL_DATABASE=db_task  -d mysql:8.0 "
                sh 'sleep 20'
                sh "docker exec -i thanhda10cl-mysql mysql --user=root --password=${MYSQL_ROOT_LOGIN_PSW} < script"
            }
        }

        stage('Deploy Spring Boot to DEV') {
            steps {
                echo 'Deploying and cleaning'
                sh 'docker image pull thanhda10cl/springboot'
                sh 'docker container stop thanhda10cl-springboot || echo "this container does not exist" '
                sh 'docker network create dev || echo "this network exists"'
                sh 'echo y | docker container prune '

                sh 'docker container run -d --rm --name thanhda10cl-springboot -p 8081:8080 --network dev thanhda10cl/springboot'
            }
        }

    }
    post {
        // Clean after build
        always {
            cleanWs()
        }
    }
}