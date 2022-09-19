# About this module
This module contains:
- Retrofit client for making calls to the BigCommerce REST v3 API.
- Moshi for JSON parsing
- Moshi adapters for serializing/deserializing Java 8 time APIs, BigDecimal, etc.
- A Hilt/Dagger `@Module` to wire it all up and `@InstallIn(SingletonComponent::class)`.
