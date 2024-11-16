terraform {
  required_providers {
    docker = {
      source  = "kreuzwerker/docker"
      version = "~> 3.0"
    }
  }
}

provider "docker" {}

# Crear la imagen Docker
resource "docker_image" "bicevidatest" {
  name = "bicevidatest:latest" # Aquí se asigna una etiqueta, como "latest"
  build {
    context    = "."
    dockerfile = "Dockerfile"
  }
}

# Crear un contenedor Docker que ejecute la aplicación
resource "docker_container" "bicevidatest_container" {
  name  = "bicevidatest-container"
  image = docker_image.bicevidatest.name  # Referir la imagen por su nombre
  restart = "always"
  ports {
    internal = 8081
    external = 8081
  }
}