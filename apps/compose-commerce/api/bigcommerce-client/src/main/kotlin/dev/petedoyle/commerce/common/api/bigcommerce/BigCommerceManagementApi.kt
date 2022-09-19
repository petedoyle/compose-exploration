/*
 * Copyright (C) 2022 Pete Doyle
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.petedoyle.commerce.common.api.bigcommerce

import com.slack.eithernet.ApiResult
import dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model.ErrorBase
import dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model.ModifierCollectionResponse
import dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model.ProductResponse
import dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model.VariantCollectionResponse
import dev.petedoyle.commerce.common.api.bigcommerce.models.CategoryCollectionResponse
import dev.petedoyle.commerce.common.api.bigcommerce.models.ProductCollectionResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BigCommerceManagementApi {

    // Note: Retrofit sends query lists in the form of ?include=images&include=options
    //  The BC API requires it as ?include=images,options in order to work, hence
    //  listOf("images,options") instead of listOf("images", "options")
    @GET("v3/catalog/products?is_featured=1")
    suspend fun listFeaturedProducts(
        @Query("limit") limit: Int = 100,
        @Query("include") include: List<String> = listOf("images,options"),
    ): ApiResult<ProductCollectionResponse, ErrorBase>

    // Note: Retrofit sends query lists in the form of ?include=images&include=options
    //  The BC API requires it as ?include=images,options in order to work, hence
    //  listOf("images,options") instead of listOf("images", "options")
    @GET("v3/catalog/products/{productId}")
    suspend fun getProduct(
        @Path("productId") productId: Int,
        @Query("include") include: List<String> = listOf("images,options"),
    ): ApiResult<ProductResponse, ErrorBase>

    @GET("v3/catalog/products/{productId}/variants")
    suspend fun getProductVariants(
        @Path("productId") productId: Int,
        @Query("limit") limit: Int = 100,
    ): ApiResult<VariantCollectionResponse, ErrorBase>

    @GET("v3/catalog/products/{productId}/modifiers")
    suspend fun getProductModifiers(
        @Path("productId") productId: Int,
        @Query("limit") limit: Int = 100,
    ): ApiResult<ModifierCollectionResponse, ErrorBase>

    @GET("v3/catalog/categories")
    suspend fun listCategories(@Query("limit") limit: Int = 100): ApiResult<CategoryCollectionResponse, ErrorBase>
}
