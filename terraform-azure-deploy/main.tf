terraform {
  required_providers {
    azurerm = {                  
      source  = "hashicorp/azurerm"
      version = "~> 3.0"
    }
  }
}

provider "azurerm" {
  features {}
}

# Resource Group
resource "azurerm_resource_group" "springboot_group" {
  name     = "springboot"
  location = "eastus"
}

# App Service Plan
resource "azurerm_service_plan" "spring-boot-project-plan" {
  name                = "my-app-service-plan"
  resource_group_name = azurerm_resource_group.springboot_group.name
  location            = azurerm_resource_group.springboot_group.location

  os_type             = "Linux"        # Specify OS type (Linux or Windows)
  sku_name            = "S1"           # SKU (Standard S1 tier)
}

# App Service
resource "azurerm_app_service" "spring-boot-spatial-wars" {
  name                = "my-springboot-app-service"
  resource_group_name = azurerm_resource_group.springboot_group.name
  location            = azurerm_resource_group.springboot_group.location
  app_service_plan_id = azurerm_service_plan.spring-boot-project-plan.id
}