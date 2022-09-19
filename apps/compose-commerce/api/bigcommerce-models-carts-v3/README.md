# About this module
This module contains:
- Generated models from BigCommerce's carts.v3.yaml file
- Integration with OpenAPI Generator Gradle Plugin

It only contains OpenAPI specs, Gradle build scripts, and generated model classes.

**Notes:**
1. To validate changes to `specs/carts.v3.yml`, run `./gradlew :apps:compose-commerce:api:bigcommerce-models-carts-v3:openApiValidate`.
1. To recreate models from `specs/carts.v3.yml`, run `./gradlew :apps:compose-commerce:api:bigcommerce-models-carts-v3:openApiGenerate`.
