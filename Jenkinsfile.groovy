node{
    properties([parameters([string(defaultValue: 'IP', description: 'Where i build e.g IP', name: 'ENV', trim: true)])])
    stage("Clone repo"){
        git 'https://github.com/farrukh90/Flaskex.git'
    }
    stage("Build application"){
        sh   "scp -r *   ec2-user@${ENV}:/tmp"
        sh "ssh ec2-user@${ENV} sudo   yum install python -pip -y"
        sh "ssh ec2-user@${ENV} sudo pip install -r /tmp/requirements.txt"
    } 
    stage("App Run"){
        sh "ssh ec2-user@${ENV} python /tmp/app.py"
    }
}           