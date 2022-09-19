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
 * The model for a POST to create an image on a product.
 *
 * @param id The unique numeric ID of the image; increments sequentially. 
 * @param productId The unique numeric identifier for the product with which the image is associated. 
 * @param imageFile Must be sent as a multipart/form-data field in the request body. 
 * @param urlZoom The zoom URL for this image. By default, this is used as the zoom image on product pages when zoom images are enabled. 
 * @param urlStandard The standard URL for this image. By default, this is used for product-page images. 
 * @param urlThumbnail The thumbnail URL for this image. By default, this is the image size used on the category page and in side panels. 
 * @param urlTiny The tiny URL for this image. By default, this is the image size used for thumbnails beneath the product image on a product page. 
 * @param dateModified The date on which the product image was modified. 
 * @param isThumbnail Flag for identifying whether the image is used as the product's thumbnail. 
 * @param sortOrder The order in which the image will be displayed on the product page. Higher integers give the image a lower priority. When updating, if the image is given a lower priority, all images with a `sort_order` the same as or greater than the image's new `sort_order` value will have their `sort_order`s reordered. 
 * @param description The description for the image. 
 * @param imageUrl Must be a fully qualified URL path, including protocol. Limit of 8MB per file. 
 */
@JsonClass(generateAdapter = true)
data class ProductImagePost (

    /* The unique numeric ID of the image; increments sequentially.  */
    @Json(name = "id")
    val id: kotlin.Int? = null,

    /* The unique numeric identifier for the product with which the image is associated.  */
    @Json(name = "product_id")
    val productId: kotlin.Int? = null,

    /* Must be sent as a multipart/form-data field in the request body.  */
    @Json(name = "image_file")
    val imageFile: kotlin.String? = null,

    /* The zoom URL for this image. By default, this is used as the zoom image on product pages when zoom images are enabled.  */
    @Json(name = "url_zoom")
    val urlZoom: kotlin.String? = null,

    /* The standard URL for this image. By default, this is used for product-page images.  */
    @Json(name = "url_standard")
    val urlStandard: kotlin.String? = null,

    /* The thumbnail URL for this image. By default, this is the image size used on the category page and in side panels.  */
    @Json(name = "url_thumbnail")
    val urlThumbnail: kotlin.String? = null,

    /* The tiny URL for this image. By default, this is the image size used for thumbnails beneath the product image on a product page.  */
    @Json(name = "url_tiny")
    val urlTiny: kotlin.String? = null,

    /* The date on which the product image was modified.  */
    @Json(name = "date_modified")
    val dateModified: java.time.OffsetDateTime? = null,

    /* Flag for identifying whether the image is used as the product's thumbnail.  */
    @Json(name = "is_thumbnail")
    val isThumbnail: kotlin.Boolean? = null,

    /* The order in which the image will be displayed on the product page. Higher integers give the image a lower priority. When updating, if the image is given a lower priority, all images with a `sort_order` the same as or greater than the image's new `sort_order` value will have their `sort_order`s reordered.  */
    @Json(name = "sort_order")
    val sortOrder: kotlin.Int? = null,

    /* The description for the image.  */
    @Json(name = "description")
    val description: kotlin.String? = null,

    /* Must be a fully qualified URL path, including protocol. Limit of 8MB per file.  */
    @Json(name = "image_url")
    val imageUrl: kotlin.String? = null

)

