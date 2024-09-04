def buildJar() {
    echo 'building the application...'
    sh 'mvn package'
}

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker hub repository', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t cnwagba/jenkins-repo-dockerhub:$IMAGE_NAME .'
        sh 'echo $PASS | docker login -u $USER --password-stdin'
        sh 'docker push cnwagba/jenkins-repo-dockerhub:$IMAGE_NAME'
    }
}

def deployApp() {
    echo 'deploying the application...'
}

return this
