# Spring Boot Kubernetes Trino Query Service

This project demonstrates how to deploy a Spring Boot application on Kubernetes and expose it through a Load Balancer.

## Table of Contents

- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Deployment](#deployment)
  - [Kubernetes Configuration](#kubernetes-configuration)
  - [Deploying to Kubernetes](#deploying-to-kubernetes)
- [Accessing the Service](#accessing-the-service)
- [Contributing](#contributing)
- [License](#license)

## Introduction

This project showcases a Spring Boot application deployed on Kubernetes, with a Load Balancer exposing a query service.

## Prerequisites

Make sure you have the following tools installed:

- Docker
- Kubernetes (kubectl)
- Helm (optional)
- Spring Boot 3.2
- openjdk:17-jdk-slim

## Project Structure

Explain the structure of your project briefly.
### controller, service 

## Deployment

### Kubernetes Configuration

Provide details on how the Kubernetes deployment is configured. Include YAML files or Helm charts if applicable.

### Deploying to Kubernetes

Step-by-step guide on deploying the Spring Boot application to Kubernetes.


## Accessing the Service

Once deployed, explain how to access the query service through the Load Balancer.

### API Endpoints

Describe the API endpoints available in your service:

- **GET /api/query**: Retrieve query results.
- **POST /api/query**: Submit a new query.

Example:

```plaintext
GET /api/v1/queries/trino?query={query}&mode=sync
POST /api/query
