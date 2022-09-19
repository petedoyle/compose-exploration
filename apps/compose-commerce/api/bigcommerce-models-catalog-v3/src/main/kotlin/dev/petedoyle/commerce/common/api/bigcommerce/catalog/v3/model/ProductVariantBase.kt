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
 * Common Variant properties.
 *
 * @param costPrice The cost price of the variant. Not affected by Price List prices.
 * @param price This variant's base price on the storefront. If a Price List ID is used, the Price List value will be used. If a Price List ID is not used, and this value is `null`, the product's default price (set in the Product resource's `price` field) will be used as the base price.
 * @param salePrice This variant's sale price on the storefront. If a Price List ID is used, the Price List value will be used. If a Price List ID is not used, and this value is null, the product's sale price (set in the Product resource's `price` field) will be used as the sale price.
 * @param retailPrice This variant's retail price on the storefront. If a Price List ID is used, the Price List value will be used. If a Price List ID is not used, and this value is null, the product's retail price (set in the Product resource's `price` field) will be used as the retail price.
 * @param weight This variant's base weight on the storefront. If this value is null, the product's default weight (set in the Product resource's weight field) will be used as the base weight.
 * @param width Width of the variant, which can be used when calculating shipping costs. If this value is `null`, the product's default width (set in the Product resource's `width` field) will be used as the base width. 
 * @param height Height of the variant, which can be used when calculating shipping costs. If this value is `null`, the product's default height (set in the Product resource's `height` field) will be used as the base height. 
 * @param depth Depth of the variant, which can be used when calculating shipping costs. If this value is `null`, the product's default depth (set in the Product resource's `depth` field) will be used as the base depth. 
 * @param isFreeShipping Flag used to indicate whether the variant has free shipping. If `true`, the shipping cost for the variant will be zero. 
 * @param fixedCostShippingPrice A fixed shipping cost for the variant. If defined, this value will be used during checkout instead of normal shipping-cost calculation. 
 * @param purchasingDisabled If `true`, this variant will not be purchasable on the storefront.
 * @param purchasingDisabledMessage If `purchasing_disabled` is `true`, this message should show on the storefront when the variant is selected.
 * @param upc The UPC code used in feeds for shopping comparison sites and external channel integrations.
 * @param inventoryLevel Inventory level for the variant, which is used when the product's inventory_tracking is set to `variant`.
 * @param inventoryWarningLevel When the variant hits this inventory level, it is considered low stock.
 * @param binPickingNumber Identifies where in a warehouse the variant is located.
 * @param mpn The Manufacturer Part Number (MPN) for the variant.
 */
@JsonClass(generateAdapter = true)
data class ProductVariantBase (

    /* The cost price of the variant. Not affected by Price List prices. */
    @Json(name = "cost_price")
    val costPrice: kotlin.Double? = null,

    /* This variant's base price on the storefront. If a Price List ID is used, the Price List value will be used. If a Price List ID is not used, and this value is `null`, the product's default price (set in the Product resource's `price` field) will be used as the base price. */
    @Json(name = "price")
    val price: kotlin.Double? = null,

    /* This variant's sale price on the storefront. If a Price List ID is used, the Price List value will be used. If a Price List ID is not used, and this value is null, the product's sale price (set in the Product resource's `price` field) will be used as the sale price. */
    @Json(name = "sale_price")
    val salePrice: kotlin.Double? = null,

    /* This variant's retail price on the storefront. If a Price List ID is used, the Price List value will be used. If a Price List ID is not used, and this value is null, the product's retail price (set in the Product resource's `price` field) will be used as the retail price. */
    @Json(name = "retail_price")
    val retailPrice: kotlin.Double? = null,

    /* This variant's base weight on the storefront. If this value is null, the product's default weight (set in the Product resource's weight field) will be used as the base weight. */
    @Json(name = "weight")
    val weight: kotlin.Double? = null,

    /* Width of the variant, which can be used when calculating shipping costs. If this value is `null`, the product's default width (set in the Product resource's `width` field) will be used as the base width.  */
    @Json(name = "width")
    val width: kotlin.Double? = null,

    /* Height of the variant, which can be used when calculating shipping costs. If this value is `null`, the product's default height (set in the Product resource's `height` field) will be used as the base height.  */
    @Json(name = "height")
    val height: kotlin.Double? = null,

    /* Depth of the variant, which can be used when calculating shipping costs. If this value is `null`, the product's default depth (set in the Product resource's `depth` field) will be used as the base depth.  */
    @Json(name = "depth")
    val depth: kotlin.Double? = null,

    /* Flag used to indicate whether the variant has free shipping. If `true`, the shipping cost for the variant will be zero.  */
    @Json(name = "is_free_shipping")
    val isFreeShipping: kotlin.Boolean? = null,

    /* A fixed shipping cost for the variant. If defined, this value will be used during checkout instead of normal shipping-cost calculation.  */
    @Json(name = "fixed_cost_shipping_price")
    val fixedCostShippingPrice: kotlin.Double? = null,

    /* If `true`, this variant will not be purchasable on the storefront. */
    @Json(name = "purchasing_disabled")
    val purchasingDisabled: kotlin.Boolean? = null,

    /* If `purchasing_disabled` is `true`, this message should show on the storefront when the variant is selected. */
    @Json(name = "purchasing_disabled_message")
    val purchasingDisabledMessage: kotlin.String? = null,

    /* The UPC code used in feeds for shopping comparison sites and external channel integrations. */
    @Json(name = "upc")
    val upc: kotlin.String? = null,

    /* Inventory level for the variant, which is used when the product's inventory_tracking is set to `variant`. */
    @Json(name = "inventory_level")
    val inventoryLevel: kotlin.Int? = null,

    /* When the variant hits this inventory level, it is considered low stock. */
    @Json(name = "inventory_warning_level")
    val inventoryWarningLevel: kotlin.Int? = null,

    /* Identifies where in a warehouse the variant is located. */
    @Json(name = "bin_picking_number")
    val binPickingNumber: kotlin.String? = null,

    /* The Manufacturer Part Number (MPN) for the variant. */
    @Json(name = "mpn")
    val mpn: kotlin.String? = null

)

