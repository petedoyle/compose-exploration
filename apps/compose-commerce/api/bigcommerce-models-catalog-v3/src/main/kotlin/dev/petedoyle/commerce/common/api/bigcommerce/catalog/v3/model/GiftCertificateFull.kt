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
 * A gift-certificate model.
 *
 * @param code The gift-certificate code. 
 * @param originalBalance The balance on a gift certificate when it was purchased. 
 * @param startingBalance The balance on a gift certificate at the time of this purchase. 
 * @param remainingBalance The remaining balance on a gift certificate. 
 * @param status The status of a gift certificate: `active` - gift certificate is active; `pending` - gift certificate purchase is pending; `disabled` - gift certificate is disabled; `expired` - gift certificate is expired. 
 */
@JsonClass(generateAdapter = true)
data class GiftCertificateFull (

    /* The gift-certificate code.  */
    @Json(name = "code")
    val code: kotlin.String? = null,

    /* The balance on a gift certificate when it was purchased.  */
    @Json(name = "original_balance")
    val originalBalance: kotlin.Float? = null,

    /* The balance on a gift certificate at the time of this purchase.  */
    @Json(name = "starting_balance")
    val startingBalance: kotlin.Float? = null,

    /* The remaining balance on a gift certificate.  */
    @Json(name = "remaining_balance")
    val remainingBalance: kotlin.Float? = null,

    /* The status of a gift certificate: `active` - gift certificate is active; `pending` - gift certificate purchase is pending; `disabled` - gift certificate is disabled; `expired` - gift certificate is expired.  */
    @Json(name = "status")
    val status: GiftCertificateFull.Status? = null

) {

    /**
     * The status of a gift certificate: `active` - gift certificate is active; `pending` - gift certificate purchase is pending; `disabled` - gift certificate is disabled; `expired` - gift certificate is expired. 
     *
     * Values: active,pending,disabled,expired
     */
    enum class Status(val value: kotlin.String) {
        @Json(name = "active") active("active"),
        @Json(name = "pending") pending("pending"),
        @Json(name = "disabled") disabled("disabled"),
        @Json(name = "expired") expired("expired");
    }
}

