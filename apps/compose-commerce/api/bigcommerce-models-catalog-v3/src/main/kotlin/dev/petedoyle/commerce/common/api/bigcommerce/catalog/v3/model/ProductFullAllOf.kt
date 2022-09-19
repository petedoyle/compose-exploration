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
 * 
 *
 * @param dateCreated The date on which the product was created. 
 * @param dateModified The date on which the product was modified. 
 * @param id ID of the product. Read Only.
 * @param baseVariantId The unique identifier of the base variant associated with a simple product. This value is `null` for complex products.
 * @param calculatedPrice The price of the product as seen on the storefront. It will be equal to the `sale_price`, if set, and the `price` if there is not a `sale_price`.
 * @param options 
 * @param modifiers 
 * @param mapPrice Minimum Advertised Price.
 * @param optionSetId Indicates that the product is in an Option Set (legacy V2 concept).
 * @param optionSetDisplay Legacy template setting which controls if the option set shows up to the side of or below the product image and description.
 * @param variants 
 */
@JsonClass(generateAdapter = true)
data class ProductFullAllOf (

    /* The date on which the product was created.  */
    @Json(name = "date_created")
    val dateCreated: java.time.OffsetDateTime? = null,

    /* The date on which the product was modified.  */
    @Json(name = "date_modified")
    val dateModified: java.time.OffsetDateTime? = null,

    /* ID of the product. Read Only. */
    @Json(name = "id")
    val id: kotlin.Int? = null,

    /* The unique identifier of the base variant associated with a simple product. This value is `null` for complex products. */
    @Json(name = "base_variant_id")
    val baseVariantId: kotlin.Int? = null,

    /* The price of the product as seen on the storefront. It will be equal to the `sale_price`, if set, and the `price` if there is not a `sale_price`. */
    @Json(name = "calculated_price")
    val calculatedPrice: kotlin.Float? = null,

    @Json(name = "options")
    val options: kotlin.collections.List<ProductOptionBase>? = null,

    @Json(name = "modifiers")
    val modifiers: kotlin.collections.List<ProductModifierFull>? = null,

    /* Minimum Advertised Price. */
    @Json(name = "map_price")
    val mapPrice: java.math.BigDecimal? = null,

    /* Indicates that the product is in an Option Set (legacy V2 concept). */
    @Json(name = "option_set_id")
    val optionSetId: kotlin.Int? = null,

    /* Legacy template setting which controls if the option set shows up to the side of or below the product image and description. */
    @Json(name = "option_set_display")
    val optionSetDisplay: kotlin.String? = null,

    @Json(name = "variants")
    val variants: kotlin.collections.List<ProductVariantFull>? = null

)

