/**
 * Catalog
 *
 * Manage products, brands and categories. To learn more about catalog resources see [Catalog Overview](https://developer.bigcommerce.com/api-docs/catalog/products-overview).  - [Authentication](#authentication) - [Differentiating Variants and Modifiers](#differentiating-variants-and-modifiers) - [Available Endpoints](#available-endpoints) - [Resources](#resources)  ## Authentication Requests can be authenticated by sending an `access_token` via `X-Auth-Token` HTTP header:  ```http GET /stores/{$$.env.store_hash}/v3/catalog/summary host: api.bigcommerce.com Accept: application/json X-Auth-Token: {access_token} ```  |Header|Parameter|Description| |-|-|-| |`X-Auth-Token`|`access_token `|Obtained by creating an API account or installing an app in a BigCommerce control panel.|  ### OAuth Scopes | UI Name  | Permission | Parameter                     | |----------|------------|-------------------------------| | Products | modify     | `store_v2_products`           | | Products | read-only  | `store_v2_products_read_only` |  For more information on OAuth Scopes, see: [Authentication](https://developer.bigcommerce.com/api-docs/getting-started/authentication).  For more information on Authenticating BigCommerce APIs, see: [Authentication](https://developer.bigcommerce.com/api-docs/getting-started/authentication).  ## Differentiating Variants and Modifiers [Variants](https://support.bigcommerce.com/s/article/Product-Options-v3#variations) represent a physical product made up of [Product Option](https://support.bigcommerce.com/s/article/Product-Options-v3) choices, i.e. a large blue t-shirt. Each variant can have a unique SKU.  Modifiers represent a choice a customer makes about a product that doesn't represent a physical item, i.e. text to be printed on a t-shirt. Assigning a SKU to a modifier will turn it into a variant.  See [Variant Options](https://developer.bigcommerce.com/api-docs/catalog/products-overview#variant-options) and [Modifier Options](https://developer.bigcommerce.com/api-docs/catalog/products-overview#modifier-options) for more information.  ## Available Endpoints | Resource / Endpoint                     | Description                                                             | |-----------------------------------------|-------------------------------------------------------------------------| | Brand Images                            | Create and manage brand images                                          | | Brand Metafields                        | Create and manage brand metafields                                      | | Brands                                  | Create and manage brands                                                | | Catalog                                 | Create and manage store categories, products, and brands                | | Categories                              | Create and manage categorties                                           | | Category Images                         | Create and manage category images                                       | | Category Metafields                     | Create and manage category metafields                                   | | Product Bulk Pricing Rules              | Create and manage product bulk pricing rules                            | | Product Complex Rules                   | Create and manage product complex rules                                 | | Product Custom Fields                   | Create and manage product custom fields                                 | | Product Images                          | Create and manage product images                                        | | Product Metafields                      | Create and manage product meta fields                                   | | Product Modifier Images                 | Create and manage product modifer images                                | | Product Modifier Values                 | Create and manage product modifier values                               | | Product Modifiers                       | Create and manage product midifiers                                     | | Product Reviews                         | Create and manage product reviews                                       | | Product Variant Option Values           | Create and manage product variant option values                         | | Product Variant Options                 | Create and manage product variant options                               | | Product Variants                        | Create and manage product variants                                      | | Product Videos                          | Create and manage product videos                                        | | Products                                | Create and manage products                                              | | ProductVariant Metafields               | Create and manage product meta flields                                  | | Variants                                | Get and update all variants                                             |  ## Resources  ### Webhooks * [Products](/api-docs/store-management/webhooks/events#products) * [Categories](/api-docs/store-management/webhooks/events#category) * [SKU](/api-docs/store-management/webhooks/events#sku)
 *
 * The version of the OpenAPI document: 3.0-noerrors
 * 
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Common Category object properties.
 *
 * @param parentId The unique numeric ID of the category's parent. This field controls where the category sits in the tree of categories that organize the catalog. Required in a POST if creating a child category.
 * @param name The name displayed for the category. Name is unique with respect to the category's siblings. Required in a POST.
 * @param id Unique ID of the *Category*. Increments sequentially. Read-Only.
 * @param description The product description, which can include HTML formatting. 
 * @param views Number of views the category has on the storefront. 
 * @param sortOrder Priority this category will be given when included in the menu and category pages. The lower the number, the closer to the top of the results the category will be. 
 * @param pageTitle Custom title for the category page. If not defined, the category name will be used as the meta title. 
 * @param searchKeywords A comma-separated list of keywords that can be used to locate the category when searching the store. 
 * @param metaKeywords Custom meta keywords for the category page. If not defined, the store's default keywords will be used. Must post as an array like: [\"awesome\",\"sauce\"]. 
 * @param metaDescription Custom meta description for the category page. If not defined, the store's default meta description will be used. 
 * @param layoutFile A valid layout file. (Please refer to [this article](https://support.bigcommerce.com/articles/Public/Creating-Custom-Template-Files/) on creating category files.) This field is writable only for stores with a Blueprint theme applied. 
 * @param isVisible Flag to determine whether the product should be displayed to customers browsing the store. If `true`, the category will be displayed. If `false`, the category will be hidden from view. 
 * @param defaultProductSort Determines how the products are sorted on category page load. 
 * @param imageUrl Image URL used for this category on the storefront. Images can be uploaded via form file post to `/categories/{categoryId}/image`, or by providing a publicly accessible URL in this field. 
 * @param customUrl 
 */
@JsonClass(generateAdapter = true)
data class CategoryFull (

    /* The unique numeric ID of the category's parent. This field controls where the category sits in the tree of categories that organize the catalog. Required in a POST if creating a child category. */
    @Json(name = "parent_id")
    val parentId: kotlin.Int,

    /* The name displayed for the category. Name is unique with respect to the category's siblings. Required in a POST. */
    @Json(name = "name")
    val name: kotlin.String,

    /* Unique ID of the *Category*. Increments sequentially. Read-Only. */
    @Json(name = "id")
    val id: kotlin.Int? = null,

    /* The product description, which can include HTML formatting.  */
    @Json(name = "description")
    val description: kotlin.String? = null,

    /* Number of views the category has on the storefront.  */
    @Json(name = "views")
    val views: kotlin.Int? = null,

    /* Priority this category will be given when included in the menu and category pages. The lower the number, the closer to the top of the results the category will be.  */
    @Json(name = "sort_order")
    val sortOrder: kotlin.Int? = null,

    /* Custom title for the category page. If not defined, the category name will be used as the meta title.  */
    @Json(name = "page_title")
    val pageTitle: kotlin.String? = null,

    /* A comma-separated list of keywords that can be used to locate the category when searching the store.  */
    @Json(name = "search_keywords")
    val searchKeywords: kotlin.String? = null,

    /* Custom meta keywords for the category page. If not defined, the store's default keywords will be used. Must post as an array like: [\"awesome\",\"sauce\"].  */
    @Json(name = "meta_keywords")
    val metaKeywords: kotlin.collections.List<kotlin.String>? = null,

    /* Custom meta description for the category page. If not defined, the store's default meta description will be used.  */
    @Json(name = "meta_description")
    val metaDescription: kotlin.String? = null,

    /* A valid layout file. (Please refer to [this article](https://support.bigcommerce.com/articles/Public/Creating-Custom-Template-Files/) on creating category files.) This field is writable only for stores with a Blueprint theme applied.  */
    @Json(name = "layout_file")
    val layoutFile: kotlin.String? = null,

    /* Flag to determine whether the product should be displayed to customers browsing the store. If `true`, the category will be displayed. If `false`, the category will be hidden from view.  */
    @Json(name = "is_visible")
    val isVisible: kotlin.Boolean? = null,

    /* Determines how the products are sorted on category page load.  */
    @Json(name = "default_product_sort")
    val defaultProductSort: CategoryFull.DefaultProductSort? = null,

    /* Image URL used for this category on the storefront. Images can be uploaded via form file post to `/categories/{categoryId}/image`, or by providing a publicly accessible URL in this field.  */
    @Json(name = "image_url")
    val imageUrl: kotlin.String? = null,

    @Json(name = "custom_url")
    val customUrl: CustomUrlFull? = null

) {

    /**
     * Determines how the products are sorted on category page load. 
     *
     * Values: useStoreSettings,featured,newest,bestSelling,alphaAsc,alphaDesc,avgCustomerReview,priceAsc,priceDesc
     */
    enum class DefaultProductSort(val value: kotlin.String) {
        @Json(name = "use_store_settings") useStoreSettings("use_store_settings"),
        @Json(name = "featured") featured("featured"),
        @Json(name = "newest") newest("newest"),
        @Json(name = "best_selling") bestSelling("best_selling"),
        @Json(name = "alpha_asc") alphaAsc("alpha_asc"),
        @Json(name = "alpha_desc") alphaDesc("alpha_desc"),
        @Json(name = "avg_customer_review") avgCustomerReview("avg_customer_review"),
        @Json(name = "price_asc") priceAsc("price_asc"),
        @Json(name = "price_desc") priceDesc("price_desc");
    }
}

