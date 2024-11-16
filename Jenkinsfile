pipeline {
    agent any
    environment {
        DOCKER_IMAGE = 'bicevidatest'
        MAVEN_HOME = '/usr/local/Cellar/maven/3.9.9/libexec'  // Ajusta la ruta a tu instalación de Maven
        TERRAFORM_HOME = '/usr/local/bin'  // Ruta de Terraform
        PATH = "$MAVEN_HOME/bin:$TERRAFORM_HOME:$PATH"  // Asegura que Maven y Terraform estén en el PATH
    }
    stages {
        stage('Construcción') {
            steps {
                script {
                    // Construir el proyecto con Maven
                    sh 'mvn clean install'
                }
            }
        }
        stage('Pruebas') {
            steps {
                script {
                    // Ejecutar pruebas con Maven
                    sh 'mvn test'
                }
            }
        }
        stage('Docker Build & Push') {
            steps {
                script {
                    // Construir la imagen Docker
                    sh 'export PATH=$PATH:/usr/local/bin && docker build -t $DOCKER_IMAGE .'
                }
            }
        }
        stage('Despliegue') {
            steps {
                script {
                    // Eliminar contenedor existente si lo hay
                    sh 'docker rm -f bicevidatest-container || true'

                    // Inicializar Terraform
                    sh 'export PATH=$PATH:/usr/local/bin && terraform init'
                    // Desplegar el contenedor con Terraform
                    sh 'terraform apply -auto-approve'
                }
            }
        }
    }
}