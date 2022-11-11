pipeline {
    agent  any
    tools
        {
            maven 'M2_HOME'
        }
    environment{
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "172.10.0.140:8081/"
        NEXUS_REPOSITORY = "maven_releases"
        NEXUS_CREDENTIAL_ID = "nexus_user_credentials"
        dockerhub = credentials('dockerhub')
    }
    stages {
        stage('Pull code from GitHub') {
            steps {
                echo 'Pulling code from Git'
                git branch: 'ahmedBannour',
                url: 'https://github.com/ferdaws99/TpAchatProject.git'
            }
        }

        stage('mvn clean install') {
        steps {
                echo 'Artifact construction ...'
                sh 'mvn clean install -DskipTests'
            }
    }
    stage('Test with Juint') {
            steps {
                echo 'Testing Facture'
                sh 'mvn test'
            }
        }
    stage('SonarQube') {
        steps {
                echo 'Sonar check ...'
                sh 'mvn sonar:sonar -Dsonar.sources=src/main/java -Dsonar.java.binaries=target/classes -Dsonar.css.node=. -Dsonar.host.url=http://172.10.0.140:9000 -Dsonar.projectKey=tn.esprit.spring -Dsonar.login=admin -Dsonar.password=faker1g1337'
            }
    }
    stage('Nexus deploy jar') {
        steps {
                echo 'Nexus ...'
                sh 'mvn deploy:deploy-file -DgroupId=com.esprit.examen -DartifactId=tpAchatProject -Dversion=1.0 -DgenerationPom=true -Dpackaging=jar -DrepositoryId=deploymentRepo -Durl=http://172.10.0.140:8081/repository/maven-releases -Dfile=target/tpAchatProject-1.0.jar'
            }
    }
    stage('Connect to Docker Hub') {
                steps
                {
                sh "docker login -u $dockerhub_USR -p $dockerhub_PSW"
                }
                post
                {
                    success
                    {
                        echo 'Docker Hub Login Completed !'
                    }
                }
        }
    stage ('Build Image - Docker'){
            steps
            {
               echo 'Starting build Docker image'
                sh "docker build -t raziel1337/devopspoject:1.0.SNAPSHOT ."
            }
            post
            {
                success
                {
                    echo 'Image Build success !'
                }
            }
        }

        stage ('Pushing Image to Docker Hub'){
            steps
            {
                sh "docker push raziel1337/devopspoject"
            }
            post
            {
                success
                {
                    echo 'Image Pushed to Docker hub succeeded !'
                }
            }
        }
    }
}
