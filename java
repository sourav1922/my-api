pipeline{
agent none
environment {
DOCKERHUB_CREDENTIALS=credentials('cba30343-cfdb-4b74-9ddd-518485437254')
}
stages{
stage('Hello'){
agent{
label 'KMaster'
}
steps{
echo 'Hello World'
}
}
stage('git'){
agent{
label 'KMaster'
}
steps{
git'https://github.com/Intellipaat-Training/Test.git'
}
}
stage('docker') {
agent {
label 'KMaster'
}
steps {
sh 'sudo docker build /home/ubuntu/jenkins/workspace/FinalProject -t
docker6767/image'
sh 'sudo echo $DOCKERHUB_CREDENTIALS_PSW | sudo docker login -u
$DOCKERHUB_CREDENTIALS_USR --password-stdin'
sh 'sudo docker push docker6767/image'
}
}
stage('Kubernetes') {
agent {
label 'KMaster'
}
steps {
sh 'kubectl create -f deploy.yml'
sh 'kubectl create -f svc.yml'
}
}
}
}
